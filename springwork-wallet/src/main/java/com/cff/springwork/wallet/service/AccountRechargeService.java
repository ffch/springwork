package com.cff.springwork.wallet.service;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cff.springwork.wallet.common.Constant;
import com.cff.springwork.wallet.dao.WaAccountDao;
import com.cff.springwork.wallet.domain.WaAccount;
import com.cff.springwork.wallet.domain.WaTransFlow;
import com.cff.springwork.wallet.exception.BussinessException;
import com.cff.springwork.wallet.manager.LiqAccountManager;
import com.cff.springwork.wallet.trans.data.TransactionMapData;
import com.cff.springwork.wallet.util.DateUtil;
import com.cff.springwork.wallet.util.StringUtil;

@Service
public class AccountRechargeService extends BusiNessService {
	private final static Logger log = LoggerFactory.getLogger(AccountRechargeService.class);
	@Autowired
	WaTransFlowService waTransFlowService;
	@Autowired
	WaAccountDao waAccountDao;
	
	@Autowired
	LiqAccountManager liqAccountManager;

	@Override
	public void doTrans(TransactionMapData tm) throws BussinessException {
		log.info("进入AccountRechargeService业务层");
		String accNo = tm.get("accNo").toString();
		if (StringUtil.isEmpty(accNo)) {
			errorCodeService.genErrorReturn(tm, Constant.ACCOUNT_NOT_EXIST);
			throw new BussinessException(Constant.ACCOUNT_NOT_EXIST);
		}
		String passwd = tm.get("passwd").toString();
		String passwdJudge = tm.get("passwdJudge").toString();
		WaAccount waAccount = waAccountDao.findByAccNoAndStatus(accNo,Constant.ACCOUNT_STATUS_NORMAL);

		if (!"0".equals(passwdJudge)) {
			if(waAccount == null){
				throw new BussinessException(Constant.EXP_ACCOUNT_ERROR);
			}
			if (!passwd.equals(waAccount.getPasswd())) {
				errorCodeService.genErrorReturn(tm, Constant.PASSWD_NOT_MATCH);
				throw new BussinessException(Constant.PASSWD_NOT_MATCH);
			}
			if(!"0".equals(waAccount.getLockFlag())){
				throw new BussinessException(Constant.EXP_ACCOUNT_LOCK);
			}

		}
		
		tm.put("drAccno", accNo);
		
		liqAccountManager.trans(tm);

		errorCodeService.genErrorReturn(tm, Constant.TRANS_SUCCESS);
		
		WaTransFlow wtf = (WaTransFlow) tm.get("transFlow");
		wtf.setUserNo(waAccount.getUserNo());
		wtf.setTransItem(waAccount.getItemNo());
	}

	@Override
	public void preTrans(TransactionMapData tm) throws BussinessException {
		String amt = tm.get("transAmt").toString();
		if (!amt.matches("[0-9]+")) {
			errorCodeService.genErrorReturn(tm, Constant.AMT_ILLEGAL);
			throw new BussinessException(Constant.AMT_ILLEGAL);
		}
		int transAmt = Integer.parseInt(amt);
		// 产生流水
		WaTransFlow wtf = new WaTransFlow();
		wtf.setTrancode(tm.getTransCode());
		wtf.setTransDate(DateUtil.formatNow8());
		wtf.setTransTime(DateUtil.formatNowTime6());
		wtf.setUserNo(tm.get("userNo") == null ? "" : tm.get("userNo").toString());
		wtf.setTransAcct(tm.get("accNo") == null ? "" : tm.get("accNo").toString());
		wtf.setTranStatus(Constant.TRANS_STATUS_ACTIVE);
		wtf.setTransAmt(transAmt);
		waTransFlowService.save(wtf);
		tm.put("transFlow", wtf);
	}

}
