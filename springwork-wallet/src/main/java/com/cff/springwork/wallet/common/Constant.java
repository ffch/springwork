package com.cff.springwork.wallet.common;

public class Constant {
	public final static String XML_NONODE = "XML_NONODE";
	public final static String XML_PARSE_ERROR = "XML_PARSE_ERROR";
	public final static String XML_FILE_ERROR = "XML_FILE_ERROR";
	public final static String TRANS_NOBEAN = "TRANS_NOBEAN";
	public final static String TRANS_SUCCESS = "TRANS_SUCCESS";
	public final static String ACCOUNT_NOT_EXIST = "ACCOUNT_NOT_EXIST";
	public final static String ACCOUNT_ITEM_ERROR = "ACCOUNT_ITEM_ERROR";
	public final static String ACCOUNT_PARAM_NOT_EXIST = "PARAM_NOT_EXIST";
	public final static String PASSWD_NOT_MATCH = "PASSWD_NOT_MATCH";
	public final static String AMT_ILLEGAL = "AMT_ILLEGAL";
	public final static String EXP_ACCOUNT_ERROR = "EXP_ACCOUNT_ERROR";
	public final static String EXP_ACCOUNT_LOCK = "EXP_ACCOUNT_LOCK";
	public final static String EXP_PARAM_ERROR = "PARAM_ERROR";
	
	public final static String SUCCESS_CODE = "00000000";

	public final static String TRANS_STATUS_SUCCESS = "0";
	public final static String TRANS_STATUS_ACTIVE = "1";
	public final static String TRANS_STATUS_UNSUCCESS = "2";

	public final static String TRAN_FLOW_SEQ = "tranflowseq";
	public final static String ACC_NO_SEQ = "accnoseq";
	public final static String ACCOUNT_FLOW_SEQ = "accountflowseq";

	public final static String USER_NO = "userNo";
	public final static String ACC_NO = "accNo";
	/**
	 * 借方 资产类账户，借方登记增加数，贷方登记减少数，Dr 是英文debit 即借方的缩写,Cr 是credit 即贷方的缩写
	 */
	public final static String BAL_DIR_DR = "1";
	/**
	 * 贷方 资产类账户，借方登记增加数，贷方登记减少数，Dr 是英文debit 即借方的缩写,Cr 是credit 即贷方的缩写
	 */
	public final static String BAL_DIR_CR = "2";
	/**
	 * 借贷双方
	 */
	public final static String BAL_DIR_All = "0";

	public final static String ACCOUNT_STATUS_CLOSE = "2";

	public final static String ACCOUNT_STATUS_NORMAL = "0";

	public final static String ACCOUNT_STATUS_UNACTIVE = "1";

}
