package com.github.tangmonkmeat.utils;

import java.util.Random;

/**
 * 盐值 工具类
 *
 * @author zwl
 * @date 2020/12/2 10:19
 */
public class SaltUtil {

    public static String getSalt(int size){
        char[] chars = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890!@#$%^&*?.+-/,".toCharArray();
        StringBuilder salt = new StringBuilder(size);
        char c;
        int len = chars.length;
        Random random = new Random();
        for (int i = size;i > 0;i--){
            c = chars[random.nextInt(len)];
            salt.append(c);
        }
        return salt.toString();
    }

    public static void main(String[] args) {
        String salt = getSalt(8);
        System.out.println(salt);
    }

}
