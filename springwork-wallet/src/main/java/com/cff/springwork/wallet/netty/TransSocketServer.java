package com.cff.springwork.wallet.netty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.cff.springwork.wallet.netty.pub.NettyServiceTemplate;
import com.cff.springwork.wallet.netty.pub.TransRequestDecoder;
import com.cff.springwork.wallet.netty.pub.TransResponseEncoder;

import io.netty.channel.ChannelHandler;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;

@Component
public class TransSocketServer extends NettyServiceTemplate{
	@Value("${transport}")
	private int port = 8888;
	@Autowired
	NettyServiceHandler nettyServerHandler;
	
	@Override
	public int getPort() {
		return port;
	}

	@Override
	public ChannelHandler[] createHandlers() {
		return new ChannelHandler[]{
				new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()),
				new TransRequestDecoder(),
				nettyServerHandler,
				
				new TransResponseEncoder()
				};
	}

}
