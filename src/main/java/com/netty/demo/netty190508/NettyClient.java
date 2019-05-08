package com.netty.demo.netty190508;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: ZhangJiaQi
 * @Date: 2019/5/8 14:06
 * @Description:
 */
public class NettyClient {
    public static void main(String[] args){
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap
                .group(workerGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel sc) throws Exception {
                        sc
                            //获取逻辑处理链
                            .pipeline()
                            //添加自定义的逻辑处理器
                            .addLast(new FirstClientHandler());
                    }
                });
        connect("127.0.0.1", 8000, bootstrap);
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
//                connect(host, port, bootstrap);//连接失败，递归调用自身进行重连
            }
        });
    }
}
