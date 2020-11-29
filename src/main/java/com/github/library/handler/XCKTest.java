package com.github.library.handler;

import com.github.library.entity.User;
import com.github.library.mapper.UserMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
