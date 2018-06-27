package com.charl.common.concurrent;
/**
 * 标记型任务
 * @author charl
 *
 * @param <T>
 */
public interface IdentityTaskAction<T> extends TaskAction<T> {
	String identity();
}
