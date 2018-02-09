package com.cff.springwork.network.common;

public class XmlErrorConstant {
	public final static String EXP_XML_NONODE = "0001";
	public final static String EXP_XML_PARSE_ERROR = "0002";
	public final static String EXP_XML_FILE_ERROR = "0003";
	public final static String EXP_TRANS_NOBEAN = "0004";
	public final static String EXP_PARAM_ERROR = "0005";
	public final static String EXP_MSG_TOOLONG = "0006";
	public final static String SUCCESS = "0000";
	
	public final static String EXP_XML_NONODE_MSG="对应节点不存在";
	public final static String EXP_XML_PARSE_ERROR_MSG="xml解析失败";
	public final static String EXP_XML_FILE_ERROR_MSG="xml文件不存在或格式错误";
	public final static String EXP_MSG_TOOLONG_MSG="报文过长";
	
	public final static String EXP_XMLFILE_NOTEXIST = "0007";
	public final static String EXP_XMLFILE_NOTEXIST_MSG = "未配置xml文件";
	
	public final static String EXP_XMLTAG_NOT_MATCH = "0008";
	public final static String EXP_XMLTAG_NOT_MATCH_MSG = "xml标记和xml文件数量不一致";
	
	public final static String EXP_XMLFILE_NOT_MATCH = "0009";
	public final static String EXP_XMLFILE_NOT_MATCH_MSG = "请求文件数量要和响应文件数量一致";
	
	public final static String EXP_MSGHEAD_NOT_COMPLETE = "0010";
	public final static String EXP_MSGHEAD_NOT_COMPLETE_MSG = "报文头不完整";
}
