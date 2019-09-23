package yomo.study.netty.lesson5;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @program: yomo
 * @description:
 * @author: hh
 * @create: 2019-09-22 22:35
 **/
public class MyProtobufHandler extends SimpleChannelInboundHandler<DataInfo.Students> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DataInfo.Students msg) throws Exception {
        System.out.println(msg.getName());
        System.out.println(msg.getAge());
        System.out.println(msg.getAddress());

    }

}
