package cc.mrbird.common.config;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class DataSourceConfig {

    @Value("${PublicKey}")
    String PublicKey;

    @Value("${spring.datasource.druid.password}")
    String password;

    @Value("${spring.datasource.druid.url}")
    String url;

    @Value("${spring.datasource.druid.username}")
    String username;

    @Value("${spring.datasource.druid.driverClassName}")
    String driverClassName;

    @Value("${spring.datasource.druid.db-type}")
    String db_type;

    @Value("${spring.datasource.druid.initial-size}")
    int initial_size;

    @Value("${spring.datasource.druid.min-idle}")
    int min_idle;

    @Value("${spring.datasource.druid.max-active}")
    int max_active;

    @Value("${spring.datasource.druid.max-wait}")
    int max_wait;
    @Bean(destroyMethod = "close")
    public DruidDataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setPassword(getPasswd(password));
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setDriverClassName(driverClassName);
        dataSource.setDbType(db_type);
        dataSource.setInitialSize(initial_size);
        dataSource.setMinIdle(min_idle);
        dataSource.setMaxActive(max_active);
        dataSource.setMaxWait(max_wait);
        return dataSource;
    }
    //guide from https://hutool.cn/docs/#/crypto/%E9%9D%9E%E5%AF%B9%E7%A7%B0%E5%8A%A0%E5%AF%86-AsymmetricCrypto
    String getPasswd(String passwd) {
        RSA rsa = new RSA(null,PublicKey);
        String encrypt2 = rsa.decryptStr(passwd, KeyType.PublicKey);
        return encrypt2;
    }
}
