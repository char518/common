package com.charl.common.concurrent.pc;

/**
 * @program: common
 * @description: 生产者
 * @author: charl
 * @create: 2018-08-02 10:16
 **/
public class Producer implements Runnable{

    private Basket basket;

    public Producer(Basket basket) {
        this.basket = basket;
    }


    @Override
    public void run() {
        try {
            System.out.println("生产者开始生产了");
            basket.put();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
