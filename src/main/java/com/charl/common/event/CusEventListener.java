package com.charl.common.event;

import java.util.EventListener;

/**
 * 事件监听器
 */
public interface CusEventListener extends EventListener {

    public void callBackListener(CusEvent cusEvent);

}
