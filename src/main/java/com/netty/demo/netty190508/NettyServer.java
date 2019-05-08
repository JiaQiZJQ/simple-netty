package com.netty.demo.netty190508;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyServer {
    public static void main(String[] args){

        NioEventLoopGroup  bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup  workerGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();

        serverBootstrap = serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new FirstServerHandler());
                    }
                });
        bind(serverBootstrap,8000);//此处为绑定端口处添加了监听器，根据判断绑定结果来执行定义好的代码
    }

    /**
     * 封装绑定端口方法，自定义端口绑定失败业务
     * @param serverBootstrap
     * @param port
     */
    private static void bind(ServerBootstrap serverBootstrap, int port){
//
        serverBootstrap.bind(port).addListener(future -> {
            if(!future.isSuccess()){
                    System.out.println("绑定端口失败"+port);
                    bind(serverBootstrap,port+1);//递归调用自身进行端口绑定
            }
        });
    }
}
