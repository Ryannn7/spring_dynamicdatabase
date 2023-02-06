package com.tuling.dynamic.datasource.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.tuling.dynamic.datasource.DynamicDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 */
@Configuration
public class DataSourceConfig  {


    /**
     * @ConfigurationProperties的prefix书写规范
     * prefix值应该用小写字母、数字、中划线“-”区分单词
     * 不能用大写字母、特殊字符，区分单词不能用下划线。
     * 例如：my-info，不要用my_info、myInfo
     *
     * springboot的提示 ：
     *
     * Reason: Canonical names should be kebab-case ('-' separated), lowercase alpha-numeric characters and must start with a letter
     *
     * 意思是：规范化名称应该是kebab-case(’-’分开) ，小写字母数字字符，并且必须以字母开头。
     *
     * yml文件
     * yml文件可以大小写、下划线、中划线同时使用；
     * yml与@ConfigurationProperties的prefix的规范不同。yml文件不受@ConfigurationProperties注解prefix规则的影响
     * 示例
     * ————————————————
     * 版权声明：本文为CSDN博主「x235cl」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
     * 原文链接：https://blog.csdn.net/x235cl/article/details/109965032*
     */
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





}
