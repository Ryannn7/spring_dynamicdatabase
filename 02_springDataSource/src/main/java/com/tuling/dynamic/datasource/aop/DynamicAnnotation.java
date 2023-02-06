package com.tuling.dynamic.datasource.aop;

import com.tuling.dynamic.datasource.DynamicDataSource;
import com.tuling.dynamic.datasource.config.WR;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * @author liuzongshuai
 * @date 2023/2/1 16:55
 */
@Component
@EnableAspectJAutoProxy //启动AOP
@Aspect
@Slf4j
public class DynamicAnnotation {

    @Pointcut("execution(* com.tuling.dynamic.datasource..*.*(..))")
    public void pointMethod(){

    };

    @Before("execution(* com.tuling.dynamic.datasource..*.*(..)) && @annotation(wr)")
    public void dynamicDataSource(JoinPoint joinPoint, WR wr){
        //获取方法的注解
        String value = wr.value();
        System.out.println("方法："+joinPoint.getSignature().getName()+ " 使用的数据源: "+value);
        DynamicDataSource.name.set(value);//设置使用的数据源

    }

}
