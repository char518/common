package com.charl.common.nettydemo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.swagger.models.auth.In;
import scala.Int;

/**
 * @program: common
 * @description:
 * @author: charl
 * @create: 2018-08-10 15:17
 **/
public class DisCardServer {

    private int port;

    public DisCardServer(int port) {
        this.port = port;
    }

    public void start() {
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();

            b.group(boss,worker)
            .channel(NioServerSocketChannel.class)
            .childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new DiscardServerHandler());
                }
            })
            .option(ChannelOption.SO_BACKLOG,128)
            .childOption(ChannelOption.SO_KEEPALIVE,true);

            ChannelFuture sync = b.bind(port).sync();
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
            port = 8080;
        }

        new DisCardServer(port).start();
    }
}
