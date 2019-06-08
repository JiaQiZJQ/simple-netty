package com.netty.demo.netty190608_end.packet;

import com.netty.demo.netty190508_1.Command;
import com.netty.demo.netty190508_1.Packet;

/**
 * @author 张佳琦
 * @ClassName: GroupSendMsgResponsePacket
 * @Description: 群聊消息应答packet
 * @date 2019/6/8 20:43
 */
public class GroupSendMsgResponsePacket extends Packet {

    private String groupId;
    private String fromUserName;
    private String msg;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
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
        return Command.GROUP_MSG_RESULT;
    }
}
