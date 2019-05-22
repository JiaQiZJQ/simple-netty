package com.netty.demo.netty190522_1.handler.client;

import com.netty.demo.netty190510.LoginUtil;
import com.netty.demo.netty190510.packet.LoginResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author 张佳琦
 * @ClassName: LoginResponseHandler
 * @Description: 用来处理类型为《LoginResponsePacket》的登录相应消息
 * @date 2019/5/22 13:27
 */
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginResponsePacket loginResponsePacket) throws Exception {
        System.out.println("客户端收到登录相应【LoginResponseHandler】：" + loginResponsePacket.getResultMsg());
        if("0000".equals(loginResponsePacket.getResultCode())){
            LoginUtil.markAsLogin(channelHandlerContext.channel());
        }
    }
}
