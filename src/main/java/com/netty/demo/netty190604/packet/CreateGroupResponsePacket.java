package com.netty.demo.netty190604.packet;

import com.netty.demo.netty190508_1.Command;
import com.netty.demo.netty190508_1.Packet;

import java.util.List;

/**
 * @author 张佳琦
 * @ClassName: CreateGroupResponsePacket
 * @Description: 创建群聊应答
 * @date 2019/6/4 10:52
 */
public class CreateGroupResponsePacket extends Packet {
    private String groupId;
    private boolean success;
    private List<String> userNameList;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<String> getUserNameList() {
        return userNameList;
    }

    public void setUserNameList(List<String> userNameList) {
        this.userNameList = userNameList;
    }

    @Override
    public Byte getCommand() {
        return Command.CREATE_GROUP_RESULT;
    }
}
