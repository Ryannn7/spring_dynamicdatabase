package com.tuling.dynamic.datasource.cglib;

import com.tuling.dynamic.datasource.annoAop.Person;
import com.tuling.dynamic.datasource.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author liuzongshuai
 * @date 2023/1/29 11:04
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
class CglibProxyServiceImplTest {

    @Autowired
    private CglibProxyServiceImpl cglibProxyService;



    @Test
    void doMethod1() {
        cglibProxyService.doMethod1();
    }


    @Test
    void doMethod2() {
        cglibProxyService.doMethod2();
    }

    @Test
    void doMethod3() throws Exception {
        cglibProxyService.doMethod3();
    }





}