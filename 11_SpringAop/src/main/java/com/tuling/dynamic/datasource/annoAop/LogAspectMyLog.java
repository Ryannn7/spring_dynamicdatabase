package com.tuling.dynamic.datasource.annoAop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author liuzongshuai
 * @date 2023/1/29 11:34
 */
@Aspect
@Component
@Slf4j
public class LogAspectMyLog {

    //定义一个切点
    @Pointcut("@annotation(com.ryan.test.LogAop)")
    private void pointCutMethod(){

    }

    //织入环绕通知
    @Around("pointCutMethod()")
    public Object logAround1(ProceedingJoinPoint pjp) throws Throwable {
        log.info("-----------------------------------");
        log.info("环绕通知，进入方法,logAround1");
        Object o = pjp.proceed();
        log.info("环绕通知，退出方法,logAround1");
        return o;
    }


    //织入环绕通知
    @Around("pointCutMethod()")
    public void logAround2(ProceedingJoinPoint joinPoint) throws Throwable {
        //获取方法名称
        String name = joinPoint.getSignature().getName();
        //获取入参
        Object[] param = joinPoint.getArgs();
        StringBuilder sb = new StringBuilder();
        for (Object o :
                param) {
            sb.append(o+";");
        }
        log.info("进入方法："+name+", 参数为："+sb.toString());
        //继续执行方法
        Object proceed = joinPoint.proceed();
        log.info(name +"方法执行结束");
    }


    //织入环绕通知
    @Around("pointCutMethod()")
    public void logAround3(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        StringBuilder sb = new StringBuilder();
        for (Object o :
                args) {
            sb.append(o);

        }
        log.info(methodName +"方法执行结束,参数："+sb.toString());
    }

}
