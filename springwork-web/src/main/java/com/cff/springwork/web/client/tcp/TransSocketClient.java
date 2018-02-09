package com.cff.springwork.web.client.tcp;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.cff.springwork.network.tcp.client.NettyClientTemplate;
import com.cff.springwork.network.tcp.coder.TransRequestDecoder;
import com.cff.springwork.network.tcp.coder.TransResponseEncoder;
import com.cff.springwork.network.tcp.data.TransactionMapData;

import io.netty.channel.ChannelHandler;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import io.netty.util.Timer;
import io.netty.util.TimerTask;

public class TransSocketClient extends NettyClientTemplate{
	public int port = 6666;
	public String domain = "127.0.0.1";
	public static final Timer timer = new HashedWheelTimer();
	public static final int DEFAULT_HEARTBEAT_PERIOD = 3000;
	public static final int DEFAULT_MSG_TRANS_PERIOD = 3000;
	@Override
	public int getPort() {
		return port;
	}

	@Test
	public void test() throws Exception{
		init();
		TransactionMapData tm = new TransactionMapData();
		tm.setTransCode("000001");
		String requestId = UUID.randomUUID().toString();
		tm.put("reqTransFlow",requestId);
		sendAndWaitResult(tm);
	}
	
	@Override
	public String getDomain() {
		return domain;
	}

	@Override
	public ChannelHandler[] createHandlers() {
		return new ChannelHandler[]{
//				new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()),
				new TransRequestDecoder(),
				
				new NettyClientHandler(),
				
				new TransResponseEncoder()
				};
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}
	
	public void startHeartBeat() throws Exception {
		timer.newTimeout(new TimerTask() {
			@Override
			public void run(Timeout timeout) throws Exception {
				try {
					// 发送心跳请求
				} catch (Throwable t) {
				} finally {
					timer.newTimeout(this, DEFAULT_HEARTBEAT_PERIOD, TimeUnit.SECONDS);
				}
			}
		}, DEFAULT_HEARTBEAT_PERIOD, TimeUnit.SECONDS);
	}

	public boolean isConnected() {
		return channel.isActive();
	}

	public TransactionMapData sendAndWaitResult(TransactionMapData req) throws Exception {
		if(!channel.isActive()){
			logger.info("断线重连中。。。");
			connect();
		}
		return writeAndSync(req,DEFAULT_MSG_TRANS_PERIOD);
	}
	public void sendRequest(TransactionMapData msg) throws Exception {
		channel.writeAndFlush(msg);
	}
	public void sendRequest(String msg) throws Exception {
		channel.writeAndFlush(msg);
	}
}
