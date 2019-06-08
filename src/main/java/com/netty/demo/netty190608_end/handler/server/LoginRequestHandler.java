package com.netty.demo.netty190608_end.handler.server;


import com.netty.demo.netty190508_1.LoginRequestPacket;
import com.netty.demo.netty190608_end.config.LoginUtil;
import com.netty.demo.netty190608_end.config.Session;
import com.netty.demo.netty190608_end.config.SessionUtil;
import com.netty.demo.netty190608_end.packet.LoginResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Random;

/**
 * @author 张佳琦
 * @ClassName: LoginRequestHandler
 * @Description: 用来处理类型为《LoginRequestPacket》的登录请求相应消息
 * @date 2019/5/22 10:38
 */
// 1. 加上注解标识，表明该 handler 是可以多个 channel 共享的
@ChannelHandler.Sharable
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
    public static final LoginRequestHandler INSTANCE = new LoginRequestHandler();

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
        System.out.println("【LoginRequestHandler】客户端登陆成功！！！");
        loginResponsePacket.setResultCode("0000");
        loginResponsePacket.setResultMsg("登陆成功！！！");
        Random random = new Random();
        String i = random.nextInt(100) + "";
        loginResponsePacket.setUserId(i);
        SessionUtil.bindSession(new Session(i,loginRequestPacket.getUsername()),channelHandlerContext.channel());
        return loginResponsePacket;
    }
}
