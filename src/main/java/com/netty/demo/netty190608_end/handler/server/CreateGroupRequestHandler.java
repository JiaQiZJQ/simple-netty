package com.netty.demo.netty190608_end.handler.server;

import com.netty.demo.netty190608_end.config.SessionUtil;
import com.netty.demo.netty190608_end.packet.CreateGroupRequestPacket;
import com.netty.demo.netty190608_end.packet.CreateGroupResponsePacket;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author 张佳琦
 * @ClassName: CreateGroupRequestHandler
 * @Description: 处理创建群聊请求
 * @date 2019/6/4 10:41
 */
// 1. 加上注解标识，表明该 handler 是可以多个 channel 共享的
@ChannelHandler.Sharable
public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {
    public static final CreateGroupRequestHandler INSTANCE = new CreateGroupRequestHandler();

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, CreateGroupRequestPacket createGroupRequestPacket) throws Exception {
        List<String> userIdList = createGroupRequestPacket.getUserIdList();//获取群成员id
        ChannelGroup channelGroup = new DefaultChannelGroup(channelHandlerContext.executor());//创建一个channel分组
        List<String> userNameList = new ArrayList<>();
        channelGroup.add(channelHandlerContext.channel());//先将自己加入群聊
        userNameList.add(SessionUtil.getSession(channelHandlerContext.channel()).getUserName());
        userIdList.stream().forEach(id ->{
            Channel channel = SessionUtil.getChannel(id);//根据userID获取channel信息
            if(channel != null && SessionUtil.hasLogin(channel)){
                channelGroup.add(channel);//加入分组
                userNameList.add(SessionUtil.getSession(channel).getUserName());
            }
        });

        CreateGroupResponsePacket createGroupResponsePacket = new CreateGroupResponsePacket();
        Random random = new Random();
        int i = random.nextInt(10000);
        createGroupResponsePacket.setGroupId(i + "");
        createGroupResponsePacket.setSuccess(true);
        createGroupResponsePacket.setUserNameList(userNameList);
        channelGroup.writeAndFlush(createGroupResponsePacket);
        //保存群聊信息
        SessionUtil.bingGroup(i+"",channelGroup);
        System.out.println("创建群聊成功【"+i+"】:" + userIdList);
    }
}
