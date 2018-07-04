package com.charl.common.concurrent.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * @program: common
 * @description: 跟传统方式相比，并没有感觉到有多快
 * @author: charl
 * @create: 2018-07-04 10:21
 **/
public class ForkJoinDemo {

    private static final int PER_MAX = 2000;

    static class MyForkJoinTask extends RecursiveTask<Integer> {

        private int start;

        private int end;

        public MyForkJoinTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            if (end - start <= PER_MAX) {
                System.out.println("开始计算部分:" + start + "，结束部分：" + end);
                Integer total = 0;
                for (int i = start; i <= end; i++) {
                    total += i;
                }
                return total;
            } else {
                int i = (start + end) / 2;

                System.out.println("拆分成两个子任务：任务1：" + start + "到" + i + "，任务2：" + (i + 1) + "到" + end);

                MyForkJoinTask task1 = new MyForkJoinTask(start, i);

                MyForkJoinTask task2 = new MyForkJoinTask(i + 1, end);

                task1.fork();
                task2.fork();

//                invokeAll(task1,task2);

                return task1.join() + task2.join();
            }
        }
    }

    public static void main(String[] args) {
//        long l = System.currentTimeMillis();
//        ForkJoinPool pool = ForkJoinPool.commonPool();
//        ForkJoinTask<Integer> submit = pool.submit(new MyForkJoinTask(1, 1000000001));
//
//        try {
////            Integer result = pool.invoke(new MyForkJoinTask(1, 1000000001)); -- 7956
//            Integer result = submit.get();
//            System.out.println("计算结果是result=======：" + result + ",计算时间：" + (System.currentTimeMillis() - l));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        long l1 = System.currentTimeMillis();
//        int total = 0;
//        for (int i = 1; i < 1000000001; i++) {
//            total += i;
//        }
//        System.out.println("计算结果："+total+",传统的计算时间：" + (System.currentTimeMillis() - l1));

    }

}
