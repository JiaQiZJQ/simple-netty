package com.netty.demo.netty190608_end.config.console;

import com.netty.demo.netty190608_end.packet.CreateGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author 张佳琦
 * @ClassName: CreateGroupConsoleCommand
 * @Description: 抽象控制台操作子类：创建群聊
 * @date 2019/6/4 10:28
 */
public class CreateGroupConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        CreateGroupRequestPacket createGroupRequestPacket = new CreateGroupRequestPacket();
        System.out.println("【拉人群聊】输入用户ID，多个人使用‘，’分隔");
        String ids = scanner.next();
        createGroupRequestPacket.setUserIdList(Arrays.asList(ids.split(",")));
        channel.writeAndFlush(createGroupRequestPacket);
    }
}
