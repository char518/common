/*
 * Copyright (C) 2006-2018
 * Author: charl
 * Description:Holder.java
 */
package com.charl.common.concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author charl
 * 
 */
public final class Holder<T> {
    private Future<T> future;

    Holder(Future<T> future) {
        super();
        this.future = future;
    }

    public T getResult() {
        try {
            return future.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public T getResult(long timeout) {
        try {
            return future.get(timeout, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            return null;
        }
    }

}
