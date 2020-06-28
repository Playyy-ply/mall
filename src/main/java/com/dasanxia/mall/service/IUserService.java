package com.dasanxia.mall.service;

import com.dasanxia.mall.pojo.User;
import com.dasanxia.mall.vo.ResponseVo;

public interface IUserService {
    /**
     * 注册
     */
    ResponseVo register(User user);

    /**
     * 登录
     */
    ResponseVo login(String username,String password);


}
