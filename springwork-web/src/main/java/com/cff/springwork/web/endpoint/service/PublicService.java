package com.cff.springwork.web.endpoint.service;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cff.springwork.web.param.ConfigParam;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

@Service
public class PublicService {
	@Autowired
	ConfigParam configParam;
	
	public String getLocation(String lng,String lat){
		String url = "http://restapi.amap.com/v3/geocode/regeo?key="+configParam.getGdAppKey()+"&location="
				+lng+","+lat+"&extensions=base";
		HttpClient client = new HttpClient();

		GetMethod getMethod = new GetMethod(url);
		String errorMsg = "";
		try {
			client.executeMethod(getMethod);
			System.out.println(getMethod.getStatusLine()); // 打印结果页面
			errorMsg = getMethod.getResponseBodyAsString();
			System.out.println(errorMsg);
			JSONObject jsonObj = JSONObject.fromObject(errorMsg);
			JSONObject addr = JSONObject.fromObject(jsonObj.get("regeocode").toString());
			errorMsg = (String) addr.get("formatted_address");

		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
			errorMsg = "解析数据失败";
		}
		return errorMsg;	
	}
}
