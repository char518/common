package com.charl.common.service;

import com.charl.common.domin.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @program: common
 * @description:
 * @author: charl
 * @create: 2018-07-03 16:07
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
@SuppressWarnings("uncheck")
public class UserServiceTest {

    @Autowired
    private IUserService userService;

    @Autowired
    private IEsService esService;

    @Test
    public void addUserTest() {
        User user = new User();
        user.setAge(20);
        user.setName("李四");
        user.setMobile("18888888889");
        user.setImage("www.baidu.com");
        user.setDesc("个人的一些介绍");
        int i = userService.addUser(user);
    }

    @Test
    public void addDocumentTest() {
        User user = new User();
        user.setAge(25);
        user.setName("Json");
        user.setMobile("18888888667");
        user.setImage("www.charl.com");
        user.setDesc("个人的介绍");
        user.setId("123123");
        int i = esService.addDocument(user);
        System.out.println(i);
    }

    @Test
    public void createIndexTest() {
        int l = esService.createIndex("hello");
        System.out.println(l);
    }

    @Test
    public void searchUsersTest() {
        User user = new User();
        user.setName("Json");
        List<User> users = esService.searchUsers(user);
        System.out.println(users);
    }

}
