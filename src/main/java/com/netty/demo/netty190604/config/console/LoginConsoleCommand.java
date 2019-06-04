package com.netty.demo.netty190604.config.console;

import com.netty.demo.netty190508_1.LoginRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author 张佳琦
 * @ClassName: LoginConsoleCommand
 * @Description: 执行登录命令的控制台操作
 * @date 2019/6/4 11:04
 */
public class LoginConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        System.out.println("请输入用户名：");
        String userName = scanner.next();
        System.out.println("请输入密码：");
        String passWord = scanner.next();
        loginRequestPacket.setUsername(userName);
        loginRequestPacket.setPassword(passWord);
        channel.writeAndFlush(loginRequestPacket);
    }
}
