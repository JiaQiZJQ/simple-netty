package com.netty.demo.netty190608_end.handler.server;

import com.netty.demo.netty190608_end.config.Session;
import com.netty.demo.netty190608_end.config.SessionUtil;
import com.netty.demo.netty190608_end.packet.GroupSendMsgRequestPacket;
import com.netty.demo.netty190608_end.packet.GroupSendMsgResponsePacket;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * @author 张佳琦
 * @ClassName: GroupSendMsgRequestHandler
 * @Description: 群聊处理handler
 * @date 2019/6/8 20:50
 */
// 1. 加上注解标识，表明该 handler 是可以多个 channel 共享的
@ChannelHandler.Sharable
public class GroupSendMsgRequestHandler extends SimpleChannelInboundHandler<GroupSendMsgRequestPacket> {

    public static final GroupSendMsgRequestHandler INSTANCE = new GroupSendMsgRequestHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, GroupSendMsgRequestPacket groupSendMsgRequestPacket) throws Exception {
        Channel channel = channelHandlerContext.channel();
        String groupId = groupSendMsgRequestPacket.getGroupId();
        Session session = SessionUtil.getSession(channel);
        System.out.println("服务器接收到" + session.getUserName() + "的群聊消息:" + groupSendMsgRequestPacket.getMsg());
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
        if(channelGroup == null){
            return;
        }
        GroupSendMsgResponsePacket groupSendMsgResponsePacket = new GroupSendMsgResponsePacket();
        groupSendMsgResponsePacket.setGroupId(groupId);
        groupSendMsgResponsePacket.setFromUserName(session.getUserName());
        groupSendMsgResponsePacket.setMsg(groupSendMsgRequestPacket.getMsg());
        channelGroup.writeAndFlush(groupSendMsgResponsePacket);
    }
}
