package com.netty.demo.netty190603.packet;

import com.netty.demo.netty190508_1.Command;
import com.netty.demo.netty190508_1.Packet;

/**
 * @author 张佳琦
 * @ClassName: MessageResponsePackge
 * @Description: 重写消息返回类
 * @date 2019/6/3 20:01
 */
public class MessageResponsePacket extends Packet {
    private String fromUserId;
    private String fromUserName;
    private String msg;

    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_RESULT;
    }
}
