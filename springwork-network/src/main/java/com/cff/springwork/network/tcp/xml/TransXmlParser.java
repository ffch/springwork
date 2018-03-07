package com.cff.springwork.network.tcp.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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

import com.cff.springwork.network.common.BussinessException;
import com.cff.springwork.network.common.XmlErrorConstant;
import com.cff.springwork.network.tcp.format.TransDataFormat;
import com.cff.springwork.network.tcp.format.TransHeadFormat;

public class TransXmlParser {

	public void test() throws BussinessException {
		parse(new File("D:\\Document\\NewWork\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp3\\wtpwebapps\\springwork-web\\WEB-INF\\classes\\TransXml\\transAccountReq.xml"),
				"trans");
	}
	
	public Map<String, TransDataFormat> parse(File fileName, String fileTag) throws BussinessException {
		try {
			return parse(new FileInputStream(fileName),fileTag);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Map<String, TransDataFormat> parse(InputStream fileName, String fileTag) throws BussinessException {
		DocumentBuilderFactory a = DocumentBuilderFactory.newInstance();
		Map<String, TransDataFormat> transData = new HashMap<String, TransDataFormat>();
		try {
			// 创建DocumentBuilder对象
			DocumentBuilder b = a.newDocumentBuilder();
			// 通过DocumentBuilder对象的parse方法返回一个Document对象
			Document document = b.parse(fileName);
			NodeList headList = document.getElementsByTagName("head");
			Boolean hasHead = false;
			TransHeadFormat transHeadFormat = new TransHeadFormat();

			if (headList != null && headList.getLength() > 0) {
				Node head = headList.item(0);
				NamedNodeMap headmap = head.getAttributes();
				Node typeNode = headmap.getNamedItem("type");
				if (typeNode != null) {
					transHeadFormat.setHeadType(typeNode.getNodeValue().trim());
					Boolean type = true;
					if (TransHeadFormat.HEADTYPE_VAR.equals(transHeadFormat.getHeadType())) {
						Node delimiterNode = headmap.getNamedItem("delimiter");
						if (delimiterNode != null) {
							transHeadFormat.setHeadDelimiter(delimiterNode.getNodeValue());
						}
						type = false;
					}
					NodeList childlist = head.getChildNodes();
					for (int t = 0; t < childlist.getLength(); t++) {
						// 循环遍历获取每一个book
						if (childlist.item(t).getNodeType() == Node.ELEMENT_NODE) {
							Node booktmp = childlist.item(t);
							NamedNodeMap bookmap2 = booktmp.getAttributes();
							Node varnameNode = bookmap2.getNamedItem("varname");
							Node serialsNode = bookmap2.getNamedItem("serials");
							Node defaultNode = bookmap2.getNamedItem("default");
							String varname = varnameNode.getNodeValue();
							int serials = Integer.parseInt(serialsNode.getNodeValue());
							int len = 0;
							if (type) {
								Node lengthNode = bookmap2.getNamedItem("length");
								len = Integer.parseInt(lengthNode.getNodeValue());
							}
							String defaultValue = null;
							if (defaultNode != null) {
								defaultValue = defaultNode.getNodeValue();
							}
							transHeadFormat.putParams(varname, serials, len, defaultValue);
						}
					}
					hasHead = true;
				}
			}

			// 通过Document对象的getElementsByTagName()返根节点的一个list集合
			NodeList booklist = document.getElementsByTagName(fileTag);

			for (int i = 0; i < booklist.getLength(); i++) {
				TransDataFormat tf = new TransDataFormat();
				if (hasHead) {
					tf.setTransHeadFormat(transHeadFormat);
				}
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
					throw new BussinessException(XmlErrorConstant.EXP_XML_NONODE, XmlErrorConstant.EXP_XML_NONODE_MSG);
				}
				if (transNameNode == null) {
					throw new BussinessException(XmlErrorConstant.EXP_XML_NONODE, XmlErrorConstant.EXP_XML_NONODE_MSG);
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
							throw new BussinessException(XmlErrorConstant.EXP_XML_NONODE,
									XmlErrorConstant.EXP_XML_NONODE_MSG);
						}
						if (serialsNode == null) {
							throw new BussinessException(XmlErrorConstant.EXP_XML_NONODE,
									XmlErrorConstant.EXP_XML_NONODE_MSG);
						}

						String varname = varnameNode.getNodeValue();
						String serials = serialsNode.getNodeValue();
						String defaultValue = null;
						if (defaultNode != null) {
							defaultValue = defaultNode.getNodeValue();

						}
						if (hasChildNode != null) {
							tf.putParams(varname, Integer.parseInt(serials), defaultValue, true);
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
										throw new BussinessException(XmlErrorConstant.EXP_XML_NONODE,
												XmlErrorConstant.EXP_XML_NONODE_MSG);
									}
									if (childSerialsNode == null) {
										throw new BussinessException(XmlErrorConstant.EXP_XML_NONODE,
												XmlErrorConstant.EXP_XML_NONODE_MSG);
									}

									String childVarname = childVarnameNode.getNodeValue();
									String childSerials = childSerialsNode.getNodeValue();
									String childDefaultValue = null;
									if (childDefaultNode != null) {
										childDefaultValue = childDefaultNode.getNodeValue();
									}
									tf.putParamsMap(varname, childVarname, Integer.parseInt(childSerials),
											childDefaultValue);
								}
							}
						} else {
							tf.putParams(varname, Integer.parseInt(serials), defaultValue);
						}

					}
				}

			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			throw new BussinessException(XmlErrorConstant.EXP_XML_PARSE_ERROR,
					XmlErrorConstant.EXP_XML_PARSE_ERROR_MSG);
		} catch (SAXException e) {
			e.printStackTrace();
			throw new BussinessException(XmlErrorConstant.EXP_XML_PARSE_ERROR,
					XmlErrorConstant.EXP_XML_PARSE_ERROR_MSG);
		} catch (IOException e) {
			e.printStackTrace();
			throw new BussinessException(XmlErrorConstant.EXP_XML_FILE_ERROR, XmlErrorConstant.EXP_XML_FILE_ERROR_MSG);
		}

		return transData;
	}
}
