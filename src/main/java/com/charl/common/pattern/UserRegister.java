package com.charl.common.pattern;

import org.springframework.context.ApplicationEvent;

/**
 * 定义事件
 */
public class UserRegister extends ApplicationEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public UserRegister(String source) {
        super(source);
    }
}
