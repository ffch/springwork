package com.cff.springwork.web.client.tcp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;

import com.cff.springwork.network.tcp.data.TransactionMapData;
import com.cff.springwork.network.tcp.future.SyncWriteFuture;
import com.cff.springwork.network.tcp.future.SyncWriteMap;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class NettyClientHandler extends ChannelInboundHandlerAdapter{
	private final static Logger log = LoggerFactory.getLogger(NettyClientHandler.class);
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		log.info("连接到地址：{}，发送心跳", ctx.channel().remoteAddress());
		TransactionMapData tm = new TransactionMapData();
		tm.setType(1);
		tm.put("heartBit", "330000");
		ctx.writeAndFlush("000000");
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		log.info("连接已关闭：{}", ctx.channel().remoteAddress());
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object obj) throws Exception {
		log.info("当前连接{}", ctx.channel().remoteAddress());
		log.info("当前obj{}", obj);
		if (obj instanceof TransactionMapData) {
			TransactionMapData msg = (TransactionMapData) obj;
			if (msg.getType() == 1) {
				log.info("心跳包：{}",msg);
				return;
			}
			String requestId = msg.get("reqTransFlow").toString();
	        SyncWriteFuture future = (SyncWriteFuture) SyncWriteMap.syncKey.get(requestId);
	        if (future != null) {
	            future.setResponse(msg);
	        }
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		log.error(cause.getMessage());
		ctx.channel().close();
	}
	
}
