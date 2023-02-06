package com.tuling.dynamic.datasource.config;

import com.tuling.dynamic.datasource.DynamicDataSource;
import com.tuling.dynamic.datasource.enums.DataSourceMark;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Properties;

/**
 * @author liuzongshuai
 * @date 2023/2/2 14:32
 */
@Intercepts({@Signature(type = Executor.class,method = "update",args={MappedStatement.class,Object.class}),
@Signature(type = Executor.class,method = "query",args={MappedStatement.class,Object.class, RowBounds.class, ResultHandler.class})})
public class DynamicDataSourcePlugin implements Interceptor {


    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] objects = invocation.getArgs();
        MappedStatement ms = (MappedStatement) objects[0];
        //使用读方法
        if(ms.getSqlCommandType().equals(SqlCommandType.SELECT)){
            DynamicDataSource.name.set(DataSourceMark.R.getName());
        }else {
            DynamicDataSource.name.set(DataSourceMark.W.getName());
        }
        //修改当前线程要选择的数据源的key
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Interceptor.super.plugin(target);
    }

    @Override
    public void setProperties(Properties properties) {
        Interceptor.super.setProperties(properties);
    }
}
