package com.tuling.dynamic.datasource;

import javafx.fxml.Initializable;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 */
@Component
@Primary   // 将该Bean设置为主要注入Bean,
public class DynamicDataSource implements DataSource, InitializingBean {


    // 当前使用的数据源标识
    public static ThreadLocal<String> name=new ThreadLocal<>();

    // 写
    @Autowired
    DataSource dataSource1;
    // 读
    @Autowired
    DataSource dataSource2;


    @Override
    public Connection getConnection() throws SQLException {
        if(name.get().equals("W")){
            return dataSource1.getConnection();
        }else{
            return dataSource2.getConnection();
        }
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        if(name.get().equals("W")){
            return dataSource1.getConnection();
        }else{
            return dataSource2.getConnection();
        }

    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }

    /**
     * InitializingBean 实现此接口需要重写afterPropertiesSet，初始化的过程。
     * 其实也可以在构造函数中进行初始化。
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
            //todo ..初始化
        name.set("W");
    }
}
