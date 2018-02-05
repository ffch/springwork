package com.cff.springwork.wallet.netty.pub;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cff.springwork.wallet.exception.BussinessException;
import com.cff.springwork.wallet.trans.data.DictData;
import com.cff.springwork.wallet.trans.data.TransactionMapData;
import com.cff.springwork.wallet.trans.xml.TransDataFormat;
import com.cff.springwork.wallet.util.IPUtil;
import com.cff.springwork.wallet.util.StringUtil;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class TransRequestDecoder extends ByteToMessageDecoder{
	private final static Logger log = LoggerFactory.getLogger(TransRequestDecoder.class);
	private String charset = "UTF-8";
	
	public TransRequestDecoder() {
		super();
	}

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in,
			List<Object> out) throws Exception {
		if (in.readableBytes() < 6) {
			return;
		}

		in.markReaderIndex();
		byte[] headField = new byte[2];
		in.readBytes(headField);
		byte[] lengthField = new byte[4];
		in.readBytes(lengthField);
		String lengthStr = new String(lengthField);
		int length = Integer.parseInt(lengthStr);
		if (length > 10 * 1024) {
			throw new BussinessException("11111111", "报文过长");
		}

		if (length == 0) {
			// 心跳包
			out.add(new TransactionMapData(1));
			return;
		}

		if (in.readableBytes() < length) {
			in.resetReaderIndex();
			return;
		}

		byte[] data = new byte[length];
		in.readBytes(data);

		String body = new String(data, charset);
		TransactionMapData tm = parseBody(body);
		String ip = IPUtil.getChannelRemoteIP(ctx);
		tm.setIp(ip);
		log.info(tm.toString());
		out.add(tm);
	}
	
	public TransactionMapData parseBody(String body){
		TransactionMapData tm = new TransactionMapData();
		String transCode = body.substring(0, 6);
		tm.setTransCode(transCode);
		TransDataFormat transDataFormat = DictData.getReqTransDataFormat(transCode);
		String delimiter = transDataFormat.getDelimiter();
		tm.put("beans", transDataFormat.getTransName());
		String[] params = body.substring(6).split(delimiter);
		for(int i=0;i<params.length;i++){
			String key = transDataFormat.getParamKey(i);
			String value = params[i];
			if(StringUtil.isEmpty(value)){
				value = transDataFormat.getParamValue(i);
			}
			tm.put(key, value);
		}
		log.info(transDataFormat.toString());
		return tm;
	}
	
}
