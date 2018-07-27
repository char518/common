package com.charl.common.lamba;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @program: common
 * @description:
 * @author: charl
 * @create: 2018-07-27 15:10
 **/
public class LambDemo2 {

    public static void main(String[] args) {
        List<String> strings = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");

        System.out.println("Languages all sites :");

        filter(strings, (str) -> true);

        //reduce
        List costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);

        Object o = costBeforeTax.stream().reduce((a, b) -> add(a, b)).get();

        System.out.println(o);

    }

    public static void filter(List list, Predicate condition) {
        for (Object o : list) {
            if (condition.test(o)) {
                System.out.println(o + "....");
            }
        }
    }

    public static int add(Object a, Object b) {
        return (int) a + (int) b;
    }
}
