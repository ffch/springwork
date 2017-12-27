package com.cff.springwork.netty.handler;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import io.netty.channel.ChannelHandler.Sharable;

@Component
@Sharable
public class NettyServerHandler extends SimpleChannelInboundHandler<String>{
	@Autowired(required = false)
	@Qualifier("closeFutureHandler")
	public Handler closeFutureHandler;
	@Autowired(required = false)
	@Qualifier("exceptionFutureHandler")
	public Handler exceptionFutureHandler;
	@Autowired
	@Qualifier("bussinessFutureHandler")
	public Handler bussinessFutureHandler;
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {		
//		System.out.println(((HandlerServiceImp) exportServiceMap.get("helloWorldService")).test());
//		// 返回客户端消息 - 我已经接收到了你的消息
		System.out.println(Thread.currentThread().getName()+"----位置6");
//		handlerService.handle(msg);
		String retMsg = bussinessFutureHandler.hander(msg);
		ctx.writeAndFlush(retMsg);

	}

	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		System.out.println(ctx.channel().remoteAddress() + " channelRegistered " );
		super.channelRegistered(ctx);
	}

	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
		System.out.println(ctx.channel().remoteAddress() + " channelUnregistered " );
		super.channelUnregistered(ctx);
		if(closeFutureHandler !=null){
			closeFutureHandler.hander(ctx.name());
		}
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println(ctx.channel().remoteAddress() + " channelActive " );
		super.channelActive(ctx);
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		System.out.println(ctx.channel().remoteAddress() + " channelInactive " );
		super.channelInactive(ctx);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		System.out.println(ctx.channel().remoteAddress() + " exceptionCaught :" + cause.getMessage() );
		super.exceptionCaught(ctx, cause);
		if(exceptionFutureHandler !=null){
			exceptionFutureHandler.hander(cause.getMessage());
		}
	}

}
