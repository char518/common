package com.charl.common.event;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 事件源（谁来触发事件）
 */
public class EventSourceObject {

    private String name;

    private Set<CusEventListener> eventListeners;

    public EventSourceObject() {
        this.eventListeners = new HashSet<>();
        this.name = "defaultname";
        System.out.println("source object name is:" + this.getName());
    }

    public void registerListener(CusEventListener listener) {
        this.eventListeners.add(listener);
    }

    //当事件发生时,通知注册在该事件源上的所有监听器做出相应的反应（调用回调方法）
    protected void notifies() {
        CusEventListener cel = null;
        Iterator<CusEventListener> iterator = this.eventListeners.iterator();
        System.out.println("开始回调了");
        while (iterator.hasNext()) {
            cel = iterator.next();
            cel.callBackListener(new CusEvent(this));
        }
    }

    public String getName() {
        return name;
    }

    //模拟事件触发器，当成员变量name的值发生变化时，触发事件。
    public void setName(String name) {
        if (!this.name.equals(name)) {
            this.name = name;
            notifies();
        }
    }
}
