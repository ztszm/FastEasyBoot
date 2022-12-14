package cc.mrbird.system.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import cc.mrbird.common.domain.QueryRequest;
import com.alibaba.fastjson.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.assertj.core.util.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cc.mrbird.common.service.impl.BaseService;
import cc.mrbird.common.util.MD5Utils;
import cc.mrbird.system.dao.UserMapper;
import cc.mrbird.system.dao.UserRoleMapper;
import cc.mrbird.system.domain.User;
import cc.mrbird.system.domain.UserRole;
import cc.mrbird.system.domain.UserWithRole;
import cc.mrbird.system.service.UserRoleService;
import cc.mrbird.system.service.UserService;
import sun.plugin.com.Utils;
import tk.mybatis.mapper.entity.Example;
@Service("userService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class UserServiceImpl extends BaseService<User> implements UserService {

    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private cc.mrbird.common.service.RedisService redisService;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private UserRoleService userRoleService;

    @Override
    public User findByName(String userName) {
        String rdise = redisService.get(userName) == null?"":redisService.get(userName);
        if(!(rdise.equals(""))){
            return   JSONObject.parseObject(rdise,User.class);
        }else{
            Example example = new Example(User.class);
            example.createCriteria().andCondition("lower(username)=", userName.toLowerCase());
            List<User> list = this.selectByExample(example);
            String json = JSONObject.toJSONString(list.get(0));
            redisService.set(userName,json);
            return list.isEmpty() ? null : list.get(0);
        }
    }
    @Override
    public List<User> findUserWithDept(User user, QueryRequest request) {
        try {
            return this.userMapper.findUserWithDept(user);
        } catch (Exception e) {
            log.error("error", e);
            return new ArrayList<>();
        }
    }

    @Override
    @Transactional
    public void registUser(User user) {
        user.setCrateTime(new Date());
        user.setTheme(User.DEFAULT_THEME);
        user.setAvatar(User.DEFAULT_AVATAR);
        user.setSsex(User.SEX_UNKNOW);
        user.setPassword(MD5Utils.encrypt(user.getUsername(), user.getPassword()));
        this.save(user);
        UserRole ur = new UserRole();
        ur.setUserId(user.getUserId());
        ur.setRoleId(3L);
        this.userRoleMapper.insert(ur);
    }

    @Override
    @Transactional
    public void updateTheme(String theme, String userName) {
        Example example = new Example(User.class);
        example.createCriteria().andCondition("username=", userName);
        User user = new User();
        user.setTheme(theme);
        this.userMapper.updateByExampleSelective(user, example);
    }

    @Override
    @Transactional
    public void addUser(User user, Long[] roles) {
        user.setCrateTime(new Date());
        user.setTheme(User.DEFAULT_THEME);
        user.setAvatar(User.DEFAULT_AVATAR);
        user.setPassword(MD5Utils.encrypt(user.getUsername(), user.getPassword()));
        this.save(user);
        setUserRoles(user, roles);
    }

    private void setUserRoles(User user, Long[] roles) {
        Arrays.stream(roles).forEach(roleId -> {
            UserRole ur = new UserRole();
            ur.setUserId(user.getUserId());
            ur.setRoleId(roleId);
            this.userRoleMapper.insert(ur);
        });
    }

    @Override
    @Transactional
    public void updateUser(User user, Long[] roles) {
        user.setPassword(null);
        user.setUsername(null);
        user.setModifyTime(new Date());
        this.updateNotNull(user);
        Example example = new Example(UserRole.class);
        example.createCriteria().andCondition("user_id=", user.getUserId());
        this.userRoleMapper.deleteByExample(example);
        setUserRoles(user, roles);
    }

    @Override
    @Transactional
    public void deleteUsers(String userIds) {
        List<String> list = Arrays.asList(userIds.split(","));
        this.batchDelete(list, "userId", User.class);

        this.userRoleService.deleteUserRolesByUserId(userIds);
    }

    @Override
    @Transactional
    public void updateLoginTime(String userName) {
        Example example = new Example(User.class);
        example.createCriteria().andCondition("lower(username)=", userName.toLowerCase());
        User user = new User();
        user.setLastLoginTime(new Date());
        this.userMapper.updateByExampleSelective(user, example);
    }

    @Override
    @Transactional
    public void updatePassword(String password) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        Example example = new Example(User.class);
        example.createCriteria().andCondition("username=", user.getUsername());
        String newPassword = MD5Utils.encrypt(user.getUsername().toLowerCase(), password);
        user.setPassword(newPassword);
        this.userMapper.updateByExampleSelective(user, example);
    }

    @Override
    public UserWithRole findById(Long userId) {
        List<UserWithRole> list = this.userMapper.findUserWithRole(userId);
        List<Long> roleList = list.stream().map(UserWithRole::getRoleId).collect(Collectors.toList());
        if (list.isEmpty()){
            return new UserWithRole();
        }
        UserWithRole userWithRole = list.get(0);
        userWithRole.setRoleIds(roleList);
        return userWithRole;
    }

    @Override
    public User findUserProfile(User user) {
        return this.userMapper.findUserProfile(user);
    }

    @Override
    @Transactional
    public void updateUserProfile(User user) {
        user.setUsername(null);
        user.setPassword(null);
        if (user.getDeptId() == null)
            user.setDeptId(0L);
        this.updateNotNull(user);
    }

}
