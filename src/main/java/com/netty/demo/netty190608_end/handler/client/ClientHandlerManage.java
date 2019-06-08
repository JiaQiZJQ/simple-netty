package com.netty.demo.netty190608_end.handler.client;

import com.netty.demo.netty190508_1.Command;
import com.netty.demo.netty190508_1.Packet;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 张佳琦
 * @ClassName: ClientHandlerManage
 * @Description: 统一管理逻辑handler
 * @date 2019/6/8 22:19
 */
public class ClientHandlerManage extends SimpleChannelInboundHandler<Packet> {

    public static final ClientHandlerManage INSTANCE = new ClientHandlerManage();

    private Map<Byte, SimpleChannelInboundHandler> map;

    public ClientHandlerManage() {
        map = new HashMap<>();
        map.put(Command.CREATE_GROUP_RESULT, CreateGroupResponseHandler.INSTANCE);
        map.put(Command.GROUP_MSG_RESULT, GroupSendMsgResponseHandler.INSTANCE);
        map.put(Command.LOGIN_RESULT, LoginResponseHandler.INSTANCE);
        map.put(Command.LOGIN_QUIET_RESULT, LoginQuietResponseHandler.INSTANCE);
        map.put(Command.MESSAGE_RESULT, MessageResponseHandler.INSTANCE);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Packet packet) throws Exception {
        map.get(packet.getCommand()).channelRead(channelHandlerContext,packet);

    }
}
