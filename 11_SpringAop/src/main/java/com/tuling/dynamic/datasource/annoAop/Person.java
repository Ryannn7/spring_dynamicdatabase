package com.tuling.dynamic.datasource.annoAop;

import com.ryan.test.Range;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;

/**
 * @author liuzongshuai
 * @date 2023/1/29 14:03
 */
@Slf4j
@Data
public class Person {
    @Range(min=3,max = 7)
   public String name;

    @Range(min=16,max = 27)
   public String address;



}
