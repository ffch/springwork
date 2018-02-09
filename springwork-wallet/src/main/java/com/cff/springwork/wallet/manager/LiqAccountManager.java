package com.cff.springwork.wallet.manager;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cff.springwork.network.common.DateUtil;
import com.cff.springwork.network.common.StringUtil;
import com.cff.springwork.network.tcp.data.TransactionMapData;
import com.cff.springwork.wallet.common.Constant;
import com.cff.springwork.wallet.common.SequenceManager;
import com.cff.springwork.wallet.dao.WaAccountDao;
import com.cff.springwork.wallet.dao.WaAccountFlowDao;
import com.cff.springwork.wallet.dao.WaDrcrControlDao;
import com.cff.springwork.wallet.domain.WaAccount;
import com.cff.springwork.wallet.domain.WaAccountFlow;
import com.cff.springwork.wallet.domain.WaDrcrControl;
import com.cff.springwork.wallet.domain.WaLiqAccount;
import com.cff.springwork.wallet.domain.WaTransFlow;
import com.cff.springwork.wallet.exception.BussinessException;
import com.cff.springwork.wallet.service.WaLiqAccountService;

@Service
public class LiqAccountManager {
	private final static Logger log = LoggerFactory.getLogger(LiqAccountManager.class);

	@Autowired
	WaLiqAccountService waLiqAccountService;
	@Autowired
	WaDrcrControlDao waDrcrControlDao;
	@Autowired
	WaAccountDao waAccountDao;
	@Autowired
	WaAccountFlowDao waAccountFlowDao;
	@Autowired
	SequenceManager sequenceManager;

	@Transactional
	public void trans(TransactionMapData tm) throws BussinessException {
		WaDrcrControl wdc = waDrcrControlDao.findByTransCode(tm.getTransCode());
		String drcrDir = wdc.getDrcrDir();
		switch (drcrDir) {
		case "1":
			if (StringUtil.isEmpty(tm.get(wdc.getDrAccno()))) {
				throw new BussinessException(Constant.ACCOUNT_PARAM_NOT_EXIST);
			}
			if (StringUtil.isEmpty(tm.get(wdc.getTransAmt()))) {
				throw new BussinessException(Constant.AMT_ILLEGAL);
			}
			tm.put("liqAmt", tm.get(wdc.getTransAmt()));
			String drAccno = tm.get(wdc.getDrAccno()).toString();
			log.info("当前借方账号为：{}",drAccno);
			WaAccount drAcc = waAccountDao.findWaAccountLockedByAccNo(drAccno);
			String drItem = drAcc.getItemNo();
			WaLiqAccount waLiqAccount = waLiqAccountService.findByItemNo(drItem);
			String crAccno = waLiqAccount.getLliqcAcctno();
			log.info("当前贷方科目为：{}",crAccno);
			WaAccount crAcc = waAccountDao.findWaAccountLockedByAccNo(crAccno);
			liquid(drAcc, crAcc, tm);
			tm.put("bal", drAcc.getBal());
			break;
		case "2":

			break;
		case "0":

			break;
		default:
			break;
		}
	}

	public void liquid(WaAccount drAcc, WaAccount crAcc, TransactionMapData tm) {
		int transAmt = Integer.parseInt(tm.get("liqAmt").toString());
		WaTransFlow wtf = (WaTransFlow) tm.get("transFlow");
		String transFlow = wtf.getTransFlow();
		drAcc.setBal(drAcc.getBal() + transAmt);
		drAcc.setAvaBal(drAcc.getAvaBal() + transAmt);
		waAccountDao.save(drAcc);
		
		String accountFlowSeq = DateUtil.formatNowFull() + sequenceManager.offerTransFlowSeq();
		WaAccountFlow wfDr = new WaAccountFlow();
		wfDr.setAccFlow(accountFlowSeq);
		wfDr.setTranFlow(transFlow);
		wfDr.setBalDir(drAcc.getBalDir());
		wfDr.setFlag("0");
		wfDr.setOppAccno(crAcc.getAccNo());
		wfDr.setOppAmt(0);
		wfDr.setOppItem(crAcc.getItemNo());
		wfDr.setTransAccno(drAcc.getAccNo());
		wfDr.setTransAmt(transAmt);
		wfDr.setTransBal(drAcc.getBal());
		wfDr.setTransDate(DateUtil.formatNow8());
		wfDr.setTransItem(drAcc.getItemNo());
		wfDr.setTransTime(DateUtil.formatNowTime6());
		waAccountFlowDao.save(wfDr);
		
		crAcc.setBal(crAcc.getBal() - transAmt);
		crAcc.setAvaBal(crAcc.getAvaBal() - transAmt);
		waAccountDao.save(crAcc);
		
		WaAccountFlow wfCr = new WaAccountFlow();
		wfCr.setAccFlow(accountFlowSeq);
		wfCr.setTranFlow(transFlow);
		wfCr.setBalDir(crAcc.getBalDir());
		wfCr.setFlag("0");
		wfCr.setOppAccno(drAcc.getAccNo());
		wfCr.setOppAmt(transAmt);
		wfCr.setOppItem(drAcc.getItemNo());
		wfCr.setTransAccno(crAcc.getAccNo());
		wfCr.setTransAmt(0);
		wfCr.setTransBal(crAcc.getBal());
		wfCr.setTransDate(DateUtil.formatNow8());
		wfCr.setTransItem(crAcc.getItemNo());
		wfCr.setTransTime(DateUtil.formatNowTime6());
		waAccountFlowDao.save(wfCr);
	}
}
