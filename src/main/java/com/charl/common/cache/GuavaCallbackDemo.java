package com.charl.common.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

/**
 * @program: common
 * @description:
 * @author: charl
 * @create: 2018-06-27 10:29
 **/
public class GuavaCallbackDemo {

    private Cache<Object, Object> cache = null;

    public void initCallback() {
        cache = CacheBuilder.newBuilder()
                .maximumSize(1024l)
                .build();
    }

    public Object get(Object key) throws ExecutionException {
        Object o = cache.get(key, new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                return getValue(key);
            }

            private Object getValue(Object key) {
                return Integer.valueOf(90);
            }
        });
        return o;
    }

    public static void main(String[] args) throws ExecutionException {
        GuavaCallbackDemo demo = new GuavaCallbackDemo();
        demo.initCallback();
        Object o = demo.get("11");
        System.out.println(o);
    }

}
