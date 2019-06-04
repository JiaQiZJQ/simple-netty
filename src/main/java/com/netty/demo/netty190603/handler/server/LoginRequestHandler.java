package com.netty.demo.netty190603.handler.server;


import com.netty.demo.netty190508_1.LoginRequestPacket;
import com.netty.demo.netty190510.LoginUtil;
import com.netty.demo.netty190603.config.Session;
import com.netty.demo.netty190603.config.SessionUtil;
import com.netty.demo.netty190603.packet.LoginResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Random;

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
        LoginResponsePacket loginResponsePacket = login(loginRequestPacket,channelHandlerContext);
        if("0000".equals(loginResponsePacket.getResultCode())){
            LoginUtil.markAsLogin(channelHandlerContext.channel());
        }
        channelHandlerContext.channel().writeAndFlush(loginResponsePacket);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("通道关闭，解除绑定！！！");
        SessionUtil.unBindSession(ctx.channel());
    }

    public LoginResponsePacket login(LoginRequestPacket loginRequestPacket, ChannelHandlerContext channelHandlerContext){
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        if(("张佳琦".equals(loginRequestPacket.getUsername()) || "褚绚红".equals(loginRequestPacket.getUsername())) && "123456".equals(loginRequestPacket.getPassword())){
            System.out.println("【LoginRequestHandler】客户端登陆成功！！！");
            loginResponsePacket.setResultCode("0000");
            loginResponsePacket.setResultMsg("登陆成功！！！");
            Random random = new Random();
            String i = random.nextInt(5) + "";
            loginResponsePacket.setUserId(i);
            SessionUtil.bindSession(new Session(i,loginRequestPacket.getUsername()),channelHandlerContext.channel());
        }else{
            System.out.println("【LoginRequestHandler】客户端登陆失败！！！");
            loginResponsePacket.setResultCode("0001");
            loginResponsePacket.setResultMsg("登陆失败！！！");
        }
        return loginResponsePacket;
    }
}
