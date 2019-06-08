package com.netty.demo.netty190604.config.console;

import com.netty.demo.netty190604.packet.LoginQuietRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author 张佳琦
 * @ClassName: LoginQuietConsoleCommmand
 * @Description: 登出指令
 * @date 2019/6/5 9:49
 */
public class LoginQuietConsoleCommmand implements ConsoleCommand{
    @Override
    public void exec(Scanner scanner, Channel channel) {
        LoginQuietRequestPacket loginQuietRequestPacket = new LoginQuietRequestPacket();
        channel.writeAndFlush(loginQuietRequestPacket);
    }
}
