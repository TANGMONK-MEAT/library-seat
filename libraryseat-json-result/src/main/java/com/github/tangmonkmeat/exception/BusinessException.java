package com.github.tangmonkmeat.exception;


import com.github.tangmonkmeat.result.ResultEnum;

/**
 * 业务异常
 * @author zwl
 * @date 2020/11/25 20:37
 */
public class BusinessException extends RuntimeException{

    /**
     * 响应状态码枚举
     */
    private ResultEnum status;

    public ResultEnum getStatus() {
        return status;
    }

    public BusinessException(ResultEnum status){
        this.status = status;
    }

    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    protected BusinessException(String message, Throwable cause,
                               boolean enableSuppression,
                               boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
