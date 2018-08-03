package com.charl.common.concurrent.pc;

/**
 * @program: common
 * @description:
 * @author: charl
 * @create: 2018-08-02 10:17
 **/
public class Test {

    public static void main(String[] args) {
        Basket basket = new Basket(10);
        for (int i = 0; i < 10; i++) {
            new Thread(new Producer(basket)).start();
        }

        for (int i = 0; i < 10; i++) {
            new Thread(new Consumer(basket)).start();
        }
    }

}
