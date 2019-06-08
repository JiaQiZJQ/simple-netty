package com.netty.demo.netty190608_end;

import com.netty.demo.netty190608_end.handler.PacketCodeHandler;
import com.netty.demo.netty190608_end.handler.server.*;
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
                        nioSocketChannel.pipeline().addLast(new Spliter());//分裂器不允许共享
                        nioSocketChannel.pipeline().addLast(PacketCodeHandler.INSTANCE);//统一编解码
                        nioSocketChannel.pipeline().addLast(LoginRequestHandler.INSTANCE);//登录必须放到校验之前，所以无法统一到serverhandler中
                        nioSocketChannel.pipeline().addLast(AuthHandler.INSTANCE);
                        nioSocketChannel.pipeline().addLast(ServerHandlerManage.INSTANCCE);
                    }
                });
        serverBootstrap.bind(8000);

    }
}
