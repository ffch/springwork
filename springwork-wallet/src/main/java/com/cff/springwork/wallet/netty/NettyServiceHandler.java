package com.cff.springwork.wallet.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.cff.springwork.network.tcp.data.TransactionMapData;
import com.cff.springwork.wallet.common.Constant;
import com.cff.springwork.wallet.common.SpringBeansManager;
import com.cff.springwork.wallet.domain.WaTransFlow;
import com.cff.springwork.wallet.service.BusiNessService;
import com.cff.springwork.wallet.service.ErrorCodeService;
import com.cff.springwork.wallet.service.WaTransFlowService;

import io.netty.channel.ChannelHandler.Sharable;

@Component
@Scope("prototype")
@Sharable
public class NettyServiceHandler extends ChannelInboundHandlerAdapter {
	private final static Logger log = LoggerFactory.getLogger(NettyServiceHandler.class);

	@Autowired
	SpringBeansManager springBeansService;
	
	@Autowired
	ErrorCodeService errorCodeService;

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		log.info("收到连接请求：{}", ctx.channel().remoteAddress());
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		log.info("连接已关闭：{}", ctx.channel().remoteAddress());
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object obj) throws Exception {
		if (obj instanceof TransactionMapData) {
			TransactionMapData msg = (TransactionMapData) obj;
			if (msg.getType() == 1) {
				ctx.channel().writeAndFlush(msg);
				return;
			}
			if (msg.get("beans") != null && !"".equals(msg.get("beans").toString())) {
				try{
					BusiNessService busiNessService = (BusiNessService) springBeansService.getBean(msg.get("beans").toString());
					
					TransactionMapData tm = busiNessService.trans(msg);
					log.info("传输数据为：{}",tm.toString());
					ctx.channel().writeAndFlush(tm);
					return;
				}catch(BeansException e){
					errorCodeService.genErrorReturn(msg,Constant.TRANS_NOBEAN);
					ctx.channel().writeAndFlush(msg);
					return;
				}	
				
			}
			errorCodeService.genErrorReturn(msg,Constant.TRANS_NOBEAN);
			ctx.channel().writeAndFlush(msg);
		}else{
			log.info("信息非法");
			ctx.channel().writeAndFlush("哈哈哈哈");
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		log.error(cause.getMessage());
		ctx.channel().close();
	}

}
