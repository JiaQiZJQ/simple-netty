package com.netty.demo.netty190603;

import com.netty.demo.netty190603.config.PacketDecoder;
import com.netty.demo.netty190603.config.PacketEncoder;
import com.netty.demo.netty190603.handler.client.ClientHandler;
import com.netty.demo.netty190603.handler.client.LoginResponseHandler;
import com.netty.demo.netty190603.handler.client.MessageResponseHandler;
import com.netty.demo.netty190603.handler.server.Spliter;
import com.netty.demo.netty190603.packet.MessageRequestPacket;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: ZhangJiaQi
 * @Date: 2019/5/10 10:12
 * @Description:
 */
public class NettyClient {
    public static void main(String[] args){
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        bootstrap
                .group(workerGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new Spliter());
                        socketChannel.pipeline().addLast(new PacketDecoder());
                        socketChannel.pipeline().addLast(new ClientHandler());
                        socketChannel.pipeline().addLast(new MessageResponseHandler());
                        socketChannel.pipeline().addLast(new LoginResponseHandler());
                        socketChannel.pipeline().addLast(new PacketEncoder());
                    }
                });
        connect("127.0.0.1",8000,bootstrap);
    }

    /**
     * 封装连接方法，进行失败重试，可以自定义重试5次之类的
     * @param host
     * @param port
     * @param bootstrap
     */
    public static void connect(String host,int port,Bootstrap bootstrap){
        bootstrap.connect(host,port).addListener(future -> {
            if(!future.isSuccess()) {
                System.out.println("连接失败进行重试,俩分钟后重试");
                bootstrap.group().schedule(() -> connect(host, port, bootstrap),2, TimeUnit.SECONDS);
            }else {
                Channel channel = ((ChannelFuture) future).channel();
                startConsoThread(channel);
            }
        });
    }

    public static void startConsoThread(Channel channel){
        /**
         * 为什么要在这里新创建一个线程来进行接收控制台输入的数据和发送给服务端呢？
         * 解答：
         *      因为调用本办法是在监听中，而监听是用来监视客户端是否与服务端连接上了的
         *      监听会一直不间断的获取结果，一旦连接成功了，就会调用本方法来打开控制台接收数据
         *      可是此时还没有执行到ClientHandler的channelActive方法，也就表示还没有进行登录操作
         *      所以如果我们在本方法中直接使用while循环的话，就会造成主线程堵塞在这里，无法继续执行
         *      ClientHandler的channelActive方法，而没有登录也就导致LoginUtil.hasLogin(channel)
         *      这个判断一直为false，所以造成了无限死循环
         */
        new Thread(() -> {
            while (!Thread.interrupted()){
                    System.out.println("输入消息发送至服务器：");
                    Scanner scanner = new Scanner(System.in);
                    String s = scanner.nextLine();
                    System.out.println("输入接收方ID：");
                    String id = scanner.nextLine();
                    MessageRequestPacket messageRequestPacket = new MessageRequestPacket();
                    messageRequestPacket.setMsg(s);
                    messageRequestPacket.setToUserId(id);
                    channel.writeAndFlush(messageRequestPacket);
            }
        }).start();
    }
}
