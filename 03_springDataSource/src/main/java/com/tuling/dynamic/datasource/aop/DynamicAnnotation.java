package com.tuling.dynamic.datasource.aop;

import com.tuling.dynamic.datasource.DynamicDataSource;
import com.tuling.dynamic.datasource.config.WR;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Bean;
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


  /* @Pointcut("execution(* com.tuling.dynamic.datasource.service.*.*(..))")
    public void pointMethod() {

    };*/


    // @Before("execution(* com.tuling.dynamic.datasource.service..*.*(..)) && @annotation(wr)")
  //  @Around("pointMethod()")
    public Object dynamicDataSource(ProceedingJoinPoint joinPoint) {
        Object obj=new Object();
        try {


            WR annotation = joinPoint.getTarget().getClass().getAnnotation(WR.class);
            if (annotation != null) {
                String value = annotation.value();
                System.out.println("类上的注解" + value);

                //获取方法的注解
                System.out.println("方法：" + joinPoint.getSignature().getName() + " 使用的数据源: " + value);
                DynamicDataSource.name.set(value);//设置使用的数据源

                    obj = joinPoint.proceed();
                    log.info("方法执行完成之后处理..");

            }
        } catch (Throwable e) {
            e.printStackTrace();

        }
        return obj;

    }


    //@Before("execution(* com.tuling.dynamic.datasource.service.impl.*.*(..)) && @annotation(wr)")
    /*@Before("within(com.tuling.dynamic.datasource.service.impl..*) && @annotation(wr)")
    public void dynamicDataSource(JoinPoint joinPoint,WR wr) {
        String value = wr.value();
        log.info("自定义注解值："+value);
        DynamicDataSource.name.set(value);

    }*/


    @Before("execution(* com.tuling.dynamic.datasource.service.impl.*.*(..))")
    public void dynamicDataSource(JoinPoint joinPoint) {
        WR annotation = joinPoint.getTarget().getClass().getAnnotation(WR.class);
        if(annotation!=null){
            String value = annotation.value();
            DynamicDataSource.name.set(value);
            log.info("使用的数源："+value);
        }
    }

}
