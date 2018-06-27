package com.charl.common.service;

import com.charl.common.domin.User;

import java.util.List;

/**
 * @program: common
 * @description:
 * @author: charl
 * @create: 2018-06-26 14:32
 **/
public interface IUserService {

    int addUser(User user);

    int updateUser(User user);

    User queryUserById(Long id);

    List<User> queryUsers();

}
