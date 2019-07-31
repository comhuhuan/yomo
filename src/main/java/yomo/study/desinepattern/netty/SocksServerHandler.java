package yomo.study.desinepattern.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.socks.*;

/**
 * <p>Title:SocksServerHandler
 * <p>Description:
 * <p>Modified History:
 *
 * @author HH
 * @date 2018/7/25 16:48
 */
public class SocksServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        SocksRequest msg1 = (SocksRequest) msg;
        SocksRequestType socksRequestType = msg1.requestType();
        switch (socksRequestType) {
            case INIT:
                //添加cmd解码器

                ctx.pipeline().addFirst("cmdDecoder", new SocksCmdRequestDecoder());
                //简单起见，无需认证
                ctx.pipeline().write(new SocksInitResponse(SocksAuthScheme.NO_AUTH));
                break;
            case AUTH:
                ctx.pipeline().addFirst("cmdDecoder", new SocksCmdRequestDecoder());
                //直接成功
                ctx.pipeline().write(new SocksAuthResponse(SocksAuthStatus.SUCCESS));
                break;
            case CMD:
                SocksCmdRequest req = (SocksCmdRequest) msg1;
                if (req.cmdType() == SocksCmdType.CONNECT) {
                    //添加处理连接的handler
                    ctx.pipeline().addLast("SocksServerConnectHandler", new SocksServerConnectHandler());
                    ctx.pipeline().remove(this);
                } else {
                    ctx.channel().close();
                }
                break;
            case UNKNOWN:
                break;
        }
        msg.toString();
        ChannelHandlerContext read = ctx.read();
        System.out.println(msg.toString());

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}