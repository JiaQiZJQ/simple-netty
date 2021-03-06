package com.netty.demo.netty190523;

import com.netty.demo.netty190523.config.PacketDecoder;
import com.netty.demo.netty190523.config.PacketEncoder;
import com.netty.demo.netty190523.handler.server.AuthHandler;
import com.netty.demo.netty190523.handler.server.LoginRequestHandler;
import com.netty.demo.netty190523.handler.server.MessageRequestHandler;
import com.netty.demo.netty190523.handler.server.Spliter;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @Auther: ZhangJiaQi
 * @Date: 2019/5/10 10:20
 * @Description:
 */
public class NettyServer {

    public static void main(String[] args){
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        serverBootstrap
                .group(bossGroup,workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {

                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        nioSocketChannel.pipeline().addLast(new Spliter());
                        nioSocketChannel.pipeline().addLast(new PacketDecoder());
                        nioSocketChannel.pipeline().addLast(new LoginRequestHandler());
                        nioSocketChannel.pipeline().addLast(new AuthHandler());
                        nioSocketChannel.pipeline().addLast(new MessageRequestHandler());
                        nioSocketChannel.pipeline().addLast(new PacketEncoder());
                    }
                });
        serverBootstrap.bind(8000);

    }
}
