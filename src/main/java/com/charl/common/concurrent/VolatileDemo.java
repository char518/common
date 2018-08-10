package com.charl.common.concurrent;

/**
 * @program: common
 * @description: volatile不是线程安全的，volatile是无法保证复合操作的原子性（单次操作是原子的），它设计出来主要是为了保证内存的可见性和有序性，也就是说多个线程获取到的值是一样的，但它并不关心这个值到底是什么。
 * 它底层是用内存屏障来实现的。保证可见性、不保证原子性，禁止指令重排序,用一个lock标识
 * 应用场景：
 * （1）对变量的写操作不依赖于当前值。
 * （2）该变量没有包含在具有其他变量的不变式中。
 * @author: charl
 * @create: 2018-08-10 10:49
 **/
public class VolatileDemo {

    private volatile int index = 0;

    public int getIndex() {
        return index;
    }

    public void incr() {
        index++;
    }

    public static void main(String[] args) {
        VolatileDemo volatileDemo = new VolatileDemo();
        int flag = 10000;
        while (flag > 0) {
            new Thread(() -> {
                volatileDemo.incr();
            }).start();
            System.out.println("index="+ volatileDemo.getIndex());
            flag --;
        }
    }

}
