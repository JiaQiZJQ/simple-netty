package com.netty.demo.netty190522_1.handler.server;

import com.netty.demo.netty190508_1.LoginRequestPacket;
import com.netty.demo.netty190510.packet.LoginResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author 张佳琦
 * @ClassName: LoginRequestHandler
 * @Description: 用来处理类型为《LoginRequestPacket》的登录请求相应消息
 * @date 2019/5/22 10:38
 */
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginRequestPacket loginRequestPacket) throws Exception {
        System.out.println("【LoginRequestHandler】客户端请求登陆。。。");
        channelHandlerContext.channel().writeAndFlush(login(loginRequestPacket));
    }

    public LoginResponsePacket login(LoginRequestPacket loginRequestPacket){
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        if("张佳琦".equals(loginRequestPacket.getUsername()) && "123456".equals(loginRequestPacket.getPassword())){
            System.out.println("【LoginRequestHandler】客户端登陆成功！！！");
            loginResponsePacket.setResultCode("0000");
            loginResponsePacket.setResultMsg("登陆成功！！！");
        }else{
            System.out.println("【LoginRequestHandler】客户端登陆失败！！！");
            loginResponsePacket.setResultCode("0001");
            loginResponsePacket.setResultMsg("登陆失败！！！");
        }
        return loginResponsePacket;
    }
}
