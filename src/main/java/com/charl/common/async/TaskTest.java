package com.charl.common.async;

/**
 * @program: common
 * @description:
 * @author: charl
 * @create: 2018-06-19 17:48
 **/
public class TaskTest {

    public static void main(String[] args) throws InterruptedException {
        AsyncDispatcher dispatcher = new AsyncDispatcher();

        Thread.sleep(5*1000);

        dispatcher.addTask(new TypedTask<Object>() {
            @Override
            public Object doAction() throws Exception {
                System.out.println("开始执行业务1内容");
                Thread.sleep(5*1000);
                System.out.println("结束执行业务1内容");
                return null;
            }

            @Override
            public String type() {
                return "type1";
            }
        });
        dispatcher.addTask(new TypedTask<Object>() {
            @Override
            public Object doAction() throws Exception {
                System.out.println("开始执行业务2内容");
                Thread.sleep(5*1000);
                System.out.println("结束执行业务2内容");
                return null;
            }

            @Override
            public String type() {
                return "type2";
            }
        });
        dispatcher.addTask(new TypedTask<Object>() {
            @Override
            public Object doAction() throws Exception {
                System.out.println("开始执行业务3内容");
                Thread.sleep(5*1000);
                System.out.println("结束执行业务3内容");
                return null;
            }

            @Override
            public String type() {
                return "type3";
            }
        });
        dispatcher.addTask(new TypedTask<Object>() {
            @Override
            public Object doAction() throws Exception {
                System.out.println("开始执行业务4内容");
                Thread.sleep(5*1000);
                System.out.println("结束执行业务4内容");
                return null;
            }

            @Override
            public String type() {
                return "type4";
            }
        });
    }

}
