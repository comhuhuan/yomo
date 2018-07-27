package study.desinepattern.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.socks.SocksCmdRequest;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.net.InetSocketAddress;

/**
 * <p>Title:SocksServerConnectHandler
 * <p>Description:
 * <p>Modified History:
 *
 * @author CVTE
 * @date 2018/7/25 17:51
 */
public class SocksServerConnectHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        final SocksCmdRequest socksCmdRequest = (SocksCmdRequest) msg;
        Channel inChannel = ctx.channel();
        NioEventLoopGroup client = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(client).channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true).handler(new InSocksHandler());
        bootstrap.connect(new InetSocketAddress(socksCmdRequest.host(), socksCmdRequest.port()));


    }
}
