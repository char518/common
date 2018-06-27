package com.charl.common.pattern;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

@Service
public class UserRegisterHandler implements ApplicationListener<UserRegister> {

    @Override
    public void onApplicationEvent(UserRegister event) {
        System.out.println("邮件服务===接收到：".concat(event.getSource().toString()).concat("的注册申请"));
    }
}
