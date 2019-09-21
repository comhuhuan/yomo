package yomo.study.netty.lesson3;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @program: yomo
 * @description:
 * @author: hh
 * @create: 2019-09-21 16:34
 **/
public class MyChartClientHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // super.channelActive(ctx);
        ctx.writeAndFlush("hello");
    }

}
