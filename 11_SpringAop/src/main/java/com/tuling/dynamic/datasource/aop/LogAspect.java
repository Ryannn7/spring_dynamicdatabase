package com.tuling.dynamic.datasource.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * @author liuzongshuai
 * @date 2023/1/29 10:37
 * 3，定义切面*
 */
@EnableAspectJAutoProxy
@Component
@Aspect
@Slf4j
public class LogAspect {

    /**
     * 定义切点*
     */
    @Pointcut("execution( * com.tuling.dynamic.datasource.aop.*.*(..) )")
    private void pointCutMethod(){

    }

    @Around("pointCutMethod()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        log.info("-----------------------------------");
        log.info("环绕通知，进入方法");
        Object o = pjp.proceed();
        log.info("环绕通知，退出方法");
        return o;
    }

    @Before("pointCutMethod()")
    public void doBefore(){
        log.info("前置通知");
    }

    /**
     *后置通知
     * @param result
     */
    @AfterReturning( pointcut="pointCutMethod()",returning="result")
    public void doAfterReturing(String result){
        log.info("后置通知"+result);
    }

    /**
     * 异常通知*
     */
    @AfterThrowing( pointcut="pointCutMethod()",throwing = "e")
    public void doAfterThrowing(Exception e){
        log.info("异常通知"+e.getMessage());

    }

    /**最终通知
     * *
     */
    @After("pointCutMethod()")
    public void aoAfter(){
        log.info("最终通知");

    }

}
