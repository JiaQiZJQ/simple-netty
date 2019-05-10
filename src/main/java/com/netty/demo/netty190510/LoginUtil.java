package com.netty.demo.netty190510;

import io.netty.channel.Channel;
import io.netty.util.Attribute;

/**
 * @Auther: ZhangJiaQi
 * @Date: 2019/5/10 15:29
 * @Description:
 */
public class LoginUtil {

    public static void markAsLogin(Channel channel){
        channel.attr(Attributes.LOGIN).set(true);
    }

    public static Boolean hasLogin(Channel channel){
        Attribute<Boolean> attr = channel.attr(Attributes.LOGIN);
        return attr.get()!=null;
    }
}
