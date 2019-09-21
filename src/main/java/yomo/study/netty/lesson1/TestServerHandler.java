package yomo.study.netty.lesson1;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;


/**
 * @program: yomo
 * @description:
 * @author: hh
 * @create: 2019-09-21 14:12
 **/
public class TestServerHandler extends SimpleChannelInboundHandler<HttpObject> {


    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        super.channelUnregistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        super.userEventTriggered(ctx, evt);
    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        super.channelWritabilityChanged(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        //会有两个 msg进来  第一个是httprequest 另外一个是EmptyLastHttpContent
        Channel channel = ctx.channel();

        System.out.println(msg.getClass());
        if (msg instanceof HttpRequest) {
            HttpRequest httpRequest = (HttpRequest) msg;
            System.out.println(httpRequest.method().name());
            System.out.println(httpRequest.uri());
            System.out.println(httpRequest.protocolVersion());

            ByteBuf hello_world = Unpooled.copiedBuffer("Hello World", CharsetUtil.UTF_8);
            // 构造响应
            DefaultFullHttpResponse defaultFullHttpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, hello_world);

            defaultFullHttpResponse.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            // 不设置长度客户端不知道是否已经读完
            defaultFullHttpResponse.headers().set(HttpHeaderNames.CONTENT_LENGTH, hello_world.readableBytes());
            ctx.writeAndFlush(defaultFullHttpResponse);
        }


    }
}
