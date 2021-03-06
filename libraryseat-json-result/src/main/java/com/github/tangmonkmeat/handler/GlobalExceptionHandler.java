package com.github.tangmonkmeat.handler;

import com.github.tangmonkmeat.exception.BusinessException;
import com.github.tangmonkmeat.result.Result;
import com.github.tangmonkmeat.result.ResultEnum;
import com.github.tangmonkmeat.annotation.*;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

/**
 * @author zwland
 * @date 2020/11/25 20:40
 */
@ControllerAdvice
@ResultResponseBody // 这个注解必须设置，将异常响应结果进行统一统一
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * 业务异常
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleBusinessException(BusinessException ex, WebRequest request) {
        logger.error("business exception, {}", request, ex);
        ResultEnum exStatus = ex.getStatus();
        Result<?> body = Result.failure(exStatus);
        return handleExceptionInternal(ex, body, HttpStatus.BAD_REQUEST, request);
    }

    /**
     * shiro 异常
     */
    @ExceptionHandler(ShiroException.class)
    public ResponseEntity<Object> handleShiroException(ShiroException ex, WebRequest request) {
        logger.error("shiro exception, {}", request, ex);
        Throwable cause = ex.getCause();
        if (cause instanceof BusinessException){
            BusinessException bcase = (BusinessException) cause;
            return handleExceptionInternal(bcase,Result.failure(bcase.getStatus()),HttpStatus.BAD_REQUEST,request);
        }
        if (ex instanceof UnauthorizedException){
            return handleExceptionInternal(ex,Result.failure(ResultEnum.PERMISSION_UNAUTHORISE),HttpStatus.UNAUTHORIZED,request);
        }
        if (ex instanceof AuthenticationException){
            return handleExceptionInternal(ex,Result.failure(ResultEnum.PERMISSION_TOKEN_INVALID),HttpStatus.BAD_REQUEST,request);
        }
        return handleExceptionInternal(ex, Result.failure(),  HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    /**
     * 未知异常
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUnknownException(Exception ex, WebRequest request) {
        Throwable cause = ex.getCause();
        if(cause instanceof BusinessException){
            BusinessException e = (BusinessException) cause;
            return handleBusinessException(e,request);
        }
        logger.error("Unknown exception, {}", request, ex);
        Result<?> body = Result.failure();
        return handleExceptionInternal(ex, body,  HttpStatus.INTERNAL_SERVER_ERROR, request);
    }


    /**
     * org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler#handleExceptionInternal(java.lang.Exception, java.lang.Object, org.springframework.http.HttpHeaders, org.springframework.http.HttpStatus, org.springframework.web.context.request.WebRequest)
     * <p>
     * A single place to customize the response body of all exception types.
     * <p>The default implementation sets the {@link org.springframework.web.util.WebUtils#ERROR_EXCEPTION_ATTRIBUTE}
     * request attribute and creates a {@link ResponseEntity} from the given
     * body, headers, and status.
     */
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Result<?> body,  HttpStatus status, WebRequest request) {
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, 0);
        }

        return new ResponseEntity<>(body,status);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ResponseEntity<Object> responseEntity = super.handleExceptionInternal(ex, body, headers, status, request);
        HttpStatus statusCode = responseEntity.getStatusCode();
        return new ResponseEntity<>(Result.failure(statusCode),headers,statusCode);
    }
}
