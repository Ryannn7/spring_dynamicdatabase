package com.tuling.dynamic.datasource.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.plugin.Interceptor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 */
@Configuration
public class DataSourceConfig  {


    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.datasource-w")
    public DataSource firstDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.datasource-r")
    public DataSource secondDataSource() {
        // 底层会自动拿到spring.datasource中的配置， 创建一个DruidDataSource
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    public Interceptor dynamicDataSourcePlugin(){
        return new DynamicDataSourcePlugin();
    }





}
