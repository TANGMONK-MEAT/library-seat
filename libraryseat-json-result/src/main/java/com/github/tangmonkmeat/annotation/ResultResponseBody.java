package com.github.tangmonkmeat.annotation;

import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.annotation.*;

/**
 * 统一响应结果注解
 *
 * @author zwl
 * @date 2020/11/25 20:44
 */
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ResponseBody
public @interface ResultResponseBody {

}
