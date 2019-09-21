package yomo.study.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.channels.SocketChannel;

/**
 * <p>Title:InSocksHandler
 * <p>Description:
 * <p>Modified History:
 *
 * @author HH
 * @date 2018/7/26 9:48
 */
public class InSocksHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }


    class OutboundHandler extends ChannelInboundHandlerAdapter {


        private final SocketChannel inboundChannel;

        private String name;

        OutboundHandler(SocketChannel inboundChannel, String name) {
            this.inboundChannel = inboundChannel;
            this.name = name;
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {


        }

        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            super.channelReadComplete(ctx);
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            super.exceptionCaught(ctx, cause);
        }
    }
}
