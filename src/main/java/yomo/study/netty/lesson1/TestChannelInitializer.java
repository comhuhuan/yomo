package yomo.study.netty.lesson1;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * @program: yomo
 * @description:
 * @author: hh
 * @create: 2019-09-21 14:00
 **/
public class TestChannelInitializer extends ChannelInitializer<SocketChannel> {
    // 初始化一个chanal 从管道获取到pipeline
    @Override
    protected void initChannel(SocketChannel channel) throws Exception {

        ChannelPipeline pipeline = channel.pipeline();
        // pipeline.addLast("httpServerCodec", new HttpServerCodec());
        // pipeline.addLast("httpServerCodec", new HttpRequestEncoder());
        // 在通道中加入处理器
        pipeline.addLast("testServerHandler", new TestServerHandler());
        // pipeline.addLast()
        // pipeline.addLast("1111", new HttpResponseEncoder());


    }
}
