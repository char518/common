package com.charl.common.event;

public class Client {

    public static void main(String[] args) {
        EventSourceObject sourceObject = new EventSourceObject();
        sourceObject.registerListener(new CusEventListener() {
            @Override
            public void callBackListener(CusEvent cusEvent) {
                System.out.println("First:start exec my tast2!!!"+Thread.currentThread().getName());
            }
        });
        sourceObject.registerListener(new CusEventListener() {
            @Override
            public void callBackListener(CusEvent cusEvent) {
                System.out.println("Second:start exec my task2!!!"+Thread.currentThread().getName());
            }
        });
        System.out.println("开始触发事件"+Thread.currentThread().getName());
        sourceObject.setName("cherry");
        return;
    }

}
