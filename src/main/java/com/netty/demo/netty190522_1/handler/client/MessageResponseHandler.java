package com.netty.demo.netty190522_1.handler.client;

import com.netty.demo.netty190510.packet.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 * @author 张佳琦
 * @ClassName: MessageResponseHandler
 * @Description: 用来处理类型为《MessageResponsePacket》的相应消息
 * @date 2019/5/22 13:33
 */
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageResponsePacket messageResponsePacket) throws Exception {
        System.out.println(new Date() + "收到服务端消息：【" + messageResponsePacket.getMessage() + "】");
    }
}
