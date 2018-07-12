package com.charl.common.es;

import com.charl.common.domin.User;
import com.charl.common.utils.JsonUtils;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @program: common
 * @description:
 * @author: charl
 * @create: 2018-07-12 09:53
 **/
@Service
public class EsService {

    @Autowired
    private RestHighLevelClient client;

    public int index() {
        User user = new User();
        user.setId(6l);
        user.setAge(20);
        user.setName("王五");
        user.setMobile("18899990876");
        user.setImage("www.baidu.com");
        user.setDesc("个人的一些介绍");
        IndexRequest request = new IndexRequest("user", "admin", user.getId() +"");
        String s = JsonUtils.obj2json(user);
        request.source(s, XContentType.JSON);
//        request.opType(DocWriteRequest.OpType.CREATE);

        IndexResponse response = null;
        try {
            response = client.index(request);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response.status().getStatus();
    }


}
