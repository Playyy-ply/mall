package com.dasanxia.mall.dao;

import com.dasanxia.mall.MallApplicationTests;
import com.dasanxia.mall.pojo.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class CategoryMapperTest extends MallApplicationTests {
    @Autowired(required = false)//不添加false会红线描错
    private CategoryMapper categoryMapper;
/*    @Test
    void findById() {
        Category category = categoryMapper.findById(100001);
        System.out.println(category.toString());
    }
    @Test
    void queryById(){
        Category category = categoryMapper.queryById(100001);
        System.out.println(category.toString());
    }*/
}