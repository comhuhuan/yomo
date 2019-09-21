package yomo.study.netty.lesson3;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @program: yomo
 * @description:
 * @author: hh
 * @create: 2019-09-21 17:06
 **/
public class MyChartServerHandler extends SimpleChannelInboundHandler<String> {
    private static DefaultChannelGroup map = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel ch = ctx.channel();
        map.forEach(channel -> {
                    if (channel != ch) {
                        channel.writeAndFlush(channel.remoteAddress() + "发送了消息" + msg + "\r\n");
                    } else {
                        channel.writeAndFlush("自己发送了消息" + msg + "\r\n");
                    }
                }

        );
    }


    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("加入连接");
        Channel channel = ctx.channel();
        map.writeAndFlush("服务器" + ctx.channel().remoteAddress() + "加入了连接\n");
        map.add(channel);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        map.writeAndFlush("服务器" + ctx.channel().remoteAddress() + "断开了连接\n");

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        cause.printStackTrace();


        ctx.close();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        // map.writeAndFlush(channel.remoteAddress() + "上线了");
        System.out.println(channel.remoteAddress() + "上线了");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        // map.writeAndFlush(channel.remoteAddress() + "下线了");
        System.out.println(channel.remoteAddress() + "上线了");

    }
}
