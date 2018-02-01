package com.cff.springwork.wallet.util;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

public class IPUtil {
	/**
	 * 获取客户端IP地址. 支持多级反向代理
	 * 
	 */
	public static String getRemoteAddr(ChannelHandlerContext ctx,
			FullHttpRequest request) {
		try {
			String remoteAddr = request.headers().get("X-Forwarded-For");
			// 如果通过多级反向代理，X-Forwarded-For的值不止一个，而是一串用逗号分隔的IP值，此时取X-Forwarded-For中第一个非unknown的有效IP字符串
			if (isEffective(remoteAddr) && (remoteAddr.indexOf(",") > -1)) {
				String[] array = remoteAddr.split(",");
				for (String element : array) {
					if (isEffective(element)) {
						remoteAddr = element;
						break;
					}
				}
			}
			if (!isEffective(remoteAddr)) {
				remoteAddr = request.headers().get("X-Real-IP");
			}
			if (!isEffective(remoteAddr)) {
				remoteAddr = getChannelRemoteIP(ctx);
			}

			if (isEffective(remoteAddr))
				return remoteAddr;
			else
				return "";
		} catch (Exception e) {
			return "";
		}
	}

	public static String getChannelRemoteIP(ChannelHandlerContext ctx) {
		String remoteAddr = "";
		try {
			SocketAddress sockAddr = ctx.channel().remoteAddress();
			if (sockAddr instanceof InetSocketAddress) {
				InetAddress inetAddr = ((InetSocketAddress) sockAddr)
						.getAddress();
				remoteAddr = inetAddr.getHostAddress();
			}
		} catch (Exception e) {
		}
		return remoteAddr;
	}

	/**
	 * 远程地址是否有效.
	 * 
	 * @param remoteAddr
	 *            远程地址
	 * @return true代表远程地址有效，false代表远程地址无效
	 */
	public static boolean isEffective(final String remoteAddr) {
		boolean isEffective = false;
		if ((null != remoteAddr) && (!"".equals(remoteAddr.trim()))
				&& (!"unknown".equalsIgnoreCase(remoteAddr.trim()))) {
			isEffective = true;
		}
		return isEffective;
	}
}
