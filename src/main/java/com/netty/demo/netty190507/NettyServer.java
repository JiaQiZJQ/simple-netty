package com.netty.demo.netty190507;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

public class NettyServer {
    public static void main(String[] args){
        /**
         * 生成俩个线程池
         * bossGroup 用来获取新连接的线程
         * workerGroup  用来处理每一条连接的数据读写即处理具体业务
         */
        NioEventLoopGroup  bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup  workerGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        /**
         * 启动netty服务端的基础三步
         * 1（group）、线程模型：对每一个新连接的线程根据线程模型生成线程
         * 2（channel）、IO模型：指定处理数据使用的IO方式
         * 3（childHandler）、读写处理逻辑：即如何处理具体业务
         */
        serverBootstrap = serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        System.out.println(ch.read().toString());
                    }
                })
//                .bind(8000)
        ;
        bind(serverBootstrap,8000);//此处为绑定端口处添加了监听器，根据判断绑定结果来执行定义好的代码
    }

    /**
     * 封装绑定端口方法，自定义端口绑定失败业务
     * @param serverBootstrap
     * @param port
     */
    private static void bind(ServerBootstrap serverBootstrap, int port){
//        serverBootstrap.bind(port).addListener(new GenericFutureListener<Future<? super Void>>() {
//            @Override
//            public void operationComplete(Future<? super Void> future) throws Exception {
//                if(!future.isSuccess()){
//                    System.out.println("绑定端口失败"+port);
//                    serverBootstrap.bind(port+1);
//                }
//            }
//        });
        serverBootstrap.bind(port).addListener(future -> {
            if(!future.isSuccess()){
                    System.out.println("绑定端口失败"+port);
                    bind(serverBootstrap,port+1);//递归调用自身进行端口绑定
            }
        });
    }
}
