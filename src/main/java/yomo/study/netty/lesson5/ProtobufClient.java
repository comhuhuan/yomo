package yomo.study.netty.lesson5;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.io.IOException;

/**
 * @program: yomo
 * @description:
 * @author: hh
 * @create: 2019-09-22 22:39
 **/
public class ProtobufClient {
    public static void main(String[] args) throws InterruptedException, IOException {
        NioEventLoopGroup eventExecutors = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventExecutors).channel(NioSocketChannel.class).handler(new LoggingHandler(LogLevel.INFO)).
                    handler(new MyProtobufClientInitialzer());
            ChannelFuture localhost = bootstrap.connect("127.0.0.1", 9998).sync();
            localhost.channel().closeFuture().sync();
        } finally {
            eventExecutors.shutdownGracefully();
        }

    }
}
