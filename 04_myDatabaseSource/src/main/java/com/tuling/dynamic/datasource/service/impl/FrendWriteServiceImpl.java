package com.tuling.dynamic.datasource.service.impl;



import com.tuling.dynamic.datasource.config.WR;
import com.tuling.dynamic.datasource.entity.Frend;
import com.tuling.dynamic.datasource.mapper.FrendMapper;
import com.tuling.dynamic.datasource.service.FrendWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/***
 *
 */
@Service
@WR("W")
public class FrendWriteServiceImpl implements FrendWriteService {

    @Autowired
    FrendMapper frendMapper;



    @Override
    public void save(Frend frend) {
        frendMapper.save(frend);

    }
}
