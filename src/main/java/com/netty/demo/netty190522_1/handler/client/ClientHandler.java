package com.netty.demo.netty190522_1.handler.client;

import com.netty.demo.netty190508_1.LoginRequestPacket;
import com.netty.demo.netty190508_1.Packet;
import com.netty.demo.netty190508_1.PacketCodeC;
import com.netty.demo.netty190510.LoginUtil;
import com.netty.demo.netty190510.packet.LoginResponsePacket;
import com.netty.demo.netty190510.packet.MessageRequestPacket;
import com.netty.demo.netty190510.packet.MessageResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;
import java.util.UUID;

/**
 * @Auther: ZhangJiaQi
 * @Date: 2019/5/10 09:41
 * @Description:
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(new Date() + "客户端开始循环发送！！！");
        int i= 0;
        while ( i < 1000){
            i++;
            MessageRequestPacket messageRequestPacket = new MessageRequestPacket();
            messageRequestPacket.setMessage(i+"");
            ByteBuf encode = PacketCodeC.INSTANCE.encode(ctx.alloc(), messageRequestPacket);
            ctx.channel().writeAndFlush(encode);
        }

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        Packet decode = PacketCodeC.INSTANCE.decode(byteBuf);
        if(decode instanceof LoginResponsePacket){
            LoginResponsePacket loginResponsePacket = (LoginResponsePacket) decode;
            System.out.println(loginResponsePacket.getResultMsg());
            if("0000".equals(loginResponsePacket.getResultCode())){
                LoginUtil.markAsLogin(ctx.channel());
            }
        }else if(decode instanceof MessageResponsePacket){
            MessageResponsePacket messageResponsePacket = (MessageResponsePacket) decode;
            System.out.println(new Date() + "收到服务端消息：【" + messageResponsePacket.getMessage() + "】");
        }
    }
}
