package com.dasanxia.mall.controller;

import com.dasanxia.mall.enums.ResponseEnum;
import com.dasanxia.mall.form.UserForm;
import com.dasanxia.mall.pojo.User;
import com.dasanxia.mall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

import static com.dasanxia.mall.enums.ResponseEnum.PARAM_ERROR;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @PostMapping("/register")
    //@Valid 配合BindingResult 验证userForm中的注解是否符合要求 比如NOTNULL
    //若不添加Valid则只会跳转到最后一个return
    public ResponseVo register(@Valid @RequestBody UserForm userForm,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("注册提交的参数有误,{} {}",
                    Objects.requireNonNull(bindingResult.getFieldError()).getField(),//提取出错误的字段(如username,password...)
                    bindingResult.getFieldError().getDefaultMessage());//defaultmessage为“不能为空”
            return ResponseVo.error(PARAM_ERROR, bindingResult);
        }
        log.info("username={}", userForm.getUsername());
        //return ResponseVo.success();
        return ResponseVo.error(ResponseEnum.NEED_LOGIN);
    }
}
