package com.charl.common.event;

import java.util.EventObject;

/**
 * 事件
 */
public class CusEvent extends EventObject {

    /**
     * 事件源
     */
    private Object source;

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public CusEvent(Object source) {
        super(source);
        this.source = source;
    }

    @Override
    public Object getSource() {
        return source;
    }

    public void setSource(Object source) {
        this.source = source;
    }
}
