package com.tuling.dynamic.datasource.controller;

import com.tuling.dynamic.datasource.entity.Frend;
import com.tuling.dynamic.datasource.service.FrendService;
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
    private FrendService frendService;

    /**
     * 注解切换
     * */
    @GetMapping(value = "selectR")
    public List<Frend> selectR(){

        return frendService.list();
    }

    @GetMapping(value = "insertW")
    public void insertW(){
        Frend frend = new Frend();
        frend.setName("徐庶");
        frendService.save(frend);
    }

    @GetMapping(value = "insertAll")
    public void insertAll(){
        Frend frend = new Frend();
        frend.setName("徐庶");
        frendService.saveAll(frend);
    }


    @GetMapping(value = "insertAB")
    public void insertAB(){
        Frend frend = new Frend();
        frend.setName("徐庶A");
        frendService.saveA(frend);
    }



}
