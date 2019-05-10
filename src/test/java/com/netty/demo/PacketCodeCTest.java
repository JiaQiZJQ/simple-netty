package com.netty.demo;

import com.alibaba.fastjson.JSON;
import com.netty.demo.netty190508_1.LoginRequestPacket;
import com.netty.demo.netty190508_1.Packet;
import com.netty.demo.netty190508_1.PacketCodeC;
import com.netty.demo.netty190508_1.serialize.Serializer;
import com.netty.demo.netty190508_1.serialize.impl.JSONSerializer;
import io.netty.buffer.ByteBuf;
import org.junit.Assert;
import org.junit.Test;

public class PacketCodeCTest {
    @Test
    public void encode() {

//        Serializer serializer = new JSONSerializer();
//        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
//
//        loginRequestPacket.setVersion(((byte) 1));
//        loginRequestPacket.setUserId("123");
//        loginRequestPacket.setUsername("zhangsan");
//        loginRequestPacket.setPassword("password");
//        System.out.println(JSON.toJSONString(loginRequestPacket));
//        PacketCodeC packetCodeC = new PacketCodeC();
//        ByteBuf byteBuf = packetCodeC.encode(loginRequestPacket);
//        System.out.println(byteBuf);
//        Packet decodedPacket = packetCodeC.decode(byteBuf);
//
//        Assert.assertArrayEquals(serializer.serialize(loginRequestPacket), serializer.serialize(decodedPacket));

    }
}