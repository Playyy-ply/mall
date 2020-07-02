package com.dasanxia.mall.service;

import com.dasanxia.mall.MallApplicationTests;
import com.dasanxia.mall.enums.ResponseEnum;
import com.dasanxia.mall.pojo.Category;
import com.dasanxia.mall.vo.CategoryVo;
import com.dasanxia.mall.vo.ResponseVo;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class ICateogoryServiceTest extends MallApplicationTests {

    @Autowired
    private  ICateogoryService cateogoryService;
    @Test
    public void selectAll() {
        ResponseVo<List<CategoryVo>> responseVo = cateogoryService.selectAll();
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(),responseVo.getStatus());
    }
}