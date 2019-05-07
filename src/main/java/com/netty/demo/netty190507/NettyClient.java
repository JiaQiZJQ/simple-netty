package com.netty.demo.netty190507;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.concurrent.TimeUnit;

public class NettyClient {
    public static void main(String[] args){
        NioEventLoopGroup workerGoup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        bootstrap
                //1.指定线程模型
                .group(workerGoup)
                //2.指定 IO 类型为 NIO
                .channel(NioSocketChannel.class)
                //3.IO 处理逻辑
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.write("hello word");
                    }
                });
//                .connect("127.0.0.1",8000).addListener(future -> {
//                                                                    if(!future.isSuccess()) System.out.println("连接失败");
//                                                                });
                connect("127.0.0.1", 8000, bootstrap);
    }

    /**
     * 封装连接方法，进行失败重试，可以自定义重试5次之类的
     * @param host
     * @param port
     * @param bootstrap
     */
    public static void connect(String host,int port,Bootstrap bootstrap){
        bootstrap.connect("127.0.0.1",8000).addListener(future -> {
            if(!future.isSuccess()) {
                System.out.println("连接失败进行重试,俩分钟后重试");
                bootstrap.group().schedule(() -> connect(host, port, bootstrap),2, TimeUnit.SECONDS);
//                connect(host, port, bootstrap);//连接失败，递归调用自身进行重连
            }
        });
    }
}
