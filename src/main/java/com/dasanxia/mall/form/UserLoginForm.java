package com.dasanxia.mall.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
@Data
public class UserLoginForm {
    @NotBlank(message = "用户名不能为空")//不允许String有空格
    private String username;
    @NotBlank
    private String password;
}
