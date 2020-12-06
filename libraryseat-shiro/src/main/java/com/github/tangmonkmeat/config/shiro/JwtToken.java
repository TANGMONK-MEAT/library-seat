package com.github.tangmonkmeat.config.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author zwl
 * @date 2020/12/1 22:01
 */
public class JwtToken implements AuthenticationToken {

    /**
     * token 令牌
     *
     */
    private final String token;

    public JwtToken(String token){
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
