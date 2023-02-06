package com.tuling.dynamic.datasource.service.impl;



import com.tuling.dynamic.datasource.entity.Frend;
import com.tuling.dynamic.datasource.mapper.r.RFrendMapper;
import com.tuling.dynamic.datasource.mapper.w.WFrendMapper;
import com.tuling.dynamic.datasource.service.FrendService;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

/***
 *
 */
@Service
public class FrendServiceImpl implements FrendService {

    @Autowired
    private WFrendMapper wFrendMapper;

    @Autowired
    private RFrendMapper rFrendMapper;

    @Autowired
    TransactionTemplate rTransactionTemplate;

    @Autowired
    TransactionTemplate wTransactionTemplate;


    @Override
    public List<Frend> list() {
        return rFrendMapper.list();
    }

    @Override
    public void save(Frend frend) {
        wFrendMapper.save(frend);
    }


    //此种方法只能回滚一个主数据库的事务，
/*    @Override
    @Transactional(transactionManager = "wTransactionManager")
    public void saveAll(Frend frend) {
        wFrendMapper.save(frend);
        rFrendMapper.save(frend);
        int i=1/0;

    }*/


    @Override
    public void saveAll(Frend frend) {

        wTransactionTemplate.execute(wstatus->{
            rTransactionTemplate.execute(rstatus->{
                try{
                    wFrendMapper.save(frend);
                    rFrendMapper.save(frend);
                    int i=1/0;
                    //r库提交
                   return  true;
                }catch (Exception e){
                    e.printStackTrace();
                    wstatus.setRollbackOnly();
                    rstatus.setRollbackOnly();
                    return  false;
                }
            });
            // 主库执行成功，提交。
               return true;
        });
    }


  /*  @Transactional(transactionManager = "wTransactionManager")
    @Override
    public void saveAll(Frend frend) {
        FrendService frendService = (FrendService) AopContext.currentProxy();
        frendService.saveAllR(frend);

    }*/

    /**
     * 2. Spring中七种Propagation类的事务属性详解：     *
     *  REQUIRED：支持当前事务，如果当前没有事务，就新建一个事务。这是最常见的选择。     *
     *  SUPPORTS：支持当前事务，如果当前没有事务，就以非事务方式执行。     *
     *  MANDATORY：支持当前事务，如果当前没有事务，就抛出异常。     *
     *  REQUIRES_NEW：新建事务，如果当前存在事务，把当前事务挂起。     *
     *  NOT_SUPPORTED：以非事务方式执行操作，如果当前存在事务，就把当前事务挂起。     *
     *  NEVER：以非事务方式执行，如果当前存在事务，则抛出异常。     *
     *  NESTED：支持当前事务，如果当前事务存在，则执行一个嵌套事务，如果当前没有事务，就新建一个事务。
     * ————————————————
     * spring 事务：https://blog.csdn.net/sayoko06/article/details/79164858
     */
    @Transactional(transactionManager = "rTransactionManager",
            propagation = Propagation.REQUIRES_NEW) //新建事务，如果当前存在事务，把当前事务挂起。
    @Override
    public void saveAllR(Frend frend) {
        wFrendMapper.save(frend);
        rFrendMapper.save(frend);
       // int i = 1 / 0;
        return;
    }


    @Override
    @Transactional(transactionManager = "wTransactionManager")
    public void saveA(Frend frend) {
        wFrendMapper.save(frend);
        Frend frendB = new Frend();
        frendB.setName("徐庶B");
        FrendService frendService = (FrendService) AopContext.currentProxy();
        frendService.saveB(frendB);
    }

    @Override
    @Transactional(transactionManager = "rTransactionManager", propagation = Propagation.REQUIRES_NEW)
    public void saveB(Frend frend) {
        rFrendMapper.save(frend);
        int i=1/0;
        System.out.println("==========end=====");

    }

}
