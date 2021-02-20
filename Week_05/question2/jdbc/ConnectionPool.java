package question2.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

/**
 * @author deguang
 * @date 2021/02/21
 */

public class ConnectionPool {

    public static DataSource getDataSource(){
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/student");
        config.setUsername("root");
        config.setPassword("password");
        config.addDataSourceProperty("connectionTimeout", "1000"); // 连接超时：1秒
        config.addDataSourceProperty("idleTimeout", "60000"); // 空闲超时：60秒
        config.addDataSourceProperty("maximumPoolSize", "10"); // 最大连接数：10
        DataSource ds = new HikariDataSource(config);
        return ds;
    }
}
