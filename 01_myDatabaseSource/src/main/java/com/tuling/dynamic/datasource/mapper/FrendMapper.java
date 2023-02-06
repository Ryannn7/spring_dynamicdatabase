package com.tuling.dynamic.datasource.mapper;


import com.tuling.dynamic.datasource.entity.Frend;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Auther: wangyi
 * @Date: 2020/12/12 01:16
 * @Description: 
 */
public interface FrendMapper {
    @Select("SELECT * FROM friend")
    List<Frend> list();

    @Insert("INSERT INTO  friend(`name`) VALUES (#{name})")
    void save(Frend frend);
}