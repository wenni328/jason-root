package com.jason.elasticsearch;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @auther: xieyong
 * @date: 2018/8/23 16:56
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class EsTestServiceImplTest {
@Autowired
private EsTestService esTestService;
    @Test
    public void add() {
        //创建类
        MyEsEntity myEsEntity = new MyEsEntity();
        myEsEntity.setId(343);
        //为了后期显示出ik分词器的效果，名字起了一个商品的名称
        myEsEntity.setName("五常大米");
        myEsEntity.setSex("man");
        myEsEntity.setAge(11);
        esTestService.add(myEsEntity);
    }

    @Test
    public void select() {
        esTestService.select().forEach(o->{
         log.info("查询结果：{}",o.getName());
        });
    }
}