package com.netty.demo.netty190508_1;

public interface Command {

    /**
     * 登陆指令
     */
    Byte LOGIN_REQUEST = 1;

    /**
     * 登陆结果指令
     */
    Byte LOGIN_RESULT = 2;

    /**
     * 客户端消息指令
     */
    Byte MESSAGE_REQUEST = 3;

    /**
     * 服务端应答消息指令
     */
    Byte MESSAGE_RESULT = 4;

    /**
     * 创建群聊指令
     */
    Byte CREATE_GROUP_REQUEST = 5;

    /**
     * 创建群聊应答指令
     */
    Byte CREATE_GROUP_RESULT = 6;

    /**
     * 登出指令指令
     */
    Byte LOGIN_QUIET_REQUEST = 7;

    /**
     * 登出应答指令
     */
    Byte LOGIN_QUIET_RESULT = 8;

    /**
     * 群聊指令
     */
    Byte GROUP_MSG_REQUEST = 9;

    /**
     * 群聊应答指令
     */
    Byte GROUP_MSG_RESULT = 10;
}