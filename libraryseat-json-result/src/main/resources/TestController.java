import com.github.tangmonkmeat.entity.Seat;
import com.github.tangmonkmeat.exception.BusinessException;

import com.github.tangmonkmeat.annotation.*;
import com.github.tangmonkmeat.mapper.SeatMapper;
import com.github.tangmonkmeat.result.Result;
import com.github.tangmonkmeat.result.ResultEnum;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

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

    @Resource
    SeatMapper seatMapper;

    @GetMapping("/or")
    public Object or(){
        Seat seat = new Seat();
        return seatMapper.findOneSelective(seat);
    }

    @RequestMapping("/json")
    public Result<Integer> test(){
        return new Result<>(ResultEnum.RESULT_DATA_NONE.getCode(),ResultEnum.RESULT_DATA_NONE.getMsg(),12);
    }

    @GetMapping("ex1")
    public Object businessExceptionTest(){
        int a = 1 / 0;
        return null;
    }

    @GetMapping("/ex2")
    public Object exceptionTest(){
        throw new BusinessException();
    }

    @GetMapping(value = "str")
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
