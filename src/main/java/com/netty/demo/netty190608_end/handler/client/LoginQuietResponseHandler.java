package com.netty.demo.netty190608_end.handler.client;

import com.netty.demo.netty190608_end.packet.LoginQuietResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author 张佳琦
 * @ClassName: LoginQuietResponseHandler
 * @Description: 登出响应
 * @date 2019/6/5 10:02
 */
public class LoginQuietResponseHandler extends SimpleChannelInboundHandler<LoginQuietResponsePacket> {

    public static final LoginQuietResponseHandler INSTANCE = new LoginQuietResponseHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginQuietResponsePacket loginQuietResponsePacket) throws Exception {
        System.out.println("登出操作返回结果：" + loginQuietResponsePacket.getMsg());
    }
}
