package com.tuling.dynamic.datasource.cglib;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author liuzongshuai
 * @date 2023/1/29 11:02
 */
@Service
@Slf4j
public class CglibProxyServiceImpl {

    public void doMethod1() {
        System.out.println("JdkProxyServiceImpl.doMethod1()");
        log.info("JdkProxyServiceImpl.doMethod1()");
    }


    public String doMethod2() {
        System.out.println("JdkProxyServiceImpl.doMethod2()");
        log.info("JdkProxyServiceImpl.doMethod2()");
        return "hello world";
    }


    public String doMethod3() throws Exception {
        System.out.println("JdkProxyServiceImpl.doMethod3()");
        log.info("JdkProxyServiceImpl.doMethod3()");
        throw new Exception("some exception");

    }
}
