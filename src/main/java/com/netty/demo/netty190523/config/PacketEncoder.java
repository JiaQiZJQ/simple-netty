package com.netty.demo.netty190523.config;

import com.netty.demo.netty190508_1.Packet;
import com.netty.demo.netty190508_1.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author 张佳琦
 * @ClassName: PacketEncoder
 * @Description: TODO
 * @date 2019/5/22 10:32
 */
public class PacketEncoder extends MessageToByteEncoder<Packet> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Packet packet, ByteBuf byteBuf) throws Exception {
        PacketCodeC.INSTANCE.encode(byteBuf,packet);
    }
}
