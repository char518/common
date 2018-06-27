package com.charl.common.pattern.observer;

public class Test {

    public static void main(String[] args) {
        Observed1 observed1 = new Observed1();

        observed1.add(new Observer1("one"));
        observed1.add(new Observer1("two"));
        observed1.add(new Observer1("three"));

        observed1.notify("hello");

    }

}
