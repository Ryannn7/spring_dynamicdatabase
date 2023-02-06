package com.tuling.dynamic.datasource.service.impl;

import com.tuling.dynamic.datasource.config.WR;
import com.tuling.dynamic.datasource.entity.Frend;
import com.tuling.dynamic.datasource.mapper.FrendMapper;
import com.tuling.dynamic.datasource.service.FrendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 */
@Service
@WR("W")
public class FrendServiceImpl implements FrendService {

    @Autowired
    FrendMapper frendMapper;


    @Override         // 库2
    public List<Frend> list() {
        return frendMapper.list();
    }

    @Override        // 库1
    public void save(Frend frend) {
        frendMapper.save(frend);
    }

}
