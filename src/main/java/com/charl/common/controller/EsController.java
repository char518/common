package com.charl.common.controller;

import com.charl.common.es.EsService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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

    @Resource
    private EsService esService;

    @PostMapping(value = "/index")
    public int index() {
        int index = esService.index();
        return index;
    }

}
