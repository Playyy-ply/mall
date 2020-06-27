package com.dasanxia.mall.service.impl;


import com.dasanxia.mall.MallApplicationTests;
import com.dasanxia.mall.enums.RoleEnum;
import com.dasanxia.mall.pojo.User;
import com.dasanxia.mall.service.IUserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;
//该注解可以回溯，用在test可以防止测试时注册一个用户在数据库中
@Transactional
public class UserServiceImplTest extends MallApplicationTests {
    @Autowired
    private IUserService userService;
    @Test
    public void register() {
        User user = new User("peng","123456","782527347@qq.com", RoleEnum.CUSTOM.getCode());
        userService.register(user);
    }
}