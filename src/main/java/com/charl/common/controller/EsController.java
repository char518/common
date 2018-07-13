package com.charl.common.controller;

import com.charl.common.domin.User;
import com.charl.common.service.IEsService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: common
 * @description:
 * @author: charl
 * @create: 2018-07-12 10:47
 **/
@Api(value = "ES测试Controller")
@RestController
@RequestMapping(value = "/es")
public class EsController {

    @Autowired
    private IEsService esService;

    @PostMapping(value = "/addDocument")
    public int index(@RequestParam User user) {
        int i = esService.addDocument(user);
        return i;
    }

}
