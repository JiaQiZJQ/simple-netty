package com.netty.demo.netty190604.config;

import io.netty.channel.Channel;
import io.netty.util.Attribute;

public class LoginUtil {

    public static void markAsLogin(Channel channel){
        channel.attr(Attributes.LOGIN).set(true);
    }

    public static Boolean hasLogin(Channel channel){
        Attribute<Boolean> attr = channel.attr(Attributes.LOGIN);
        return attr.get()!=null;
    }
}