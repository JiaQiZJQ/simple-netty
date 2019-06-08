package com.netty.demo.netty190604.config.console;

import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author 张佳琦
 * @ClassName: ConsoleCommandManage
 * @Description: TODO
 * @date 2019/6/4 10:23
 */
public class ConsoleCommandManage implements ConsoleCommand {

    private Map<String,ConsoleCommand> consoleCommandMap;

    public ConsoleCommandManage() {
        consoleCommandMap = new HashMap<>();
        consoleCommandMap.put("sendToUser",new SendToUserConsoleCommand());
        consoleCommandMap.put("createGroup", new CreateGroupConsoleCommand());
        consoleCommandMap.put("login", new LoginConsoleCommand());
        consoleCommandMap.put("loginQuiet", new LoginQuietConsoleCommmand());
    }

    @Override
    public void exec(Scanner scanner, Channel channel) {
        String command = scanner.next();
        ConsoleCommand consoleCommand = consoleCommandMap.get(command);
        if(consoleCommand != null){
            consoleCommand.exec(scanner,channel);
        }else {
            System.out.println("无法识别【" + command + "】该指令");
        }
    }
}
