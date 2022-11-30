package cc.mrbird.common.config;

import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.env.Environment;


@ImportResource(locations = "classpath*:resource.*.xml")
@Configuration
public class DataSourceConfig {
    @Autowired
    private Environment environment;
    @Value("${Salt}")
    String Salt;
    @Bean(destroyMethod = "close")
    public DruidDataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        String passwd = environment.getProperty("spring.datasource.druid.password");
        dataSource.setPassword(getPasswd(passwd));
        dataSource.setUrl(environment.getProperty("spring.datasource.druid.url"));
        dataSource.setUsername(environment.getProperty("spring.datasource.druid.username"));
        dataSource.setDriverClassName(environment.getProperty("spring.datasource.druid.driverClassName"));
        dataSource.setDbType(environment.getProperty("spring.datasource.druid.db-type"));
        dataSource.setInitialSize(environment.getProperty("spring.datasource.druid.initial-size", Integer.class));
        dataSource.setMinIdle(environment.getProperty("spring.datasource.druid.min-idle", Integer.class));
        dataSource.setMaxActive(environment.getProperty("spring.datasource.druid.max-active", Integer.class));
        dataSource.setMaxWait(environment.getProperty("spring.datasource.druid.max-wait", Integer.class));
        return dataSource;
    }
    //guide from https://hutool.cn/docs/#/crypto/%E9%9D%9E%E5%AF%B9%E7%A7%B0%E5%8A%A0%E5%AF%86-AsymmetricCrypto
    String getPasswd(String passwd) {
        RSA rsa = new RSA(null,Salt);
        String encrypt2 = rsa.decryptStr(passwd, KeyType.PublicKey);
        return encrypt2;
    }
}
