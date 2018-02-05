package com.cff.springwork.wallet.netty.pub;

import java.text.DecimalFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cff.springwork.wallet.trans.data.DictData;
import com.cff.springwork.wallet.trans.data.TransactionMapData;
import com.cff.springwork.wallet.trans.xml.TransDataFormat;
import com.cff.springwork.wallet.trans.xml.TransDataFormat.KeyValue;

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

	public String makeBody(TransactionMapData transactionMapData) {
		String transCode = transactionMapData.getTransCode();
		TransDataFormat transDataFormat = DictData.getResTransDataFormat(transCode);
		log.info("reponse transDataFormat:" + transDataFormat);
		String delimiter = transDataFormat.getDelimiter();
		List<KeyValue> params = transDataFormat.getParams();
		StringBuilder sb = new StringBuilder();
		sb.append(transCode);
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

}
