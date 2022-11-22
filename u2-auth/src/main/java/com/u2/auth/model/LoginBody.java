package com.u2.auth.model;

/**
 * 人员登录对象
 *
 * @author vhans
 */
public class LoginBody {
    /**
     * 账号
     */
    private String name;

    /**
     * 用户密码
     */
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
