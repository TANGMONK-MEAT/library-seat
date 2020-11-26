package com.github.library.handler;

import com.github.library.annotation.ResultResponseBody;
import com.github.library.exception.BusinessException;
import com.github.library.result.Result;
import com.github.library.util.AnnotatedElementUtil;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.Method;

/**
 * @author zwl
 * @date 2020/11/25 20:52
 */
@RestControllerAdvice
public class ResultResponseBodyHandler implements ResponseBodyAdvice<Object> {

    private static final Class<? extends ResultResponseBody> ANNOTATION_TYPE = ResultResponseBody.class;

    /**
     * 判断类或者方法是否使用了 @ResponseResultBody
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        Method method = methodParameter.getMethod();
        Class<?> clazz = methodParameter.getContainingClass();
        assert method != null;
        // 判断 handler 或 handler的方法上是否标注有ResultResponseBody 注解
        return AnnotatedElementUtil.hasAnnotation(clazz,ANNOTATION_TYPE) || AnnotatedElementUtil.hasAnnotation(method,ANNOTATION_TYPE);
    }

    /**
     * 当类或者方法使用了 @ResponseResultBody 就会调用这个方法
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        // 业务异常
        if (body instanceof BusinessException){
            return Result.failure(((BusinessException) body).getStatus());
        }
        // 防止重复包裹的问题出现
        if (body instanceof Result) {
            return body;
        }
        return Result.success(body);
    }
}
