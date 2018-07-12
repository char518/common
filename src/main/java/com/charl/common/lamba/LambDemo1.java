package com.charl.common.lamba;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @program: common
 * @description:
 * @author: charl
 * @create: 2018-07-10 09:42
 **/
public class LambDemo1 {

    public static void main(String[] args) {
        List<String> strings = Arrays.asList("abc", "123", "bgh", "charl", "jone");
        Map<String, Long> collect = strings.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(collect);
    }

}
