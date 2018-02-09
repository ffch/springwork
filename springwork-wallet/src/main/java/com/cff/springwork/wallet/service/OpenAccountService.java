package com.cff.springwork.wallet.service;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cff.springwork.network.common.StringUtil;
import com.cff.springwork.network.tcp.data.TransactionMapData;
import com.cff.springwork.wallet.common.Constant;
import com.cff.springwork.wallet.common.SequenceManager;
import com.cff.springwork.wallet.dao.WaAccountDao;
import com.cff.springwork.wallet.domain.WaAccount;
import com.cff.springwork.wallet.domain.WaItem;
import com.cff.springwork.wallet.domain.WaProduct;
import com.cff.springwork.wallet.exception.BussinessException;

@Service
public class OpenAccountService extends BusiNessService {
	private final static Logger log = LoggerFactory.getLogger(OpenAccountService.class);
	@Autowired
	ErrorCodeService errorCodeService;

	@Autowired
	WaProductService waProductService;

	@Autowired
	WaItemService waItemService;

	@Autowired
	SequenceManager sequenceManager;

	@Autowired
	WaAccountDao waAccountDao;

	@Override
	@Transactional
	public void doTrans(TransactionMapData tm) throws BussinessException {
		log.info("进入OpenAccountService业务层");
		
		if(StringUtil.isEmpty(tm.get("userNo")) || StringUtil.isEmpty(tm.get("accountType"))){
			throw new BussinessException(Constant.EXP_PARAM_ERROR);
		}
		String userNo = tm.get("userNo").toString();
		String accType = tm.get("accountType").toString();
		//查询科目号
		WaItem wi = waItemService.getItem(accType);
		
		if(wi == null)throw new BussinessException(Constant.ACCOUNT_ITEM_ERROR);
		
		//查询产品号
		WaProduct wp = waProductService.getProduct(wi.getItemNo());
		if(StringUtil.isNotEmpty(wp.getIsRepeat()) && "0".equals(wp.getIsRepeat())){
			WaAccount waAccountTmp = waAccountDao.findByUserNoAndAccType(userNo, accType);
			if(waAccountTmp != null)throw new BussinessException(Constant.ACCOUNT_ALREADY_EXIST);
		}
		
		WaAccount waAccount = new WaAccount();
		waAccount.setUserNo(tm.get("userNo").toString());
		waAccount.setAccType(tm.get("accountType").toString());
		waAccount.setPasswd(tm.get("passwd").toString());
		waAccount.setItemNo(wi.getItemNo());
		waAccount.setBalDir(wi.getBalDir());
		
		waAccount.setProductNo(wp.getProductNo());
		waAccount.setStatus("0");
		if("".equals(waAccount.getPasswd())){
			waAccount.setStatus("1");
		}
		waAccount.setAccNo(genAccNo(waAccount));
		waAccountDao.save(waAccount);
		
		tm.put(Constant.ACC_NO, waAccount.getAccNo());
		errorCodeService.genErrorReturn(tm, Constant.TRANS_SUCCESS);
	}

	public String genAccNo(WaAccount waAccount) {
		return waAccount.getAccType() + sequenceManager.offerAccNoSeq();
	}

}
