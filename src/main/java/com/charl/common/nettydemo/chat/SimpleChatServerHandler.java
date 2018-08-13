package com.charl.common.nettydemo.chat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @program: common
 * @description:
 * @author: charl
 * @create: 2018-08-13 14:03
 **/
public class SimpleChatServerHandler extends SimpleChannelInboundHandler {

    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel incoming = ctx.channel();

        // Broadcast a message to multiple Channels
        channels.writeAndFlush("[SERVER] - " + incoming.remoteAddress() + " 加入\n");
        channels.add(incoming);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();

        // Broadcast a message to multiple Channels
        channels.writeAndFlush("[SERVER] - " + channel.remoteAddress() + " 离开\n");

        // A closed Channel is automatically removed from ChannelGroup,
        // so there is no need to do "channels.remove(ctx.channel());"
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        Channel channel = ctx.channel();
        System.out.println("SimpleClient:" + channel.remoteAddress() + "异常...");
        ctx.close();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println("SimpleClient:"+channel.remoteAddress()+"在线...");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println("SimpleClient:"+channel.remoteAddress()+"掉线...");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        Channel incoming = ctx.channel();

        for (Channel channel : channels) {
            if(channel != incoming) {
                channel.writeAndFlush("[" + incoming.remoteAddress() +"]" + (java.lang.String) msg + "\n");
            } else {
                channel.writeAndFlush("[you]" + (java.lang.String) msg + "\n");
            }
        }
    }
}
