package yomo.study.netty.lesson3;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @program: yomo
 * @description: client
 * @author: hh
 * @create: 2019-09-21 16:24
 **/
public class MyChartClient {
    public static void main(String[] args) throws InterruptedException, IOException {
        NioEventLoopGroup eventExecutors = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventExecutors).channel(NioSocketChannel.class).handler(new LoggingHandler(LogLevel.INFO)).
                    handler(new MyChartClientInitialzer());
            ChannelFuture localhost = bootstrap.connect("127.0.0.1", 7777).sync();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            Channel channel = localhost.channel();
            for (; ; ) {
                channel.writeAndFlush(bufferedReader.readLine() + "\r\n");
            }
        } finally {
            eventExecutors.shutdownGracefully();
        }

    }
}
