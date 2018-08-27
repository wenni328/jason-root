package com.jason.elasticsearch;

import java.util.List;

/**
 * @auther: xieyong
 * @date: 2018/8/18 16:11
 * @Description:
 */
public interface EsTestService {

    void add(MyEsEntity myEsEntity);

    /**
     * 查询
     *
     * @return
     */
    List<MyEsEntity> select();
}
