package com.charl.common.service;

import com.charl.common.domin.User;

public interface IEsService {

    int addDocument(User user);

    int createIndex(String index);

}
