package com.dasanxia.mall.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
@Data
public class UserForm {

    @NotBlank(message = "用户名不能为空")//不允许String有空格
    @NotNull//不允许为空
    private String username;
    @NotBlank
    @NotNull
    private String password;
    @NotBlank
    @NotNull
    private String email;
}
