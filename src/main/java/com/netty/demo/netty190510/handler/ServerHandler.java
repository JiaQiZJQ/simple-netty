package com.netty.demo.netty190510.handler;

import com.netty.demo.netty190508_1.LoginRequestPacket;
import com.netty.demo.netty190508_1.Packet;
import com.netty.demo.netty190508_1.PacketCodeC;
import com.netty.demo.netty190510.packet.LoginResponsePacket;
import com.netty.demo.netty190510.packet.MessageRequestPacket;
import com.netty.demo.netty190510.packet.MessageResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

/**
 * @Auther: ZhangJiaQi
 * @Date: 2019/5/10 10:17
 * @Description:
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        Packet decode = PacketCodeC.INSTANCE.decode(byteBuf);
        if(decode instanceof LoginRequestPacket){
            System.out.println("客户端请求登陆。。。");
            LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
            LoginRequestPacket loginRequestPacket = (LoginRequestPacket) decode;
            if("张佳琦".equals(loginRequestPacket.getUsername()) && "123456".equals(loginRequestPacket.getPassword())){
                System.out.println("客户端登陆成功！！！");
                loginResponsePacket.setResultCode("0000");
                loginResponsePacket.setResultMsg("登陆成功！！！");
            }else{
                System.out.println("客户端登陆失败！！！");
                loginResponsePacket.setResultCode("0001");
                loginResponsePacket.setResultMsg("登陆失败！！！");
            }
            ByteBuf encode = PacketCodeC.INSTANCE.encode(ctx.alloc(),loginResponsePacket);
            ctx.channel().writeAndFlush(encode);
        }else if (decode instanceof MessageRequestPacket){
            MessageRequestPacket messageRequestPacket = (MessageRequestPacket) decode;
            System.out.println(new Date() + "收到客户端消息：" + messageRequestPacket.getMessage());
            MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
            messageResponsePacket.setMessage(new Date() + "服务端回复【"+ messageRequestPacket.getMessage() +"】");
            ByteBuf encode = PacketCodeC.INSTANCE.encode(ctx.alloc(), messageResponsePacket);
            ctx.channel().writeAndFlush(encode);
        }
    }
}
