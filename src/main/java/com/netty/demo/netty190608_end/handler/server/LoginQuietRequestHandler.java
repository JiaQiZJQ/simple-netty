package com.netty.demo.netty190608_end.handler.server;

import com.netty.demo.netty190608_end.config.Session;
import com.netty.demo.netty190608_end.config.SessionUtil;
import com.netty.demo.netty190608_end.packet.LoginQuietRequestPacket;
import com.netty.demo.netty190608_end.packet.LoginQuietResponsePacket;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author 张佳琦
 * @ClassName: LoginQuietRequestHandler
 * @Description: 用户登出
 * @date 2019/6/5 9:55
 */
// 1. 加上注解标识，表明该 handler 是可以多个 channel 共享的
@ChannelHandler.Sharable
public class LoginQuietRequestHandler extends SimpleChannelInboundHandler<LoginQuietRequestPacket> {
    public static final LoginQuietRequestHandler INSTANCE = new LoginQuietRequestHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginQuietRequestPacket loginQuietRequestPacket) throws Exception {
        Channel channel = channelHandlerContext.channel();
        Session session = SessionUtil.getSession(channel);
//        SessionUtil.unBindSession(channel);     通道关闭时，在LoginRequestHandler中调用了解绑操作
        LoginQuietResponsePacket loginQuietResponsePacket = new LoginQuietResponsePacket();
        loginQuietResponsePacket.setCode("0000");
        loginQuietResponsePacket.setMsg("退出登录成功");
        channel.writeAndFlush(loginQuietResponsePacket);
        System.out.println(session.getUserName()+"退出登录！！！");
        channelHandlerContext.channel().close();
    }
}
