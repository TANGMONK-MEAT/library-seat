package com.github.tangmonkmeat.vo;

/**
 * 接收登录信息
 *
 * @author zwl
 * @date 2020/12/3 22:23
 */
public class LoginUserVO {

    /**
     * 用户名
     *
     */
    private String username;

    /**
     * 密码
     *
     */
    private String password;

    /**
     * 验证码
     *
     */
    private String code;

    @Override
    public String toString() {
        return "LoginUserVO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", code='" + code + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
