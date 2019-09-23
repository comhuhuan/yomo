package yomo.study.netty.lesson5;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @program: yomo
 * @description:
 * @author: hh
 * @create: 2019-09-22 22:43
 **/
public class MyProtobufClientHandler extends SimpleChannelInboundHandler<DataInfo.Students> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DataInfo.Students msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        DataInfo.Students build = DataInfo.Students.newBuilder().setAge(12).setAddress("sadf").setName("asdfsf").build();
        // byte[] bytes = build.toByteArray();
        // ByteBuf byteBuf = Unpooled.copiedBuffer(bytes);
        // ctx.writeAndFlush(byteBuf);
        ctx.writeAndFlush(build);
    }
}
