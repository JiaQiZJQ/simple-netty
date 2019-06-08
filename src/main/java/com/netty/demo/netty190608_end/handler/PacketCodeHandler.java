package com.netty.demo.netty190608_end.handler;

import com.netty.demo.netty190508_1.Packet;
import com.netty.demo.netty190608_end.config.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;

import java.util.List;

/**
 * @author 张佳琦
 * @ClassName: PacketCodeHandler
 * @Description: 统一编码解码
 * @date 2019/6/8 21:55
 */
@ChannelHandler.Sharable
public class PacketCodeHandler extends MessageToMessageCodec<ByteBuf, Packet> {

    public static final PacketCodeHandler INSTANCE = new PacketCodeHandler();

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Packet packet, List<Object> list) throws Exception {
        ByteBuf byteBuf = PacketCodeC.INSTANCE.encode(channelHandlerContext.alloc().ioBuffer(), packet);
        list.add(byteBuf);
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        list.add(PacketCodeC.INSTANCE.decode(byteBuf));
    }
}
