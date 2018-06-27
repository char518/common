package com.charl.common.concurrent;

/**
 * 异步任务执行器工厂
 * 
 * @author charl
 * 
 */
public class DefaultTaskProcessFactory implements TaskProcessFactory {
    /**
     * 核心线程数
     */
    private static int DEFAULT_CORE_SIZE = Runtime.getRuntime().availableProcessors();

    /**
     * 线程池大小
     */
    private static int DEFAULT_POOL_SIZE = DEFAULT_CORE_SIZE * 2;

    private int coreSize = DEFAULT_CORE_SIZE;

    private int poolSize = DEFAULT_POOL_SIZE;

    public TaskProcess createTaskProcess(String domain, int coreSize, int poolSize) {
        return new TaskProcess(domain, coreSize, poolSize);
    }

    public TaskProcess createTaskProcess(String domain) {
        return createTaskProcess(domain, coreSize, poolSize);
    }

    public void setCoreSize(int coreSize) {
        this.coreSize = coreSize;
    }

    public void setPoolSize(int poolSize) {
        this.poolSize = poolSize;
    }
}
