package com.charl.common.es;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.common.xcontent.XContentType;

/**
 * @program: common
 * @description:
 * @author: charl
 * @create: 2018-07-11 15:11
 **/
public class ElasticDemo {

    public static void main(String[] args) {
        IndexRequest request = new IndexRequest(
                "posts",
                "doc",
                "1");
        String jsonString = "{" +
                "\"user\":\"kimchy\"," +
                "\"postDate\":\"2013-01-30\"," +
                "\"message\":\"trying out Elasticsearch\"" +
                "}";
        IndexRequest source = request.source(jsonString, XContentType.JSON);
        System.out.println(source);
    }

}
