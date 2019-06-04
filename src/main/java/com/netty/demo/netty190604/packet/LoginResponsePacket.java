package com.netty.demo.netty190604.packet;

import com.netty.demo.netty190508_1.Command;
import com.netty.demo.netty190508_1.Packet;

/**
 * @author 张佳琦
 * @ClassName: LoginResponsePacket
 * @Description: 登录返回对象，记录登录id
 * @date 2019/6/3 19:34
 */
public class LoginResponsePacket extends Packet {
    private String resultCode;
    private String resultMsg;
    private String userId;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public Byte getCommand() {
        return Command.LOGIN_RESULT;
    }
}
