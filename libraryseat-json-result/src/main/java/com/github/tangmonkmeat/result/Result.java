package com.github.tangmonkmeat.result;

import com.sun.istack.internal.NotNull;
import org.springframework.http.HttpStatus;

/**
 * @author zwl
 * @date 2020/11/25 20:15
 */
public class Result<T> {

    /**
     * 状态码
     */
    private int code;

    /**
     * 描述
     */
    private String msg;

    /**
     * 具体数据
     */
    private T data;

    public Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(ResultEnum status, T data) {
        this.code = status.getCode();
        this.msg = status.getMsg();
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * 业务成功返回业务代码和描述信息
     */
    public static Result<Object> success() {
        return new Result<Object>(ResultEnum.SUCCESS, null);
    }

    /**
     * 业务成功返回业务代码,描述和返回的参数
     */
    public static <T> Result<T> success(T data) {
        return new Result<T>(ResultEnum.SUCCESS, data);
    }

    /**
     * 业务成功返回业务代码,描述和返回的参数
     */
    public static <T> Result<T> success(ResultEnum status, T data) {
        if (status == null) {
            return success(data);
        }
        return new Result<T>(status, data);
    }

    /**
     * 业务异常返回业务代码和描述信息
     */
    public static <T> Result<T> failure() {
        return new Result<T>(ResultEnum.INTERNAL_SERVER_ERROR, null);
    }

    /**
     * 业务异常返回业务代码,描述和返回的参数
     */
    public static <T> Result<T> failure(ResultEnum status) {
        return failure(status, null);
    }

    /**
     * 业务异常返回业务代码,描述和返回的参数
     */
    public static <T> Result<T> failure(ResultEnum status, T data) {
        if (status == null) {
            return new Result<T>(ResultEnum.INTERNAL_SERVER_ERROR, null);
        }
        return new Result<T>(status, data);
    }

    public static <T> Result<T> failure(@NotNull  HttpStatus httpStatus){
        return new Result<T>(httpStatus.value(),httpStatus.getReasonPhrase(),null);
    }
}
