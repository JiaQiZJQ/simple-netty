package com.netty.demo.netty190608_end.packet;

import com.netty.demo.netty190508_1.Command;
import com.netty.demo.netty190508_1.Packet;

/**
 * @author 张佳琦
 * @ClassName: LoginQuietResponsePacket
 * @Description: 登出应答
 * @date 2019/6/5 9:53
 */
public class LoginQuietResponsePacket extends Packet {
    private String code;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public Byte getCommand() {
        return Command.LOGIN_QUIET_RESULT;
    }
}
