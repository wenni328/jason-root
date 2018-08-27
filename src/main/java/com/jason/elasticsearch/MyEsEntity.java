package com.jason.elasticsearch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.Id;

/**
 * @auther: xieyong
 * @date: 2018/8/18 16:06
 * @Description:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "myes", type = "info")
public class MyEsEntity {
    /**
     * id(需要添加@Id注解,或会自动识别名称为id的字段为id,其余字段没有限制)
     */
    @Id
    private Integer id;
    /**
     * 名称
     */
    private String name;
    /**
     *
     */
    private String sex;
    /**
     * 年龄
     */
    private Integer age;
}
