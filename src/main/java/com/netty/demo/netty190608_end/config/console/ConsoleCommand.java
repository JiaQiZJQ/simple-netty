package com.netty.demo.netty190608_end.config.console;

import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author 张佳琦
 * @ClassName: ConsoleCommand
 * @Description: TODO
 * @date 2019/6/4 10:22
 */
public interface ConsoleCommand {

    void exec(Scanner scanner, Channel channel);
}
