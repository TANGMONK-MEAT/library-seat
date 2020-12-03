package com.github.tangmonkmeat.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.github.tangmonkmeat.common.constant.JwtConstant;

import java.util.Arrays;
import java.util.Base64;
import java.util.Date;

/**
 * jwt 工具类
 *
 * @author zwl
 * @date 2020/12/1 23:53
 */
public class JwtUtil {

    /**
     * 校验 token 是否正确
     *
     * @param token token
     * @param username 用户名-唯一
     * @param secret 密匙
     * @return true代表校验通过，否则校验失败
     */
    public static boolean verify(String token,String username,String secret){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier jwtVerifier = JWT.require(algorithm)
                    .withClaim(JwtConstant.WITH_CLAIM_KEY,username)
                    .build();
            jwtVerifier.verify(token);
            return true;
        } catch (IllegalArgumentException | JWTVerificationException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获取 用户名-唯一
     *
     * @param token token
     * @return 存在返回
     */
    public static String getUserName(String token){
        try {
            DecodedJWT jwt = JWT.decode(token);
            Claim claim = jwt.getClaim(JwtConstant.WITH_CLAIM_KEY);
            return claim.asString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 判断过期
     *
     * @param token token
     * @return 返回true代表未过期，否则过期
     */
    public static boolean isExpire(String token){
        long currentTimeMillis = System.currentTimeMillis();
        DecodedJWT jwt = JWT.decode(token);
        return currentTimeMillis > jwt.getExpiresAt().getTime();
    }

    /**
     * 签名-{@link JwtConstant#EXPIRE_TIME 时间后过期}
     *
     * @param username username
     * @param secret 密匙
     * @return token; 否则返回null
     */
    public static String sign(String username,String secret){
        try {
            Date date = new Date(System.currentTimeMillis() + JwtConstant.EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withClaim(JwtConstant.WITH_CLAIM_KEY, username)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (IllegalArgumentException | JWTCreationException e) {
            e.printStackTrace();
        }
        return null;
    }

    //public static void main(String[] args) {
    //    DecodedJWT jwt = JWT.decode("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE2MDY5ODU3NTksInVzZXJuYW1lIjoiendsIn0.U4i8xDnIGNBsFUSPRNaEAXV3jl9-a1sNSTZcaN34Sps");
    //    Claim claim = jwt.getClaim(JwtConstant.WITH_CLAIM_KEY);
    //    System.out.println(claim.asString());
    //    System.out.println(jwt.getExpiresAt().getTime());
    //    byte[] bytes = Base64.getEncoder().encode("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9".getBytes());
    //    System.out.println(Arrays.toString(bytes));
    //}
}
