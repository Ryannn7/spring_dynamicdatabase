package com.tuling.dynamic.datasource.aop;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author liuzongshuai
 * @date 2023/1/29 10:53
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
class IJdkProxyServiceImplTest {

    @Autowired
    private IJdkProxyService iJdkProxyService;

    @Test
    void doMethod1() {
        iJdkProxyService.doMethod1();
    }

    @Test
    void doMethod2() {
        iJdkProxyService.doMethod2();
    }

    @Test
    void doMethod3() throws Exception {
        iJdkProxyService.doMethod3();
    }
}