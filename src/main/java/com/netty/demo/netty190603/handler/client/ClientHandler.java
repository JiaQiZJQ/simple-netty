package com.netty.demo.netty190603.handler.client;

import com.netty.demo.netty190508_1.LoginRequestPacket;
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
        System.out.println(new Date() + "客户端开始登陆！！！");

        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(UUID.randomUUID().toString());
        loginRequestPacket.setUsername("张佳琦");
        loginRequestPacket.setPassword("123456");
        ctx.channel().writeAndFlush(loginRequestPacket);

    }

//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        ByteBuf byteBuf = (ByteBuf) msg;
//        Packet decode = PacketCodeC.INSTANCE.decode(byteBuf);
//        if(decode instanceof LoginResponsePacket){
//            LoginResponsePacket loginResponsePacket = (LoginResponsePacket) decode;
//            System.out.println(loginResponsePacket.getResultMsg());
//            if("0000".equals(loginResponsePacket.getResultCode())){
//                LoginUtil.markAsLogin(ctx.channel());
//            }
//        }else if(decode instanceof MessageResponsePacket){
//            MessageResponsePacket messageResponsePacket = (MessageResponsePacket) decode;
//            System.out.println(new Date() + "收到服务端消息：【" + messageResponsePacket.getMessage() + "】");
//        }
//    }
}
