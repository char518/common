package com.charl.common.nettydemo.chat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @program: common
 * @description:
 * @author: charl
 * @create: 2018-08-13 15:21
 **/
public class SimpleChatClientHandler extends SimpleChannelInboundHandler {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println((String) msg);
    }
}
