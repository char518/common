package com.charl.common.concurrent;

/**
 * 任务回调封装
 * 
 * @author charl
 * 
 * @param <T>
 *            返回类型
 */
public interface TaskAction<T> {
	T doInAction() throws Exception;
}
