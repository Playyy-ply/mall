package com.dasanxia.mall.service.impl;


import com.dasanxia.mall.MallApplicationTests;
import com.dasanxia.mall.pojo.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class UserServiceImplTest extends MallApplicationTests {
    @Autowired
    private UserServiceImpl userService;
    @Test
    public void register() {
        User user = new User("peng","123456","782527347@qq.com");
        userService.register(user);
    }
}