package com.netty.demo.netty190608_end.handler.server;

import com.netty.demo.netty190508_1.Command;
import com.netty.demo.netty190508_1.Packet;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 张佳琦
 * @ClassName: ServerHandlerManage
 * @Description: 对逻辑处理的handler进行统一处理，缩短传播路劲
 *                 缩短传播路径： 之前会依次对逻辑handler进行判断，现在只需要从map中取
 * @date 2019/6/8 22:05
 */
public class ServerHandlerManage extends SimpleChannelInboundHandler<Packet> {

    public static final ServerHandlerManage INSTANCCE = new ServerHandlerManage();

    private Map<Byte, SimpleChannelInboundHandler< ? extends Packet>> map;

    private ServerHandlerManage() {
        map = new HashMap<>();
        map.put(Command.CREATE_GROUP_REQUEST, CreateGroupRequestHandler.INSTANCE);
        map.put(Command.GROUP_MSG_RESULT, GroupSendMsgRequestHandler.INSTANCE);
        map.put(Command.LOGIN_REQUEST, LoginRequestHandler.INSTANCE);
        map.put(Command.LOGIN_QUIET_REQUEST, LoginQuietRequestHandler.INSTANCE);
        map.put(Command.MESSAGE_REQUEST, MessageRequestHandler.INSTANCE);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Packet packet) throws Exception {
        map.get(packet.getCommand()).channelRead(channelHandlerContext,packet);
    }
}
