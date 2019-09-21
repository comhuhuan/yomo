package yomo.study.netty.lesson1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @program: yomo
 * @description: netty第一课
 * @author: hh
 * @create: 2019-09-20 23:48
 **/
public class TestServer {
    public static void main(String[] args) throws InterruptedException {
        //死循环处理组 接客的
        NioEventLoopGroup boss = new NioEventLoopGroup();
        //工作线程组 店小二
        NioEventLoopGroup work = new NioEventLoopGroup();
        try {
            // 服务端启动器
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            // 同过反射进行获取 NioServerSocketChannel
            serverBootstrap.group(boss, work).channel(NioServerSocketChannel.class)
                    .childHandler(new TestChannelInitializer());
            ChannelFuture cf = serverBootstrap.bind(9999).sync();

            cf.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }

    }
}
