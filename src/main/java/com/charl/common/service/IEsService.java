package com.charl.common.service;

import com.charl.common.domin.User;

import java.util.List;

public interface IEsService {

    int addDocument(User user);

    int createIndex(String index);

    List<User> searchUsers(User user);

    List<User> searchUserList(User user);

}
