package com.github.tangmonkmeat.handler;

import com.github.tangmonkmeat.annotation.*;
import com.github.tangmonkmeat.result.Result;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 处理 404 错误
 * @author zwl
 * @date 2020/11/27 10:32
 */
@Controller
@ResultResponseBody
public class Default404ErrorHandler implements ErrorController {


    private final static String ERROR_PATH = "/error";

    /**
     * 处理　404 错误
     */
    @RequestMapping(value = ERROR_PATH)
    public Result<Object> handle404Error(HttpServletRequest request){
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if(statusCode == HttpStatus.NOT_FOUND.value()){
            return Result.failure(HttpStatus.NOT_FOUND);
        }
        return null;
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
