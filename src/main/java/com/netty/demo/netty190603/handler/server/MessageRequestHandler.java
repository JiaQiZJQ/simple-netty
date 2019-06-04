package com.netty.demo.netty190603.handler.server;

import com.netty.demo.netty190603.config.Session;
import com.netty.demo.netty190603.config.SessionUtil;
import com.netty.demo.netty190603.packet.MessageRequestPacket;
import com.netty.demo.netty190603.packet.MessageResponsePacket;
import io.netty.channel.Channel;
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
        this.readMsg(ctx,messageRequestPacket);
    }

    public void readMsg(ChannelHandlerContext ctx, MessageRequestPacket messageRequestPacket){

        Session session = SessionUtil.getSession(ctx.channel());//获取与通道绑定的session信息
        System.out.println(new Date() + "MessageRequestHandler收到客户端["+session.getUserId()+"]发送给["+messageRequestPacket.getToUserId()+"]消息：" + messageRequestPacket.getMsg());
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setFromUserId(session.getUserId());
        messageResponsePacket.setFromUserName(session.getUserName());
        messageResponsePacket.setMsg(messageRequestPacket.getMsg());

        Channel channel = SessionUtil.getChannel(messageRequestPacket.getToUserId());
        if(channel != null && SessionUtil.hasLogin(channel)){
            channel.writeAndFlush(messageResponsePacket);
        }else {
            System.out.println(messageRequestPacket.getToUserId()+":不在线，发送失败");
        }

    }
}