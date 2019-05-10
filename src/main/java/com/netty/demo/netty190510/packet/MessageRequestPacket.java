package com.netty.demo.netty190510.packet;

import com.netty.demo.netty190508_1.Command;
import com.netty.demo.netty190508_1.Packet;

/**
 * @Auther: ZhangJiaQi
 * @Date: 2019/5/10 15:20
 * @Description:
 */
public class MessageRequestPacket extends Packet {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_REQUEST;
    }
}
