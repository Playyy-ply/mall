package com.dasanxia.mall.service.impl;

import com.dasanxia.mall.dao.UserMapper;
import com.dasanxia.mall.pojo.User;
import com.dasanxia.mall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public void register(User user) {
        //userName,email不能重复
        int countByUsername = userMapper.countByUsername(user.getUsername());
        if(countByUsername > 0){
            throw new RuntimeException("这个username已经被注册过");
        }
        int countByEmail = userMapper.countByEmail(user.getUsername());
        if(countByEmail > 0){
            throw new RuntimeException("这个email已经被注册过");
        }

        //MD5摘要算法
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes(StandardCharsets.UTF_8)));

        //写入数据库
        if(userMapper.insertSelective(user)==0){
            throw new RuntimeException("注册失败");
        }
    }
}
