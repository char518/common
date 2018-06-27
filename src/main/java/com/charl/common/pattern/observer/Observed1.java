package com.charl.common.pattern.observer;

import java.util.ArrayList;
import java.util.List;

public class Observed1 implements Observed {

    private List<Observer> observers = new ArrayList<>();

    @Override
    public void add(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void remove(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notify(String msg) {
        System.out.println("observed1 notify:msg to .");
        if (!observers.isEmpty() && observers.size() > 0) {
            observers.forEach(v -> {
                v.update();
            });
        }
    }
}
