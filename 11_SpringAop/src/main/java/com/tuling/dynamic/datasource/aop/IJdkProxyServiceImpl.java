package com.tuling.dynamic.datasource.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author liuzongshuai
 * @date 2023/1/29 10:36
 * 自定实现类*
 */
@Service
@Slf4j
public class IJdkProxyServiceImpl implements IJdkProxyService {
    @Override
    public void doMethod1() {
        log.info("JdkProxyServiceImpl.doMethod1()");
    }

    @Override
    public String doMethod2() {
        log.info("JdkProxyServiceImpl.doMethod2()");
        return "hello world";
    }

    @Override
    public String doMethod3() throws Exception {
        log.info("JdkProxyServiceImpl.doMethod3()");
        throw new Exception("some exception");

    }
}
