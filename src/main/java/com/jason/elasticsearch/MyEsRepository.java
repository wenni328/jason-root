package com.jason.elasticsearch;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @auther: xieyong
 * @date: 2018/8/18 16:08
 * @Description:
 */
@Repository
public interface MyEsRepository extends ElasticsearchRepository<MyEsEntity, Integer> {
}
