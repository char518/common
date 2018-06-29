package com.charl.common.async;

import org.springframework.util.CollectionUtils;

import java.util.concurrent.*;

/**
 * @program: product-gateway
 * @description: 异步处理类
 * @author: charl
 * @create: 2018-06-19 16:47
 **/
public class AsyncDispatcher {

    /**
     * concurrent执行器
     */
    private ExecutorService executor;

    /**
     * 核心线程数
     */
    private int coreSize = Runtime.getRuntime().availableProcessors();

    /**
     * 定时任务：定时处理待处理的任务
     */
    private static ScheduledExecutorService dispatchTaskExecutor = Executors.newScheduledThreadPool(1);

    private BlockingQueue<TypedTask> tasks = new LinkedBlockingQueue<>();

    public AsyncDispatcher() {
        createThreadPool();
        dispatchTaskExecutor.scheduleAtFixedRate(this::run, 0, 2, TimeUnit.SECONDS);
    }

    public void addTask(TypedTask task) {
        tasks.add(task);
        System.out.println("task size:" + tasks.size() + ",task type:" + task.type());
    }

    public void addTasks(TypedTask... taskList) {
        //线程都在忙
        if (!CollectionUtils.isEmpty(tasks) && tasks.size() <= coreSize) {
            return;
        }
    }

    private void createThreadPool() {
        //创建执行线程
        executor = Executors.newFixedThreadPool(coreSize);
    }

    public void run() {
        System.out.println("schedule run():" + tasks.size());
        if (CollectionUtils.isEmpty(tasks)) {
            return;
        }

        while (tasks.size() > 0) {
            try {
                //取出队列头部数据,并擦除
                TypedTask poll = tasks.take();
                executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            poll.doAction();
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            tasks.remove(poll);
                        }
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
