package com.charl.common.pattern.observer;

public class Observer1 implements Observer {

    private String name;

    public Observer1(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void update() {
        System.out.println(this.name + ":exec update()");
    }
}
