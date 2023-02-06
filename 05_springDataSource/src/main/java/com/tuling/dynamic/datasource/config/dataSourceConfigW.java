package com.tuling.dynamic.datasource.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
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
@MapperScan(basePackages = "com.tuling.dynamic.datasource.mapper.w",sqlSessionFactoryRef = "wSqlSessionFactory")
public class dataSourceConfigW {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.datasource-w")
    public DataSource wDataSource() {
        // 底层会自动拿到spring.datasource中的配置， 创建一个DruidDataSource
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public SqlSessionFactory wSqlSessionFactory() throws Exception {

        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(wDataSource());
        // 指定主库对应的mapper.xml文件
        /*sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper/order/*.xml"));
        如果是使用註解的方式實現则使用@MapperScan來指定mappper即可。
          */
        return sessionFactory.getObject();
    }

    @Bean
    @Primary
    public DataSourceTransactionManager wTransactionManager(){
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(wDataSource());
        return dataSourceTransactionManager;
    }


    @Bean
    public TransactionTemplate wTransactionTemplate(){
        return new TransactionTemplate(wTransactionManager());
    }
}
