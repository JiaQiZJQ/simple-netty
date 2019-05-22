package com.netty.demo.netty190522.handler.server;

import com.netty.demo.netty190510.packet.MessageRequestPacket;
import com.netty.demo.netty190510.packet.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 * @author 张佳琦
 * @ClassName: MessageResponseHandler
 * @Description: 用来处理类型为《MessageRequestPacket》的相应消息
 * @date 2019/5/22 13:33
 */
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket messageRequestPacket) {
        ctx.channel().writeAndFlush(this.readMsg(messageRequestPacket));
    }

    public MessageResponsePacket readMsg(MessageRequestPacket messageRequestPacket){
        System.out.println(new Date() + "MessageRequestHandler收到客户端消息：" + messageRequestPacket.getMessage());
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setMessage(new Date() + "服务端MessageRequestHandler回复 【"+ messageRequestPacket.getMessage() +"】");
        return messageResponsePacket;
    }
}