package com.charl.common.nettydemo.chat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @program: common
 * @description:
 * @author: charl
 * @create: 2018-08-13 14:27
 **/
public class SimpleChatServer {

    private int port;

    public SimpleChatServer(int port) {
        this.port = port;
    }

    public void run() {
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();

            b.group(boss, worker)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new SimpleChatServerInitializer())
                    .option(ChannelOption.SO_BACKLOG,128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            System.out.println("SimpleChatServer 启动了...");

            //绑定端口
            ChannelFuture sync = b.bind(port).sync();

            // 等待服务器  socket 关闭 。
            // 在这个例子中，这不会发生，但你可以优雅地关闭你的服务器。
            sync.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            worker.shutdownGracefully();
            boss.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        int port;

        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        } else {
            port = 8090;
        }

        new SimpleChatServer(port).run();
    }
}
