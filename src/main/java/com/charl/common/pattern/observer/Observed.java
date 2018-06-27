package com.charl.common.pattern.observer;

/**
 * 被观察者接口
 */
public interface Observed {

    public void add(Observer observer);

    public void remove(Observer observer);

    public void notify(String msg);

}
