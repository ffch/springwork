package com.cff.springwork.network.tcp.xml;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import com.cff.springwork.network.common.BussinessException;
import com.cff.springwork.network.common.XmlErrorConstant;
import com.cff.springwork.network.tcp.data.DictData;

public class TransDataCoderConfiguration {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	public TransXmlParser transXmlParser = new TransXmlParser();
	public List<String> xmlReqFiles;
	public List<String> xmlResFiles;
	public List<String> xmlTags;
	public String commonTag = "trans";
	
	public void init() throws BussinessException, FileNotFoundException{
		logger.info("开始解析xml。");
		if(xmlReqFiles == null || xmlReqFiles.size() < 1){
			throw new BussinessException(XmlErrorConstant.EXP_XMLFILE_NOTEXIST,XmlErrorConstant.EXP_XMLFILE_NOTEXIST_MSG);
		}
		if(xmlResFiles == null || xmlResFiles.size() < 1){
			throw new BussinessException(XmlErrorConstant.EXP_XMLFILE_NOTEXIST,XmlErrorConstant.EXP_XMLFILE_NOTEXIST_MSG);
		}
		if(xmlReqFiles.size() != xmlResFiles.size()){
			throw new BussinessException(XmlErrorConstant.EXP_XMLFILE_NOT_MATCH,XmlErrorConstant.EXP_XMLFILE_NOT_MATCH_MSG);
		}
		if(xmlTags != null && xmlTags.size() > 0 && xmlTags.size() != xmlReqFiles.size()){
			throw new BussinessException(XmlErrorConstant.EXP_XMLTAG_NOT_MATCH,XmlErrorConstant.EXP_XMLTAG_NOT_MATCH_MSG);
		}
		if(xmlTags == null || xmlTags.size() < 1){
			for(int i =0; i< xmlReqFiles.size(); i++){
				File transFile = ResourceUtils.getFile(xmlReqFiles.get(i));
				logger.info("请求xml路径为：{}",transFile.getPath());
				DictData.transFormatDataReq.putAll(transXmlParser.parse(transFile, commonTag));
			}
			for(int i =0; i< xmlResFiles.size(); i++){
				File transFile = ResourceUtils.getFile(xmlResFiles.get(i));
				logger.info("请求xml路径为：{}",transFile.getPath());
				DictData.transFormatDataRes.putAll(transXmlParser.parse(transFile, commonTag));
			}
		}else{
			for(int i =0; i< xmlReqFiles.size(); i++){
				File transFile = ResourceUtils.getFile(xmlReqFiles.get(i));
				DictData.transFormatDataReq = transXmlParser.parse(transFile, xmlTags.get(i));
			}
			for(int i =0; i< xmlResFiles.size(); i++){
				File transFile = ResourceUtils.getFile(xmlResFiles.get(i));
				DictData.transFormatDataRes.putAll(transXmlParser.parse(transFile, xmlTags.get(i)));
			}
		}
		logger.info("xml解析完成。请求文件个数：{}，响应文件个数：{}",xmlReqFiles.size(),xmlResFiles.size());
//		logger.info("请求格式map为：{}，响应格式map为：{}",DictData.transFormatDataReq,DictData.transFormatDataRes);
	}
	
	
	public TransXmlParser getTransXmlParser() {
		return transXmlParser;
	}
	public void setTransXmlParser(TransXmlParser transXmlParser) {
		this.transXmlParser = transXmlParser;
	}
	public List<String> getXmlTags() {
		return xmlTags;
	}
	public void setXmlTags(List<String> xmlTags) {
		this.xmlTags = xmlTags;
	}
	public String getCommonTag() {
		return commonTag;
	}
	public void setCommonTag(String commonTag) {
		this.commonTag = commonTag;
	}
	public List<String> getXmlReqFiles() {
		return xmlReqFiles;
	}
	public void setXmlReqFiles(List<String> xmlReqFiles) {
		this.xmlReqFiles = xmlReqFiles;
	}
	public List<String> getXmlResFiles() {
		return xmlResFiles;
	}
	public void setXmlResFiles(List<String> xmlResFiles) {
		this.xmlResFiles = xmlResFiles;
	}
	
}
