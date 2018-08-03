package com.charl.common.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.charl.common.config.ElasticsearchUtils;
import com.charl.common.dao.ElasticSearchRestClientDao;
import com.charl.common.domin.User;
import com.charl.common.service.IEsService;
import com.charl.common.utils.JsonUtils;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EsService implements IEsService {

    @Autowired
    private ElasticsearchUtils elasticsearchUtils;


    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Autowired
    private ElasticSearchRestClientDao restClientDao;

    @Override
    public int addDocument(User user) {
        String s1 = JSONObject.toJSONString(user);
        String s = elasticsearchUtils.addData(JSONObject.parseObject(s1), "user", "admin");
        System.out.println(s);
        return 1;
    }

    @Override
    public int createIndex(String index) {
        boolean user = elasticsearchUtils.createIndex(index);
        return user ? 1 : 0;
    }

    @Override
    public List<User> searchUsers(User user) {
        List<Map<String, Object>> maps = elasticsearchUtils.searchListData("user", "admin", "name,age,mobile,image,email", "name=" + user.getName());
        String s = JsonUtils.obj2json(maps);
        List<User> users = JsonUtils.json2list(s, User.class);
        return users;
    }

    public List<User> searchUserList(User user) {
        List<User> user1 = restClientDao.queryAll("user", User.class, 3);
        return user1;
    }

}
