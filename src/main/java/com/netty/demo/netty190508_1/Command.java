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
}