package com.charl.common.concurrent.pc;

/**
 * @program: common
 * @description:
 * @author: charl
 * @create: 2018-08-02 14:09
 **/
public class Basket {

    private int maxLength;

    private int size;

    public Basket(int maxLength) {
        this.maxLength = maxLength;
    }

    public int getSize() {
        return size;
    }

    public synchronized void put() throws InterruptedException {
        while (size == maxLength) {
            System.out.println("篮子满了，等等吧！");
            this.wait();
        }
        size++;
        System.out.println("开始生产：" + size);
    }

    public synchronized void get() throws InterruptedException {
        while (size == 0) {
            System.out.println("篮子空了，等等吧！");
            this.wait();
        }
        System.out.println("开始消费："+ size);
        size--;
        this.notifyAll();
    }

}
