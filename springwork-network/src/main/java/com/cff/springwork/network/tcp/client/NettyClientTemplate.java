package com.cff.springwork.network.tcp.client;

import java.net.InetSocketAddress;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cff.springwork.network.tcp.data.TransactionMapData;
import com.cff.springwork.network.tcp.future.SyncWriteFuture;
import com.cff.springwork.network.tcp.future.SyncWriteMap;
import com.cff.springwork.network.tcp.future.WriteFuture;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.UnpooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.internal.SystemPropertyUtil;

public abstract class NettyClientTemplate {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	public ChannelFuture future;
	public Channel channel;
	/**
	 * 共享IO线程
	 **/
	public NioEventLoopGroup workerGroup;

	public void init() throws Exception {
		connect();
	}

	public void stop() {
		if (null == channel) {
			System.out.println("server channel is null");
		}
		workerGroup.shutdownGracefully();
		channel.closeFuture().syncUninterruptibly();
		workerGroup = null;
		channel = null;
	}

	public abstract int getPort();

	public abstract String getDomain();

	public abstract ChannelHandler[] createHandlers();

	public int getConnectionTimeout() {
		return 3000;
	}

	public TransactionMapData writeAndSync(TransactionMapData request, long timeout) throws Exception {
		if (channel == null) {
			throw new NullPointerException("channel");
		}
		if (request == null) {
			throw new NullPointerException("request");
		}
		if (timeout <= 0) {
			throw new IllegalArgumentException("timeout <= 0");
		}
		logger.info("reqTransFlow为{}", request.get("reqTransFlow"));
		WriteFuture<TransactionMapData> future = new SyncWriteFuture(request.get("reqTransFlow").toString());
		SyncWriteMap.syncKey.put(request.get("reqTransFlow").toString(), future);

		TransactionMapData response = doWriteAndSync(request, timeout, future);

		SyncWriteMap.syncKey.remove(request.get("reqTransFlow").toString());
		return response;
	}

	private TransactionMapData doWriteAndSync(TransactionMapData request, long timeout,
			WriteFuture<TransactionMapData> writeFuture) throws Exception {

		channel.writeAndFlush(request).addListener(new ChannelFutureListener() {
			public void operationComplete(ChannelFuture future) throws Exception {
				writeFuture.setWriteResult(future.isSuccess());
				writeFuture.setCause(future.cause());
				// 失败移除
				if (!writeFuture.isWriteSuccess()) {
					SyncWriteMap.syncKey.remove(writeFuture.requestId());
				}
			}
		});

		TransactionMapData response = writeFuture.get(timeout, TimeUnit.MILLISECONDS);
		if (response == null) {
			if (writeFuture.isTimeout()) {
				throw new TimeoutException();
			} else {
				// write exception
				throw new Exception(writeFuture.cause());
			}
		}
		return response;
	}

	public void connect() throws Exception {
		if (workerGroup != null) {
			workerGroup.shutdownGracefully();
			workerGroup = null;
		}
		if (channel != null) {
			channel.closeFuture().syncUninterruptibly();
			channel = null;
		}

		Bootstrap bootstrap = new Bootstrap();
		workerGroup = new NioEventLoopGroup();
		bootstrap.group(workerGroup)//
				.option(ChannelOption.SO_KEEPALIVE, true).option(ChannelOption.TCP_NODELAY, true)
				.channel(NioSocketChannel.class)//
				.handler(new ChannelInitializer<NioSocketChannel>() {
					@Override
					protected void initChannel(NioSocketChannel ch) throws Exception {
						ChannelHandler[] handlers = createHandlers();
						for (ChannelHandler handler : handlers) {
							ch.pipeline().addLast(handler);
						}
					}

				});
		int connectTimeout = getConnectionTimeout();
		if (connectTimeout < 1000) {
			bootstrap.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000);
		} else {
			bootstrap.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, connectTimeout);
		}

		String targetIP = getDomain();
		int targetPort = getPort();
		logger.info("准备连接到{}的{}端口", targetIP, targetPort);
		future = bootstrap.connect(new InetSocketAddress(targetIP, targetPort)).sync();
		if (future.isSuccess()) {
			channel = future.channel();

		} else {
			future.cancel(true);
			future.channel().close();
			throw new Exception(targetIP);
		}
	}
}
