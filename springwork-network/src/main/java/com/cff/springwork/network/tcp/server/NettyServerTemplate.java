package com.cff.springwork.network.tcp.server;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public abstract class NettyServerTemplate  {
	
	private Channel channel;
	private EventLoopGroup bossGroup;
	private EventLoopGroup workerGroup;

	public abstract int getPort();
	
	@PostConstruct
	public void init() throws Exception {
		bossGroup = new NioEventLoopGroup();
		workerGroup = new NioEventLoopGroup();
		System.out.println(Thread.currentThread().getName() + "----位置4");
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup);
			b.channel(NioServerSocketChannel.class);
			b.childHandler(new ChannelInitializer<SocketChannel>() {
				@Override
				public void initChannel(SocketChannel ch) throws Exception {
					ChannelHandler[] handlers = createHandlers();
					for (ChannelHandler handler : handlers) {
						ch.pipeline().addLast(handler);
					}
				}
			}).option(ChannelOption.SO_BACKLOG, 128)
			.option(ChannelOption.SO_REUSEADDR, true)
			.childOption(ChannelOption.SO_KEEPALIVE, true)
			.childOption(ChannelOption.SO_REUSEADDR, true);

			// 服务器绑定端口监听
			ChannelFuture f = b.bind(getPort()).sync();
			// 监听服务器关闭监听
			if (!f.isSuccess()) {
				System.out.println("无法绑定端口：" + getPort());
				throw new Exception("无法绑定端口：" + getPort());
			}

			System.out.println("服务[NettyServerAnno]启动完毕，监听端口[{}]"+ getPort());
			channel = f.channel();
			// 可以简写为
			/* b.bind(portNumber).sync().channel().closeFuture().sync(); */
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public abstract ChannelHandler[] createHandlers();

	@PreDestroy
	public void stop() {
		System.out.println("destroy server resources");
		if (null == channel) {
			System.out.println("server channel is null");
		}
		bossGroup.shutdownGracefully();
		workerGroup.shutdownGracefully();
		channel.closeFuture().syncUninterruptibly();
		bossGroup = null;
		workerGroup = null;
		channel = null;
	}
	
}
