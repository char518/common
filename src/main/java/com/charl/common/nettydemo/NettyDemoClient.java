package com.charl.common.nettydemo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Date;

/**
 * @program: common
 * @description: netty client
 * @author: charl
 * @create: 2018-06-29 15:50
 **/
public class NettyDemoClient {

    private String host;

    private Integer port;

    public NettyDemoClient(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

    public void run(String msg) throws InterruptedException {
        EventLoopGroup client = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(client)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<Channel>() {

                    @Override
                    protected void initChannel(Channel ch) {
                        ch.pipeline().addLast(new StringEncoder());
                    }
                });

        io.netty.channel.Channel channel = bootstrap.connect(host, port).channel();

        while (true) {
            String conMsg = new Date().toString().concat(msg);
            System.out.println("客户端发送消息："+ conMsg);
            channel.writeAndFlush(conMsg);
            Thread.sleep(2 * 1000);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new NettyDemoClient("localhost",8000).run("你好啊");
    }
}
