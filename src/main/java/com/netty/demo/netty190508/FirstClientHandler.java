package com.netty.demo.netty190508;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;
import java.util.Date;

/**
 * @Auther: ZhangJiaQi
 * @Date: 2019/5/8 14:14
 * @Description:
 */
public class FirstClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    //本方法会在客户端连接建立成功后调用
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(new Date() + "客户端开始写出数据");
        //获取数据
        ByteBuf byteBuf = this.getByteBuf(ctx);
        //写数据
        ctx.channel().writeAndFlush(byteBuf);


    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        System.out.println(new Date() + "客户端接收到数据==>" + byteBuf.toString(Charset.forName("utf-8")));

        //客户端响应服务端消息
        ByteBuf firstByteBuf = this.getFirstByteBuf(ctx);
        ctx.channel().writeAndFlush(firstByteBuf);
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx){
        //1.获取二进制抽象ByteBuf，netty的中的数据都是以bytebuf为单位的
        ByteBuf buffer = ctx.alloc().buffer();
        //2.准备数据，指定字符串的字符集为utf-8
        byte[] bytes = "你好，服务器！！！".getBytes(Charset.forName("UTF-8"));
        //3.填充数据到ByteBuf
        buffer.writeBytes(bytes);
        return buffer;
    }

    private ByteBuf getFirstByteBuf(ChannelHandlerContext ctx){
        //1.获取二进制抽象ByteBuf，netty的中的数据都是以bytebuf为单位的
        ByteBuf buffer = ctx.alloc().buffer();
        //2.准备数据，指定字符串的字符集为utf-8
        byte[] bytes = "见到你和开心，服务器！！！".getBytes(Charset.forName("UTF-8"));
        //3.填充数据到ByteBuf
        buffer.writeBytes(bytes);
        return buffer;
    }
}
