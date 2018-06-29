/*
 * Copyright (C) 2006-2018
 * Author: charl
 * Description:TaskProcessFactory.java
 */
package com.charl.common.concurrent;


/**
 * 任务处理器创建工厂
 * 
 * @author charl
 * 
 */
public interface TaskProcessFactory {

    /**
     * 任务处理器
     * 
     * <pre>
     * 使用默认的线程池
     * </pre>
     * @return
     */
    TaskProcess createTaskProcess(String domain);
}
