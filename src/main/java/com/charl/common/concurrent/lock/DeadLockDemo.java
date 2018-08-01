package com.charl.common.concurrent.lock;

/**
 * @program: common
 * @description: 死锁demo
 * @author: charl
 * @create: 2018-07-31 16:14
 **/
public class DeadLockDemo {

    private static Object a = new Object();
    private static Object b = new Object();

    public static void methodA() throws InterruptedException {
        synchronized (a) {
            Thread.sleep(1 * 1000);

            synchronized (b) {
                System.out.println("执行methodA的方法");
            }
        }
    }

    public static void methodB() throws InterruptedException {
        synchronized (b) {
            Thread.sleep(1 * 1000);

            synchronized (a) {
                System.out.println("执行methodB的方法");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> {
            try {
                DeadLockDemo.methodA();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                DeadLockDemo.methodB();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

    }
}
