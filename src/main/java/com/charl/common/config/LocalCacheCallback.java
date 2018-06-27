package com.charl.common.config;

public interface LocalCacheCallback {

    public Object load(String key, Object oldvalue) throws Exception;

}
