package com.charl.common.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @program: common
 * @description:
 * @author: charl
 * @create: 2018-06-27 10:29
 **/
public class GuavaCacheLoaderDemo {

    private  LoadingCache<Object, Object> cache = null;

    public void loadCache() {
        cache = CacheBuilder.newBuilder()
                .refreshAfterWrite(60, TimeUnit.SECONDS)
                .build(new CacheLoader<Object, Object>() {
            @Override
            public Object load(Object key) throws Exception {
                //当本地方法没有命中缓存时，重新调用load，重新刷入缓存
                return getValue(key);
            }

            private Object getValue(Object key) {
                return Integer.valueOf(90);
            }
        });
    }

    public Object get(Object key) throws ExecutionException {
        loadCache();
        Object o = cache.get(key);
        return o;
    }

    public static void main(String[] args) throws ExecutionException {
        GuavaCacheLoaderDemo demo = new GuavaCacheLoaderDemo();
        Object o = demo.get("1");
        System.out.println(o);
    }
}
