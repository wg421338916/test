package com.wanggang.springbootstudy.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @program: test
 * @description:
 * @author: Mr.WG
 * @create: 2019-04-22 13:47
 **/
public class User {
    @NotBlank
    @Pattern(
            regexp = "[0-9a-zA-Z_.-]+",
            message = "name格式错误"
    )
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
