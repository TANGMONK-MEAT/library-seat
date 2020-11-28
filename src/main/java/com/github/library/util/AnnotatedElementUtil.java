package com.github.library.util;

import com.github.library.annotation.ResultResponseBody;
import com.sun.istack.internal.NotNull;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * 注解辅助类
 *
 * @author zwl
 * @date 2020/11/25 21:20
 */
public class AnnotatedElementUtil {

    /**
     * 判断 指定类上是否标注有 指定注解
     *
     * @param clazz 指定类的Class实例
     * @param aClazz 指定注解的Class实例
     * @return 存在返回true；否则返回false
     */
    public static boolean hasAnnotation(@NotNull Class<?> clazz,@NotNull Class<? extends Annotation> aClazz){
        return clazz.isAnnotationPresent(aClazz);
    }

    /**
     * 判断 指定方法上是否标注有 指定注解
     *
     * @param method 指定方法的Method实例
     * @param aClazz 指定注解的Class实例
     * @return 存在返回true；否则返回false
     */
    public static boolean hasAnnotation(@NotNull Method method, @NotNull Class<? extends Annotation> aClazz){
        return method.isAnnotationPresent(aClazz);
    }
}
