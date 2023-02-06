package com.tuling.dynamic.datasource.service;

import com.ryan.test.Range;
import com.tuling.dynamic.datasource.annoAop.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;

/**
 * @author liuzongshuai
 * @date 2023/1/29 14:17
 */
@Slf4j
@Service
public class PersonService {

    public void check(Person person) throws IllegalAccessException {
        for (Field field :
                person.getClass().getFields()) {
            Range range = field.getAnnotation(Range.class);
            if(range!=null){
                //获取当前字段的值
                Object value = field.get(person);
                if(value instanceof String){
                    String s = (String) value;
                    if(s.length()<range.min()||s.length()>range.max()){
                        log.info("字段的大小值有异常字段值："+value+"长度："+s.length()+"最小值："+range.min()+"最大值："+range.max());
                        throw new RuntimeException("字段的大小值有异常");
                    }else{
                        log.info("字段的大小正常，字段值："+value+"长度："+s.length()+"最小值："+range.min()+"最大值："+range.max());
                    }
                }
            }
        }
    }
}
