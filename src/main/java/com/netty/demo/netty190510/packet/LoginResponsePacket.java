package com.netty.demo.netty190510.packet;

import com.netty.demo.netty190508_1.Command;
import com.netty.demo.netty190508_1.Packet;

/**
 * @Auther: ZhangJiaQi
 * @Date: 2019/5/10 10:31
 * @Description:
 */
public class LoginResponsePacket extends Packet {

    private String resultCode;

    private String resultMsg;

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

    @Override
    public Byte getCommand() {
        return Command.LOGIN_RESULT;
    }
}
