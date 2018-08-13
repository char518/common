package com.charl.common.nettydemo.chat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @program: common
 * @description:
 * @author: charl
 * @create: 2018-08-13 15:34
 **/
public class SimpleChatClient {

    private final String host;

    private int port;

    public SimpleChatClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void run() {
        EventLoopGroup client = new NioEventLoopGroup();

        Bootstrap b = new Bootstrap();
        b.group(client)
                .channel(NioSocketChannel.class)
                .handler(new SimpleChatClientInitializer());

        try {
            Channel channel = b.connect(host, port).sync().channel();

            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            while(true){
                channel.writeAndFlush(in.readLine() + "\r\n");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            client.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new SimpleChatClient("localhost",8090).run();
    }

}
