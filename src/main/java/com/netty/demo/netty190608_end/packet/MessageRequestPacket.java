package com.netty.demo.netty190608_end.packet;

import com.netty.demo.netty190508_1.Command;
import com.netty.demo.netty190508_1.Packet;

/**
 * @author 张佳琦
 * @ClassName: MessageRequestPacket
 * @Description: 重新定义下次发送类
 * @date 2019/6/3 19:55
 */
public class MessageRequestPacket extends Packet {
    private String toUserId;
    private String msg;

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_REQUEST;
    }
}
