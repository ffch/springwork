package com.cff.springwork.network.common;

public class TransConstant {
	public final static String SUCCESS_CODE = "00000000";

	public final static String TRANS_STATUS_SUCCESS = "0";
	public final static String TRANS_STATUS_ACTIVE = "1";
	public final static String TRANS_STATUS_UNSUCCESS = "2";

	public final static String USER_NO = "userNo";
	public final static String ACC_NO = "accNo";
	public final static String PASSWD = "passwd";
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
