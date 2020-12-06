package com.github.tangmonkmeat.annotation;

import java.lang.annotation.*;


/**
 * 忽略 jwt 校验的注解，只可以标注在方法上
 *
 * @author zwl
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JwtIgnore {
}
