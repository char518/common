package com.charl.common.concurrent.pc;

/**
 * @program: common
 * @description:
 * @author: charl
 * @create: 2018-08-02 10:16
 **/
public class Consumer implements Runnable {

    private Basket basket;

    public Consumer(Basket basket) {
        this.basket = basket;
    }

    @Override
    public void run() {
        try {
            System.out.println("消费者开始运行啦！！");
            basket.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
