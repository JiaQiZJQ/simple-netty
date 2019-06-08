package com.netty.demo.netty190608_end.packet;

import com.netty.demo.netty190508_1.Command;
import com.netty.demo.netty190508_1.Packet;

import java.util.List;

/**
 * @author 张佳琦
 * @ClassName: CreateGroupRequestPacket
 * @Description: 创建群聊请求
 * @date 2019/6/4 10:33
 */
public class CreateGroupRequestPacket extends Packet {

    private List<String> userIdList;

    public List<String> getUserIdList() {
        return userIdList;
    }

    public void setUserIdList(List<String> userIdList) {
        this.userIdList = userIdList;
    }

    @Override
    public Byte getCommand() {
        return Command.CREATE_GROUP_REQUEST;
    }
}
