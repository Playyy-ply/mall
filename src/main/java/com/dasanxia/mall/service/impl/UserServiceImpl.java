package com.dasanxia.mall.service.impl;

import com.dasanxia.mall.dao.UserMapper;
import com.dasanxia.mall.enums.ResponseEnum;
import com.dasanxia.mall.pojo.User;
import com.dasanxia.mall.service.IUserService;
import com.dasanxia.mall.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;

import static com.dasanxia.mall.enums.ResponseEnum.*;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public ResponseVo<User> register(User user) {
        //userName,email不能重复
        int countByUsername = userMapper.countByUsername(user.getUsername());
        if(countByUsername > 0){
            return ResponseVo.error(USERNAME_EXIST);
        }
        int countByEmail = userMapper.countByEmail(user.getUsername());
        if(countByEmail > 0){
            return ResponseVo.error(EMAIL_EXIST);
        }
        user.setRole(0);
        //MD5摘要算法
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes(StandardCharsets.UTF_8)));

        //写入数据库
        if(userMapper.insertSelective(user)==0){
            return ResponseVo.error(ERROR);
        }

        return ResponseVo.success();
    }

    @Override
    public ResponseVo<User> login(String username, String password) {
        User user = userMapper.selectByUsername(username);
        if(user == null){//用户名不存在
            return ResponseVo.error(USERNAME_OR_PASSWORD_ERROR);
        }

        if(!user.getPassword().equalsIgnoreCase(//密码错误
                DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8))
        )){
            return ResponseVo.error(USERNAME_OR_PASSWORD_ERROR);
        }
        user.setPassword("");//把login中的返回的json对象秘密设为空
        return ResponseVo.success(user);
    }

}
