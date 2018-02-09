package com.cff.springwork.network.tcp.coder;

import java.text.DecimalFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cff.springwork.network.common.BussinessException;
import com.cff.springwork.network.common.StringUtil;
import com.cff.springwork.network.common.XmlErrorConstant;
import com.cff.springwork.network.tcp.data.DictData;
import com.cff.springwork.network.tcp.data.TransactionMapData;
import com.cff.springwork.network.tcp.format.TransDataFormat;
import com.cff.springwork.network.tcp.format.TransHeadFormat;
import com.cff.springwork.network.tcp.format.TransDataFormat.KeyValue;
import com.cff.springwork.network.tcp.format.TransHeadFormat.HeadKeyValue;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class TransResponseEncoder extends MessageToByteEncoder<TransactionMapData> {
	private final static Logger log = LoggerFactory.getLogger(TransResponseEncoder.class);
	private String charset = "UTF-8";

	public TransResponseEncoder() {
		super();
	}

	@Override
	protected void encode(ChannelHandlerContext ctx, TransactionMapData msg, ByteBuf out) throws Exception {
		if (msg.getType() == 1) {
			if(msg.get("heartBit")!=null){
				out.writeBytes(msg.get("heartBit").toString().getBytes());
				log.info("发送心跳:{}",msg.get("heartBit"));
			}else{
				out.writeBytes("000000".getBytes());
				log.info("发送心跳:000000");
			}
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

	public String makeBody(TransactionMapData transactionMapData) {
		String transCode = transactionMapData.getTransCode();
		StringBuilder sb = new StringBuilder();
		sb.append(transCode);
		TransDataFormat transDataFormat = DictData.getResTransDataFormat(transCode);
		log.info("reponse transDataFormat:" + transDataFormat);
		if(transDataFormat.getTransHeadFormat() != null){
			TransHeadFormat transHeadFormat = transDataFormat.getTransHeadFormat();
			makeHead(transactionMapData,sb,transHeadFormat);
			log.info("组装报文头后为：{}",sb);
		}
		String delimiter = transDataFormat.getDelimiter();
		List<KeyValue> params = transDataFormat.getParams();
		
		for (int i = 0; i < params.size(); i++) {
			KeyValue kv = params.get(i);
			if (kv.getHasChild()) {
				List<KeyValue> paraList = transDataFormat.getParamsMap(kv.getKey());
				String listDelimiter = transDataFormat.getListDelimiter();
				String paramDelimiter = transDataFormat.getParamDelimiter();
				Object obj = transactionMapData.get(kv.getKey());
				if (obj != null && !"".equals(obj.toString())) {
					JSONArray ja = JSONArray.fromObject(obj);
					for (int js = 0; js < ja.size(); js++) {
						JSONObject job = ja.getJSONObject(js); 
						log.info("json:" + job.toString());
						log.info("paraList:" + paraList.toString());
						for(int k = 0;k<paraList.size();k++ ){
							KeyValue kvp = paraList.get(k);
							String value = kvp.getValue();
							Object objJson = job.get(kvp.getKey());
							if (objJson != null && !"".equals(objJson.toString())) {
								value = objJson.toString();
							}
							sb.append(value);
							sb.append(paramDelimiter);
						}
						sb.append(listDelimiter);
						
					}
					sb.append(delimiter);
				}
				continue;
			}
			String value = kv.getValue();
			Object obj = transactionMapData.get(kv.getKey());
			if (obj != null && !"".equals(obj.toString())) {
				value = obj.toString();
			}
			sb.append(value);
			sb.append(delimiter);
		}
		log.info(sb.toString());
		return sb.toString();
	}

	private void makeHead(TransactionMapData transactionMapData, StringBuilder sb, TransHeadFormat transHeadFormat) {
		if(TransHeadFormat.HEADTYPE_FIXED.equals(transHeadFormat.getHeadType())){
			for(int i=0;i<transHeadFormat.getParamSize();i++){
				HeadKeyValue hkv = transHeadFormat.get(i);
				Object obj = transactionMapData.get(hkv.getKey());
				String value = hkv.getDefaultValue();
				if(StringUtil.isNotEmpty(obj)){
					value = (String) transactionMapData.get(hkv.getKey());
				}
				value = StringUtil.format(value,hkv.getLength());
				sb.append(value);
			}
		}else{
			for(int i=0;i<transHeadFormat.getParamSize();i++){
				HeadKeyValue hkv = transHeadFormat.get(i);
				Object obj = transactionMapData.get(hkv.getKey());
				String value = hkv.getDefaultValue();
				if(StringUtil.isNotEmpty(obj)){
					value = (String) transactionMapData.get(hkv.getKey());
				}
				sb.append(value);
				sb.append(transHeadFormat.getHeadDelimiter());
			}
		}
	}

}
