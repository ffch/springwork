package com.cff.springwork.wallet.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.cff.springwork.network.tcp.format.TransDataFormat;


public class TestXmlApi {
	@Test
	public void test() throws ParserConfigurationException, SAXException, IOException{
		DocumentBuilderFactory a = DocumentBuilderFactory.newInstance();   
		Map<String,TransDataFormat> transData = new HashMap<String,TransDataFormat>();
        try {    
            //创建DocumentBuilder对象    
            DocumentBuilder b = a.newDocumentBuilder();    
            //通过DocumentBuilder对象的parse方法返回一个Document对象    
            Document document = b.parse("testApi.xml");    
            //通过Document对象的getElementsByTagName()返根节点的一个list集合    
            NodeList booklist = document.getElementsByTagName("trans");   
            
            for(int i =0; i<booklist.getLength(); i++){  
            	TransDataFormat tf = new TransDataFormat();
                //循环遍历获取每一个book    
                Node book = booklist.item(i); 
                
                //通过Node对象的getAttributes()方法获取全的属性值    
                NamedNodeMap bookmap = book.getAttributes();
                //循环遍每一个book的属性值    
                for(int j = 0; j<bookmap.getLength(); j++){    
                    Node node = bookmap.item(j);    
                    //通过Node对象的getNodeName()和getNodeValue()方法获取属性名和属性值    
                    System.out.print(node.getNodeName()+":");    
                    System.out.println(node.getNodeValue());    

                }    
                //解析book节点的子节点  
                NodeList childlist = book.getChildNodes();    
                System.out.println(childlist.getLength());
                for(int t = 0; t<childlist.getLength(); t++){    
                	//循环遍历获取每一个book    
                	if(childlist.item(t).getNodeType() == Node.ELEMENT_NODE){ 
                    Node booktmp = childlist.item(t);    
                    //通过Node对象的getAttributes()方法获取全的属性值    
                    NamedNodeMap bookmap2 = booktmp.getAttributes(); 
                    //循环遍每一个book的属性值    
                    for(int j = 0; j<bookmap2.getLength(); j++){    
                        Node node = bookmap2.item(j);    
                        //通过Node对象的getNodeName()和getNodeValue()方法获取属性名和属性值    
                        System.out.print(node.getNodeName()+":");    
                        System.out.println(node.getNodeValue());    
                    }    
                	}
                }    
                 
            }    
        } catch (ParserConfigurationException e) {    
            e.printStackTrace();    
        } catch (SAXException e) {    
            e.printStackTrace();    
        } catch (IOException e) {    
            e.printStackTrace();    
        }   
	}
}
