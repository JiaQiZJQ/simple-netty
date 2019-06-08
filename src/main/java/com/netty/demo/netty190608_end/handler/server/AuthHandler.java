package com.netty.demo.netty190608_end.handler.server;

import com.netty.demo.netty190608_end.config.LoginUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author 张佳琦
 * @ClassName: AuthHandler
 * @Description: 在接受消息之前校验是否登录
 * @date 2019/6/1 11:04
 */
// 1. 加上注解标识，表明该 handler 是可以多个 channel 共享的
@ChannelHandler.Sharable
public class AuthHandler extends ChannelInboundHandlerAdapter {
    public static final AuthHandler INSTANCE = new AuthHandler();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(!LoginUtil.hasLogin(ctx.channel())){
            System.out.println("未登录，请重新登录！！！");
            ctx.channel().close();
            return;
        }
        System.out.println("校验通过，继续执行之后的逻辑！！！");
        ctx.pipeline().remove(this);
        super.channelRead(ctx, msg);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("通过校验，移除登陆校验！！！");
    }
}
