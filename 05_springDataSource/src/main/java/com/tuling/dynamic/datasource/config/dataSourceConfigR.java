package com.tuling.dynamic.datasource.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionManager;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;

/**
 * @author liuzongshuai
 * @date 2023/2/2 17:44
 */
@Configuration
// 继承mybatis:
// 1. 指定扫描的mapper接口包（主库）
// 2. 指定使用sqlSessionFactory是哪个（主库）
@MapperScan(basePackages = "com.tuling.dynamic.datasource.mapper.r",
        sqlSessionFactoryRef = "rSqlSessionFactory")
public class dataSourceConfigR {


    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.datasource-r")
    public DataSource rDataSource() {
        // 底层会自动拿到spring.datasource中的配置， 创建一个DruidDataSource
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public SqlSessionFactory rSqlSessionFactory() throws Exception {

        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(rDataSource());
        return sessionFactory.getObject();
    }

    @Bean
    public DataSourceTransactionManager rTransactionManager(){
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(rDataSource());
        return dataSourceTransactionManager;
    }

    @Bean
    public TransactionTemplate rTransactionTemplate(){
        return new TransactionTemplate(rTransactionManager());
    }
}
