package com.tuling.dynamic.datasource.service.impl;

import com.tuling.dynamic.datasource.config.WR;
import com.tuling.dynamic.datasource.entity.Frend;
import com.tuling.dynamic.datasource.mapper.FrendMapper;
import com.tuling.dynamic.datasource.service.FrendReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liuzongshuai
 * @date 2023/2/1 18:20
 */
@Service
@WR("R")
public class FrendReadServiceImpl implements FrendReadService {

    @Autowired
    FrendMapper frendMapper;


    @Override
    public List<Frend> list() {
        return frendMapper.list();
    }

}
