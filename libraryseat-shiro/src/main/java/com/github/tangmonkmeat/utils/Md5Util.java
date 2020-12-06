package com.github.tangmonkmeat.utils;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * MD5 加密
 *
 * @author zwl
 * @date 2020/12/1 23:40
 */
public class Md5Util {

    /**
     * 密码加密
     *
     * @param password 密码
     * @param salt 盐
     * @return MD5（密码+盐）
     */
    public static String md5Encryption(final String password,final String salt){
        final int hashIterations = 996;
        Md5Hash result = new Md5Hash(password.getBytes(),salt,hashIterations);
        return result.toHex();
    }

}
