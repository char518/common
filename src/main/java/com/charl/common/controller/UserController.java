package com.charl.common.controller;

import com.charl.common.domin.BaseResponse;
import com.charl.common.domin.User;
import com.charl.common.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: common
 * @description:
 * @author: charl
 * @create: 2018-06-26 15:07
 **/
@RestController
@Api(value = "【用户中心】接口文档",description = "用户中心接口")
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping(value = "/register")
    @ApiOperation(value = "注册用户接口",notes = "注册")
    public BaseResponse<Object> registerUser(@RequestBody User user) {
        int i = userService.addUser(user);
        return BaseResponse.buildSuccessResponse(i);
    }

    @PostMapping(value = "/getById")
    @ApiOperation(value = "获取用户",notes = "获取")
    public BaseResponse<User> getUserById(@RequestBody Long id) {
        User user = userService.queryUserById(id);
        return BaseResponse.buildSuccessResponse(user);
    }

}
