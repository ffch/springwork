package com.cff.springwork.wallet.service;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cff.springwork.wallet.common.Constant;
import com.cff.springwork.wallet.dao.WaAccountDao;
import com.cff.springwork.wallet.domain.WaAccount;
import com.cff.springwork.wallet.domain.WaLiqAccount;
import com.cff.springwork.wallet.domain.WaTransFlow;
import com.cff.springwork.wallet.exception.BussinessException;
import com.cff.springwork.wallet.manager.LiqAccountManager;
import com.cff.springwork.wallet.trans.data.TransactionMapData;
import com.cff.springwork.wallet.util.DateUtil;

@Service
public class AccountRechargeService extends BusiNessService {
	private final static Logger log = LoggerFactory.getLogger(AccountRechargeService.class);
	@Autowired
	WaTransFlowService waTransFlowService;
	@Autowired
	WaAccountDao waAccountDao;
	@Autowired
	WaLiqAccountService waLiqAccountService;
	
	@Autowired
	LiqAccountManager liqAccountManager;

	@Override
	@Transactional
	public void doTrans(TransactionMapData tm) throws BussinessException {
		log.info("进入AccountRechargeService业务层");
		String amt = tm.get("transAmt").toString();
		String accNo = tm.get("accNo").toString();
		if (StringUtils.isEmpty(accNo)) {
			errorCodeService.genErrorReturn(tm, Constant.ACCOUNT_NOT_EXIST);
			throw new BussinessException(Constant.ACCOUNT_NOT_EXIST);
		}
		WaAccount waAccount = waAccountDao.findByAccNo(accNo);
		String passwd = tm.get("passwd").toString();
		String passwdJudge = tm.get("passwd").toString();
		if (!"0".equals(passwdJudge)) {
			if (!passwd.equals(waAccount.getPasswd())) {
				errorCodeService.genErrorReturn(tm, Constant.PASSWD_NOT_MATCH);
				throw new BussinessException(Constant.PASSWD_NOT_MATCH);
			}
		}
		int transAmt = Integer.parseInt(amt);

		WaLiqAccount waLiqAccount = waLiqAccountService.findByItemNo(waAccount.getItemNo());
		waLiqAccount.setAccNo(waAccount.getAccNo());
		waLiqAccount.setBalDir(Constant.BAL_DIR_CR);
		waLiqAccount.setTransAmt(transAmt);

		liqAccountManager.trans(waLiqAccount);
		
		waAccountDao.save(waAccount);

		errorCodeService.genErrorReturn(tm, Constant.TRANS_SUCCESS);
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
		wtf.setTranStatus("1");
		wtf.setTransAmt(transAmt);
		waTransFlowService.save(wtf);
		tm.put("transFlow", wtf.getTransFlow());
	}

	@Override
	public void aftTrans(TransactionMapData tm) {
		String transFlow = "";
		transFlow = tm.get("transFlow") == null ? "" : tm.get("").toString();
		if(StringUtils.isNotEmpty(transFlow)){
			WaTransFlow wtf = waTransFlowService.findByTransFlow(transFlow);
			wtf.setRetCode(tm.get("errCode") == null ? "": tm.get("errCode").toString());
			wtf.setRetRemark(tm.get("errMsg") == null ? "": tm.get("errMsg").toString());
			wtf.setTranStatus(tm.get("transStatus") == null ? "": tm.get("transStatus").toString());
			waTransFlowService.save(wtf);
		}
	}

}
