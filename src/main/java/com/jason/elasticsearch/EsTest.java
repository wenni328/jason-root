package com.jason.elasticsearch;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @auther: xieyong
 * @date: 2018/8/18 16:13
 * @Description:
 */
@RestController
@RequestMapping(value = "/es")
public class EsTest {

    @Resource
    EsTestService esTestService;

    @RequestMapping(value = "/add")
    public void add() {
        //创建类
        MyEsEntity myEsEntity = new MyEsEntity();
        myEsEntity.setId(2);
        //为了后期显示出ik分词器的效果，名字起了一个商品的名称
        myEsEntity.setName("五常大米");
        myEsEntity.setSex("man");
        myEsEntity.setAge(18);
        esTestService.add(myEsEntity);
    }

    @RequestMapping(value = "/query")
    public List<MyEsEntity> select() {
        return esTestService.select();
    }
}
