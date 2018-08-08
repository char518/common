package com.charl.common.dao;

import com.charl.common.utils.JsonUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @program: common
 * @description:
 * @author: charl
 * @create: 2018-08-03 16:16
 **/
@Repository
public class ElasticSearchRestClientDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(ElasticSearchRestClientDao.class);

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    /**
     * 查询指定Index 所有数据
     * @param index
     * @param tClass
     * @param size
     * @return
     */
    public <T> List<T> queryAll(String index, Class<T> tClass, int size) {
        SearchRequest searchRequest = new SearchRequest(index);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        searchSourceBuilder.size(size);
        searchRequest.source(searchSourceBuilder);
        SearchResponse search = null;
        try {
            search = restHighLevelClient.search(searchRequest);
        } catch (IOException e) {
            LOGGER.error("restHighLevelClient search error:{}", e);
        }

        if (null != search && search.status().getStatus() != 200) {
            return Collections.EMPTY_LIST;
        }

        long totalHits = search.getHits().totalHits;
        List<T> result = new ArrayList<>((int) totalHits);
        for (SearchHit documentFields : search.getHits()) {
            String sourceAsString = documentFields.getSourceAsString();
            T t = JsonUtils.json2obj(sourceAsString, tClass);
            result.add(t);
        }
        return result;
    }

}
