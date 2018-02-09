package com.cff.springwork.network.tcp.coder;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cff.springwork.network.common.BussinessException;
import com.cff.springwork.network.common.IPUtil;
import com.cff.springwork.network.common.StringUtil;
import com.cff.springwork.network.common.XmlErrorConstant;
import com.cff.springwork.network.tcp.data.DictData;
import com.cff.springwork.network.tcp.data.TransactionMapData;
import com.cff.springwork.network.tcp.format.TransDataFormat;
import com.cff.springwork.network.tcp.format.TransDataFormat.KeyValue;
import com.cff.springwork.network.tcp.format.TransHeadFormat;
import com.cff.springwork.network.tcp.format.TransHeadFormat.HeadKeyValue;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class TransRequestDecoder extends ByteToMessageDecoder {
	private final static Logger log = LoggerFactory.getLogger(TransRequestDecoder.class);
	private String charset = "UTF-8";

	public TransRequestDecoder() {
		super();
	}

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		System.out.println("test");
		if (in.readableBytes() < 6) {
			log.info("报文过短，丢弃");
			return;
		}
		log.info(in.toString());
		in.markReaderIndex();
		byte[] headField = new byte[2];
		in.readBytes(headField);
		byte[] lengthField = new byte[4];
		in.readBytes(lengthField);
		String lengthStr = new String(lengthField);
		int length = Integer.parseInt(lengthStr);
		if (length > 10 * 1024) {
			throw new BussinessException(XmlErrorConstant.EXP_MSG_TOOLONG, XmlErrorConstant.EXP_MSG_TOOLONG_MSG);
		}

		if (length == 0) {
			log.info("收到心跳包：{}",new String(headField) + new String(lengthField));
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

	public TransactionMapData parseBody(String body) throws BussinessException {
		TransactionMapData tm = new TransactionMapData();
		String transCode = body.substring(0, 6);
		String transBody = body.substring(6);
		tm.setTransCode(transCode);
		TransDataFormat transDataFormat = DictData.getReqTransDataFormat(transCode);
		log.info("解析报文数据为：{}",transBody);
		if(transDataFormat.getTransHeadFormat() != null){
			TransHeadFormat transHeadFormat = transDataFormat.getTransHeadFormat();
			transBody = parseHead(tm,transBody,transHeadFormat);
			log.info("解析报文头后为：{}",transBody);
		}
		String delimiter = transDataFormat.getDelimiter();
		tm.put("beans", transDataFormat.getTransName());
		String[] params = transBody.split(delimiter);
		List<KeyValue> listKv = transDataFormat.getParams();
		for (int i = 0; i < params.length; i++) {
			KeyValue kv = listKv.get(i);
			String key = kv.getKey();
			String value = params[i];
			if (StringUtil.isEmpty(value)) {
				value = kv.getValue();
			} else {
				if (kv.getHasChild()) {
					List<KeyValue> paraList = transDataFormat.getParamsMap(kv.getKey());
					String listDelimiter = transDataFormat.getListDelimiter();
					String paramDelimiter = transDataFormat.getParamDelimiter();
					String[] listOne = value.split(listDelimiter);
					JSONArray ja = new JSONArray();
					for(int li = 0; li < listOne.length; li++){
						String jsonstr = listOne[li];
						String[] jsonOne = jsonstr.split(paramDelimiter);
						JSONObject json = new JSONObject();
						for(int jsi = 0; jsi < jsonOne.length; jsi++ ){
							KeyValue jsonKv = paraList.get(jsi);
							String jsonValue = jsonOne[jsi];
							if(StringUtil.isEmpty(jsonValue)){
								jsonValue = jsonKv.getValue();
							}
							json.put(jsonKv.getKey(), jsonValue);
						}
						ja.add(json);
					}
					value = ja.toString();
				}
			}

			tm.put(key, value);
		}
		log.info(transDataFormat.toString());
		return tm;
	}

	/**
	 * 解析报文头
	 * @param tm 传输体
	 * @param data 报文数据
	 * @param transHeadFormat 
	 * @return 报文体
	 * @throws BussinessException 
	 */
	public String parseHead(TransactionMapData tm,String transBody, TransHeadFormat transHeadFormat) throws BussinessException{
		if(TransHeadFormat.HEADTYPE_FIXED.equals(transHeadFormat.getHeadType())){
			for(int i=0;i<transHeadFormat.getParamSize();i++){
				HeadKeyValue hkv = transHeadFormat.get(i);
				tm.put(hkv.getKey(), transBody.substring(0, hkv.getLength()));
				transBody = transBody.substring(hkv.getLength());
			}
			return transBody;
		}else{
			String[] params = transBody.split(transHeadFormat.getHeadDelimiter());
			if(params.length < transHeadFormat.getParamSize()){
				throw new BussinessException(XmlErrorConstant.EXP_MSGHEAD_NOT_COMPLETE, XmlErrorConstant.EXP_MSGHEAD_NOT_COMPLETE_MSG);
			}
			log.info("head的长度为{}",transHeadFormat.getParamSize());
			for(int i=0;i<transHeadFormat.getParamSize();i++){
				HeadKeyValue hkv = transHeadFormat.get(i);
				tm.put(hkv.getKey(), params[i]);
				transBody = transBody.substring(params[i].length()+1);
			}
			return transBody;
		}

	}
}
