import com.github.tangmonkmeat.entity.User;
import com.github.tangmonkmeat.mapper.UserMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/xck")
public class XCKTest {
    @Resource
    UserMapper<User> userMappers;

    @RequestMapping("/mybatis")
    public void testMybatis() throws Exception {
            User user=new User();
            user.setuId(1);
            user.setCity("南昌");
            userMappers.findOfList(user);

    }
}
