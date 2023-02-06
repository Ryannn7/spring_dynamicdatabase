package com.tuling.dynamic.datasource;

import com.tuling.dynamic.datasource.enums.DataSourceMark;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 */
@Component
@Primary   // 将该Bean设置为主要注入Bean,
public class DynamicDataSource extends AbstractRoutingDataSource  {


    // 当前使用的数据源标识
    public static ThreadLocal<String> name=new ThreadLocal<>();

    // 写
    @Autowired
    DataSource firstDataSource;
    // 读
    @Autowired
    DataSource secondDataSource;


    /**
     * 返回当前数据源标识
     * @return
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return name.get();
    }

    @Override
    public void afterPropertiesSet() {
        //1,为targetDataSource初始化所有数据源
        Map<Object,Object> targetDataSources= new HashMap<>();
        targetDataSources.put(DataSourceMark.W.name(),firstDataSource);
        targetDataSources.put(DataSourceMark.R.name(),secondDataSource);
        super.setTargetDataSources(targetDataSources);

        //2,为defaultDataSource设置默认的数据源
        super.setDefaultTargetDataSource(firstDataSource);
        super.afterPropertiesSet();
    }
}

