package com.charl.common.controller;

import com.charl.common.pattern.UserPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @Autowired
    UserPublisher userPublisher;

    @GetMapping(value = "/")
    public String index() {
        return "项目成功启动！！";
    }

    @GetMapping(value = "/register/{name}")
    public String register(@PathVariable String name) {
        userPublisher.register(name);
        return "register success!!!";
    }
}
