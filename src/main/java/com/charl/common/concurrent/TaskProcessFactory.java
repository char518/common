/*
 * Copyright (C) 2006-2016 Aijia All rights reserved
 * Author: charl
 * Date: 2015-11-10
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
