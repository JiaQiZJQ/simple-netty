package com.netty.demo.netty190608_end.packet;

import com.netty.demo.netty190508_1.Command;
import com.netty.demo.netty190508_1.Packet;

/**
 * @author 张佳琦
 * @ClassName: GroupSendMsgRequestPacket
 * @Description: 群聊请求packet
 * @date 2019/6/8 20:40
 */
public class GroupSendMsgRequestPacket extends Packet {

    private String groupId;
    private String msg;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public Byte getCommand() {
        return Command.GROUP_MSG_REQUEST;
    }
}
