package com.netty.demo.netty190604.handler.client;

import com.netty.demo.netty190604.packet.CreateGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author 张佳琦
 * @ClassName: CreateGroupResponseHandler
 * @Description: 接受创建群聊应答
 * @date 2019/6/4 11:09
 */
public class CreateGroupResponseHandler extends SimpleChannelInboundHandler<CreateGroupResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, CreateGroupResponsePacket createGroupResponsePacket) throws Exception {
        System.out.println("您已加入群聊"+ createGroupResponsePacket.getGroupId() +"】,该房间目前的人员有/n:" + createGroupResponsePacket.getUserNameList());
    }
}
