package com.tuling.dynamic.datasource.service;

import com.ryan.test.MessageType;
import com.tuling.dynamic.datasource.annoAop.LogMessage;
import com.tuling.dynamic.datasource.annoAop.Person;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author liuzongshuai
 * @date 2023/1/29 14:25
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
class PersonServiceTest {

    @Autowired
    private ApplicationContext context;

    @Autowired
    PersonService personService;

    private Map<Integer, String> msgTypeMap = new ConcurrentHashMap<>();

    @Test
    void personTest() throws Exception {
        Person person = new Person();
        person.setName("zshuai");
        person.setAddress("qingdao");
        personService.check(person);
        log.info("end----------");
    }


    @Test
    void messageTypeTest() {
        log.info("scan consumeer...");
        this.context.getBeansOfType(MessageType.class).forEach((k, v) -> {
            Class<? extends MessageType> cls = v.getClass();
            MessageType annotation = cls.getAnnotation(MessageType.class);
            log.info("message+" + k + ", " + v.getClass().getName() + "load successful,messageType" + annotation.type());
            msgTypeMap.put(annotation.type(), cls.getName());

        });
    }

    @Test
    void messageTypeTest2() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        log.info("scan consumeer...");
        LogMessage logMessage = new LogMessage();
        logMessage.setActionName("logAction");
        logMessage.setModuleName("logModule");
        MessageType annotation = logMessage.getClass().getAnnotation(MessageType.class);
        log.info("类型："+annotation.type());

       /* Field[] declaredFields = log.getClass().getDeclaredFields();
                                       declaredFields[0].getAnnotation()
        log.getClass().getMethod("run",String.class).getAnnotation()
        log.getClass().getMethod("run",String.class).invoke(logMessage,"2345");
        */
    }

    @Test
    public void f5(){
        long l = System.nanoTime();

    }


}