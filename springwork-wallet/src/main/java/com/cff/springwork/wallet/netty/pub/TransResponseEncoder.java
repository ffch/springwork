package com.cff.springwork.wallet.netty.pub;

import java.text.DecimalFormat;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cff.springwork.wallet.exception.BussinessException;
import com.cff.springwork.wallet.trans.data.DictData;
import com.cff.springwork.wallet.trans.data.TransactionMapData;
import com.cff.springwork.wallet.trans.xml.TransDataFormat;
import com.cff.springwork.wallet.trans.xml.TransDataFormat.KeyValue;
import com.cff.springwork.wallet.util.IPUtil;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToByteEncoder;

public class TransResponseEncoder extends MessageToByteEncoder<TransactionMapData>{
	private final static Logger log = LoggerFactory.getLogger(TransResponseEncoder.class);
	private String charset = "UTF-8";
	
	public TransResponseEncoder() {
		super();
	}

	@Override
	protected void encode(ChannelHandlerContext ctx, TransactionMapData msg, ByteBuf out) throws Exception {
		if(msg.getType() == 1){
			out.writeBytes("00000000".getBytes());
			return;
		}
		String body = makeBody(msg);
		byte[] data = body.getBytes(charset);
		int length = data.length;
		DecimalFormat df = new DecimalFormat("0000");
		String lengthStr = df.format(length);
		byte[] lengthField = lengthStr.getBytes();
		out.writeBytes("00".getBytes());
		out.writeBytes(lengthField);
		out.writeBytes(data);
	}
	
	public String makeBody(TransactionMapData transactionMapData){
		String transCode = transactionMapData.getTransCode();
		TransDataFormat transDataFormat = DictData.getResTransDataFormat(transCode);
		String delimiter = transDataFormat.getDelimiter();
		List<KeyValue> params = transDataFormat.getParams();
		StringBuilder sb = new StringBuilder();
		sb.append(transCode);
		for(int i=0;i<params.size();i++){
			KeyValue kv = params.get(i);
			String value = kv.getValue();
			Object obj = transactionMapData.get(kv.getKey());
			if(obj != null && !"".equals(obj.toString())){
				value = obj.toString();
			}
			sb.append(value);
			sb.append(delimiter);
		}
		log.info(sb.toString());
		return sb.toString();
	}
	
}
