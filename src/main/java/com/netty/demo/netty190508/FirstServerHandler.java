package com.netty.demo.netty190508;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.Date;

/**
 * @Auther: ZhangJiaQi
 * @Date: 2019/5/8 14:31
 * @Description:
 */
public class FirstServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //1.拿到客户端数据
        ByteBuf byteBuf = (ByteBuf) msg;
        //2.打印客户端数据
        System.out.println(new Date() + "服务端接收到数据=>" + byteBuf.toString(Charset.forName("utf-8")));
        //3.拿到给客户端返回的数据
        ByteBuf buf = this.getByteBuf(ctx);
        //4.给客户端返回数据
        ctx.channel().writeAndFlush(buf);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //客户端首次连接成功，服务器主动推送消息
        ByteBuf firshByteBuf = this.getFirshByteBuf(ctx);
        ctx.channel().writeAndFlush(firshByteBuf);
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx){
        System.out.println("服务端开始准备写出数据");

        //1.准备数据
        byte[] bytes = "你好，客户端！！！".getBytes(Charset.forName("utf-8"));
        //2.拿到传输单位butebuf
        ByteBuf buffer = ctx.alloc().buffer();
        //3.填充数据
        buffer.writeBytes(bytes);
        return buffer;
    }

    private ByteBuf getFirshByteBuf(ChannelHandlerContext ctx){
        System.out.println("客户端首次接入，主动推送消息");

        //1.准备数据
        byte[] bytes = "恭喜你，客户端！！！".getBytes(Charset.forName("utf-8"));
        //2.拿到传输单位butebuf
        ByteBuf buffer = ctx.alloc().buffer();
        //3.填充数据
        buffer.writeBytes(bytes);
        return buffer;
    }
}
