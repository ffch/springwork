package com.cff.springwork.wallet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cff.springwork.wallet.common.Constant;
import com.cff.springwork.wallet.domain.WaTransFlow;
import com.cff.springwork.wallet.exception.BussinessException;
import com.cff.springwork.wallet.trans.data.TransactionMapData;
import com.cff.springwork.wallet.util.DateUtil;
import com.cff.springwork.wallet.util.StringUtil;

@Service
public abstract class BusiNessService {
	@Autowired
	WaTransFlowService waTransFlowService;
	@Autowired
	ErrorCodeService errorCodeService;
	
	public TransactionMapData trans(TransactionMapData tm){
		try {
			preTrans(tm);
			doTrans(tm);
			aftTrans(tm);
		} catch (BussinessException e) {
			String errorName = e.getErrorName();
			errorCodeService.genErrorReturn(tm, errorName);
		}
		
		return tm;
	}
	
	public void preTrans(TransactionMapData tm) throws BussinessException{
		//产生流水
		WaTransFlow wtf = new WaTransFlow();
		wtf.setTrancode(tm.getTransCode());
		wtf.setTransDate(DateUtil.formatNow8());
		wtf.setTransTime(DateUtil.formatNowTime6());
		wtf.setUserNo(tm.get("userNo") == null ? "" : tm.get("userNo").toString());
		wtf.setTranStatus("1");
		waTransFlowService.save(wtf);
		tm.put("transFlow", wtf);
	}
	
	public void aftTrans(TransactionMapData tm) throws BussinessException{
		WaTransFlow wtf = (WaTransFlow) tm.get("transFlow");
		wtf.setRetCode(tm.get("errCode") == null ? "": tm.get("errCode").toString());
		wtf.setRetRemark(tm.get("errMsg") == null ? "": tm.get("errMsg").toString());
		wtf.setTranStatus(Constant.TRANS_STATUS_SUCCESS);
		if(!Constant.SUCCESS_CODE.equals(wtf.getRetCode())){
			wtf.setTranStatus(Constant.TRANS_STATUS_UNSUCCESS);
		}
		waTransFlowService.save(wtf);
	}
	
	public abstract void doTrans(TransactionMapData tm) throws BussinessException;
}
