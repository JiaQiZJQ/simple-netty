package com.netty.demo.netty190608_end.packet;

import com.netty.demo.netty190508_1.Command;
import com.netty.demo.netty190508_1.Packet;

/**
 * @author 张佳琦
 * @ClassName: LoginQuietRequestPacket
 * @Description: 登出请求
 * @date 2019/6/5 9:50
 */
public class LoginQuietRequestPacket extends Packet {
    @Override
    public Byte getCommand() {
        return Command.LOGIN_QUIET_REQUEST;
    }
}
