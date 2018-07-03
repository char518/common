package com.charl.common.domin;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: common
 * @description:
 * @author: charl
 * @create: 2018-06-26 14:32
 **/
@Data
public class User implements Serializable {

    private Long id;

    private String name;

    private Integer age;

    private String mobile;

    private String image;

    private String email;

    private String desc;

}
