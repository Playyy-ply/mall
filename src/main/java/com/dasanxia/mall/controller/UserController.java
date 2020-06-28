package com.dasanxia.mall.controller;

import com.dasanxia.mall.consts.MallConst;
import com.dasanxia.mall.enums.ResponseEnum;
import com.dasanxia.mall.form.UserLoginForm;
import com.dasanxia.mall.form.UserRegisterForm;
import com.dasanxia.mall.pojo.User;
import com.dasanxia.mall.service.IUserService;
import com.dasanxia.mall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Objects;

import static com.dasanxia.mall.consts.MallConst.CURRENT_USER;
import static com.dasanxia.mall.enums.ResponseEnum.*;

@RestController
@Slf4j
public class UserController {

    @Autowired
    private IUserService userService;
    @PostMapping("/user/register")
    //@Valid 配合BindingResult 验证userForm中的注解是否符合要求 比如NOTNULL
    //若不添加Valid则只会跳转到最后一个return
    public ResponseVo<User> register(@Valid @RequestBody UserRegisterForm userRegisterForm,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("注册提交的参数有误,{} {}",
                    Objects.requireNonNull(bindingResult.getFieldError()).getField(),//提取出错误的字段(如username,password...)
                    bindingResult.getFieldError().getDefaultMessage());//defaultmessage为“不能为空”
            return ResponseVo.error(PARAM_ERROR, bindingResult);
        }
        User user = new User();
        BeanUtils.copyProperties(userRegisterForm,user);
        return userService.register(user);
    }
    @PostMapping("/user/login")
    public ResponseVo<User> login(@Valid @RequestBody UserLoginForm userLoginForm,
                                  BindingResult bindingResult,
                                  HttpSession session){
        if(bindingResult.hasErrors()){
            return ResponseVo.error(PARAM_ERROR,bindingResult);
        }
        ResponseVo<User> userResponseVo = userService.login(userLoginForm.getUsername(),userLoginForm.getPassword());
        //设置session
        session.setAttribute(CURRENT_USER,userResponseVo.getData());
        return userResponseVo;
    }

    //session保存在内存里，之后token+redis
    @GetMapping("/user")
    public ResponseVo<User> userInfo(HttpSession session){
        log.info("/user sessionId={}",session.getId());
        User user = (User)session.getAttribute(CURRENT_USER);

        return ResponseVo.success(user);
    }
    @PostMapping("/user/logout")
    public ResponseVo<User> logout(HttpSession session){
        log.info("/user/logout sessionId={}",session.getId());
        User user = (User)session.getAttribute(CURRENT_USER);

        session.removeAttribute(CURRENT_USER);
        return ResponseVo.success();
    }
}
