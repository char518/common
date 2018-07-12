package com.charl.common.concurrent;

import java.util.concurrent.Callable;

/**
 * @program: common
 * @description:
 * @author: charl
 * @create: 2018-07-11 17:15
 **/
public class TaskDemo {

    //1.线程
    static class Thead1 extends Thread {

        @Override
        public void run() {
            try {
                Thread.sleep(2*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName().concat(":我的Thead1:"+Thread.currentThread().getId()));
        }
    }

    //2.
    static class Thead2 implements Runnable {

        @Override
        public void run() {
            try {
                Thread.sleep(2*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName().concat(":Thead2:"+Thread.currentThread().getId()));
        }
    }

    //3.
    static class Thead3 implements Callable {

        @Override
        public Object call() throws Exception {
            try {
                Thread.sleep(2*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName().concat(":Thead3:"+Thread.currentThread().getId()));
            return "Thead3 hello";
        }
    }

    //并发：是指一个线程或者CPU多次轮换执行
    //并行：是指多个线程或者多个CPU同时进行执行
    public static void main(String[] args) throws Exception {
        Thead1 thead1 = new Thead1();
        Thead2 thead2 = new Thead2();
        Thead3 thead3 = new Thead3();

        int i1 = Thread.activeCount();
        System.out.println("active count:" + i1);

        for (int i = 0; i < 10; i++) {
            thead1.run();
            thead2.run();
            String call = (String) thead3.call();

            int i2 = Thread.activeCount();
            System.out.println("active count:" + i2);
        }
    }

}
