package com.charl.common.nettydemo;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

/**
 * @program: common
 * @description: 丢弃服务器
 * @author: charl
 * @create: 2018-08-10 15:00
 **/
public class DiscardServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        ByteBuf in = (ByteBuf) msg;
//        try {
//            while (in.isReadable()) {
//                System.out.println("discard server received:" + (char) in.readByte());
//                System.out.flush();
//            }
//        } finally {
//            ReferenceCountUtil.release(msg);
//        }
        ByteBuf in = (ByteBuf) msg;
        System.out.println("discard server received:" + in.toString(CharsetUtil.UTF_8));
        ctx.writeAndFlush(msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
