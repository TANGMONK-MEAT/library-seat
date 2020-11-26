package com.github.library.handler;

import com.github.library.annotation.ResultResponseBody;
import com.github.library.result.Result;
import com.github.library.result.ResultEnum;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 测试接口异常
 *
 * @author zwl
 * @date 2020/11/25 21:08
 */
@ResultResponseBody
@Controller
@RequestMapping("/api")
public class TestController {

    @RequestMapping("/json")
    public Result<Integer> test(){
        return new Result<>(ResultEnum.DATA_NOT_EXIST.getCode(),ResultEnum.DATA_NOT_EXIST.getMsg(),12);
    }

    @GetMapping("exception")
    public Object exceptionTest(){
        int a = 1 / 0;
        //throw new BusinessException();
        return null;
    }

    @GetMapping(value = "string")
    public String testString() {
        return "sasasa";
    }

    @GetMapping(value = "/null")
    public HttpHeaders headers(){
        return new HttpHeaders();
    }

    @GetMapping(value = "param")
    public Result<String> param(@RequestParam Integer param){
        System.out.println(param);
        return Result.success("param");
    }
}
