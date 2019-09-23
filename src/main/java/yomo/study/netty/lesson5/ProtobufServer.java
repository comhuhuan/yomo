package yomo.study.netty.lesson5;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @program: yomo
 * @description: Protobuf
 * @author: hh
 * @create: 2019-09-22 22:23
 **/
public class ProtobufServer {
    public static void main(String[] args) {
        NioEventLoopGroup work = new NioEventLoopGroup();
        NioEventLoopGroup boss = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap().group(boss, work).channel(NioServerSocketChannel.class)
                    .childHandler(new ProtobufServerChannalInitializer());
            ChannelFuture sync = serverBootstrap.bind(9998).sync();
            sync.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            work.shutdownGracefully();
            boss.shutdownGracefully();
        }
    }

}
