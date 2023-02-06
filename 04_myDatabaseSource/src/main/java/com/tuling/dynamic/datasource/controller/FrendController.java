package com.tuling.dynamic.datasource.controller;

import com.tuling.dynamic.datasource.entity.Frend;
import com.tuling.dynamic.datasource.service.FrendReadService;
import com.tuling.dynamic.datasource.service.FrendWriteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/***
 * @Author 徐庶   QQ:1092002729
 * @Slogan 致敬大师，致敬未来的你
 */
@RestController
@RequestMapping("frend")
@Slf4j
public class FrendController {

    @Autowired
    private FrendWriteService frendWriteService;

    @Autowired
    private FrendReadService frendReadService;




    /**
     * 注解切换
     * */
    @GetMapping(value = "selectR")
    public List<Frend> selectR(){

        return frendReadService.list();
    }

    @GetMapping(value = "insertW")
    public void insertW(){
        Frend frend = new Frend();
        frend.setName("徐庶");
        frendWriteService.save(frend);
    }



}
