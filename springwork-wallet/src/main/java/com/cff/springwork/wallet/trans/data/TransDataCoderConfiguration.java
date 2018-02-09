package com.cff.springwork.wallet.trans.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import com.cff.springwork.network.common.BussinessException;
import com.cff.springwork.network.common.XmlErrorConstant;
import com.cff.springwork.network.tcp.data.DictData;
import com.cff.springwork.network.tcp.xml.TransXmlParser;

@Component
public class TransDataCoderConfiguration {
	public TransXmlParser transXmlParser = new TransXmlParser();
	
	@Value("#{'${xml.reqfiles}'.split(',')}")
	public List<String> xmlReqFiles;
	@Value("#{'${xml.resfiles}'.split(',')}")
	public List<String> xmlResFiles;
	public List<String> xmlTags;
	public String commonTag = "trans";
	
	@PostConstruct
	public void init() throws BussinessException, FileNotFoundException{
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
				DictData.transFormatDataReq.putAll(transXmlParser.parse(transFile, commonTag));
			}
			for(int i =0; i< xmlResFiles.size(); i++){
				File transFile = ResourceUtils.getFile(xmlResFiles.get(i));
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
