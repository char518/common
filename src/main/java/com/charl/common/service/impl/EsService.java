package com.charl.common.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.charl.common.domin.User;
import com.charl.common.service.IEsService;
import com.charl.common.config.ElasticsearchUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EsService implements IEsService {

    @Autowired
    private ElasticsearchUtils elasticsearchUtils;

    @Override
    public int addDocument(User user) {
        String s1 = JSONObject.toJSONString(user);
        String s = elasticsearchUtils.addData(JSONObject.parseObject(s1), "user", "admin", user.getId() + "");
        System.out.println(s);
        return 1;
    }

    @Override
    public int createIndex(String index) {
        boolean user = elasticsearchUtils.createIndex(index);
        return user ? 1 : 0;
    }
}
