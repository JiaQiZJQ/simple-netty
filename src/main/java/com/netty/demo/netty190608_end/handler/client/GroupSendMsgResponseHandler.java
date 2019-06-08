package com.netty.demo.netty190608_end.handler.client;

import com.netty.demo.netty190608_end.packet.GroupSendMsgResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author 张佳琦
 * @ClassName: GroupSendMsgResponseHandler
 * @Description: 接受群聊
 * @date 2019/6/8 20:56
 */
public class GroupSendMsgResponseHandler extends SimpleChannelInboundHandler<GroupSendMsgResponsePacket> {

    public static final GroupSendMsgResponseHandler INSTANCE = new GroupSendMsgResponseHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, GroupSendMsgResponsePacket groupSendMsgResponsePacket) throws Exception {
        System.out.println("来自房间【" + groupSendMsgResponsePacket.getGroupId() + "】的 " + groupSendMsgResponsePacket.getFromUserName() + " 的群聊消息："+groupSendMsgResponsePacket.getMsg());
    }
}
