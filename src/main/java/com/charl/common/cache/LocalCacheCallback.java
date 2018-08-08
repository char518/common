package com.charl.common.cache;

public interface LocalCacheCallback {

    public Object load(String key, Object oldvalue) throws Exception;

}
