package com.netty.demo.netty190608_end.config.console;

import com.netty.demo.netty190608_end.packet.GroupSendMsgRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author 张佳琦
 * @ClassName: GroupSendMsgConsoleCommand
 * @Description: 群聊操作
 * @date 2019/6/8 20:19
 */
public class GroupSendMsgConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("请输入您要发送消息的房间号：");
        String groupId = scanner.next();
        System.out.println("请输入您要发送的消息：");
        String msg = scanner.next();
        GroupSendMsgRequestPacket groupSendMsgRequestPacket = new GroupSendMsgRequestPacket();
        groupSendMsgRequestPacket.setGroupId(groupId);
        groupSendMsgRequestPacket.setMsg(msg);
        channel.writeAndFlush(groupSendMsgRequestPacket);
    }
}
