package com.charl.common.service;

import com.charl.common.domin.User;
import org.junit.Assert;
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
 * @create: 2018-07-27 17:53
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class EsServiceTest {

    @Autowired
    private IEsService esService;

    @Test
    public void searchUsersTest() {
        User user = new User();
        user.setName("张三");
        List<User> users = esService.searchUsers(user);
        Assert.assertNotNull(users);
    }

}
