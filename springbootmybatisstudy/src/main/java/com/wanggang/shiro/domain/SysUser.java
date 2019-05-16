package com.wanggang.shiro.domain;

import java.io.Serializable;

/**
 * @program: test
 * @description:
 * @author: Mr.WG
 * @create: 2019-05-13 14:43
 **/
public class SysUser implements Serializable {
    private String nickName;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "SysUser{" +
                "nickName='" + nickName + '\'' +
                '}';
    }
}
