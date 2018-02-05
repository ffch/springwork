package com.cff.springwork.wallet.trans.xml;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.cff.springwork.wallet.common.Constant;
import com.cff.springwork.wallet.exception.BussinessException;
import com.cff.springwork.wallet.service.ErrorCodeService;
import com.cff.springwork.wallet.trans.data.DictData;

@Component
public class TransXmlParser {
	@Autowired
	ErrorCodeService errorCodeService;
	
	@PostConstruct
	public void init() throws Exception {
		File transFile = ResourceUtils.getFile("classpath:TransXml/transAccountReq.xml");
		DictData.transFormatDataReq = parse(transFile, "trans");
		File transFileRes = ResourceUtils.getFile("classpath:TransXml/transAccountRes.xml");
		DictData.transFormatDataRes = parse(transFileRes, "trans");
	}

	public Map<String, TransDataFormat> parse(File fileName, String fileTag) throws BussinessException {
		DocumentBuilderFactory a = DocumentBuilderFactory.newInstance();
		Map<String, TransDataFormat> transData = new HashMap<String, TransDataFormat>();
		try {
			// 创建DocumentBuilder对象
			DocumentBuilder b = a.newDocumentBuilder();
			// 通过DocumentBuilder对象的parse方法返回一个Document对象
			Document document = b.parse(fileName);
			// 通过Document对象的getElementsByTagName()返根节点的一个list集合
			NodeList booklist = document.getElementsByTagName(fileTag);

			for (int i = 0; i < booklist.getLength(); i++) {
				TransDataFormat tf = new TransDataFormat();
				// 循环遍历获取每一个book
				Node book = booklist.item(i);
				// 通过Node对象的getAttributes()方法获取全的属性值
				NamedNodeMap bookmap = book.getAttributes();

				Node transCodeNode = bookmap.getNamedItem("transcode");
				Node transNameNode = bookmap.getNamedItem("name");
				Node delimiterNode = bookmap.getNamedItem("delimiter");
				Node listDelimiterNode = bookmap.getNamedItem("listDelimiter");
				Node paramDelimiterNode = bookmap.getNamedItem("paramDelimiter");
				if (transCodeNode == null) {
					throw new BussinessException(errorCodeService.getErrCode(Constant.XML_NONODE));
				}
				if (transNameNode == null) {
					throw new BussinessException(errorCodeService.getErrCode(Constant.XML_NONODE));
				}

				if (delimiterNode != null) {
					tf.setDelimiter(delimiterNode.getNodeValue());
				}
				if (listDelimiterNode != null) {
					tf.setListDelimiter(listDelimiterNode.getNodeValue());
				}
				if (paramDelimiterNode != null) {
					tf.setParamDelimiter(paramDelimiterNode.getNodeValue());
				}
				String transCode = transCodeNode.getNodeValue();
				tf.setTransCode(transCode);
				tf.setTransName(transNameNode.getNodeValue());
				transData.put(transCode, tf);
				// 解析book节点的子节点
				NodeList childlist = book.getChildNodes();
				for (int t = 0; t < childlist.getLength(); t++) {
					// 循环遍历获取每一个book
					if (childlist.item(t).getNodeType() == Node.ELEMENT_NODE) {
						Node booktmp = childlist.item(t);
						NamedNodeMap bookmap2 = booktmp.getAttributes();
						Node varnameNode = bookmap2.getNamedItem("varname");
						Node serialsNode = bookmap2.getNamedItem("serials");
						Node defaultNode = bookmap2.getNamedItem("default");
						Node hasChildNode = bookmap2.getNamedItem("hasChild");
						if (varnameNode == null) {
							throw new BussinessException(errorCodeService.getErrCode(Constant.XML_NONODE));
						}
						if (serialsNode == null) {
							throw new BussinessException(errorCodeService.getErrCode(Constant.XML_NONODE));
						}
						
						String varname = varnameNode.getNodeValue();
						String serials = serialsNode.getNodeValue();
						String defaultValue = null;
						if (defaultNode != null) {
							defaultValue = defaultNode.getNodeValue();

						}
						if (hasChildNode != null) {
							tf.putParams(varname, Integer.parseInt(serials), defaultValue,true);
							NodeList childParamList = booktmp.getChildNodes();
							for (int cpl = 0; cpl < childParamList.getLength(); cpl++) {
								// 循环遍历获取每一个book
								if (childParamList.item(cpl).getNodeType() == Node.ELEMENT_NODE) {
									Node childParam = childParamList.item(cpl);
									NamedNodeMap childParamMap = childParam.getAttributes();
									Node childVarnameNode = childParamMap.getNamedItem("varname");
									Node childSerialsNode = childParamMap.getNamedItem("serials");
									Node childDefaultNode = childParamMap.getNamedItem("default");
									
									if (childVarnameNode == null) {
										throw new BussinessException(errorCodeService.getErrCode(Constant.XML_NONODE));
									}
									if (childSerialsNode == null) {
										throw new BussinessException(errorCodeService.getErrCode(Constant.XML_NONODE));
									}
									
									String childVarname = childVarnameNode.getNodeValue();
									String childSerials = childSerialsNode.getNodeValue();
									String childDefaultValue = null;
									if (childDefaultNode != null) {
										childDefaultValue = childDefaultNode.getNodeValue();
									}
									tf.putParamsMap(varname, childVarname, Integer.parseInt(childSerials), childDefaultValue);
								}
							}
						}else{
							tf.putParams(varname, Integer.parseInt(serials), defaultValue);	
						}
						
					}
				}

			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			throw new BussinessException(errorCodeService.getErrCode(Constant.XML_PARSE_ERROR));
		} catch (SAXException e) {
			e.printStackTrace();
			throw new BussinessException(errorCodeService.getErrCode(Constant.XML_PARSE_ERROR));
		} catch (IOException e) {
			e.printStackTrace();
			throw new BussinessException(errorCodeService.getErrCode(Constant.XML_FILE_ERROR));
		}
		
		return transData;
	}
}
