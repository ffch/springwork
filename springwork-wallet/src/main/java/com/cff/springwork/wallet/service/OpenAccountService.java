package com.cff.springwork.wallet.service;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cff.springwork.wallet.common.Constant;
import com.cff.springwork.wallet.common.SequenceManager;
import com.cff.springwork.wallet.dao.WaAccountDao;
import com.cff.springwork.wallet.domain.WaAccount;
import com.cff.springwork.wallet.domain.WaItem;
import com.cff.springwork.wallet.domain.WaProduct;
import com.cff.springwork.wallet.trans.data.TransactionMapData;

@Service
public class OpenAccountService implements BusiNessService{
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
	public TransactionMapData trans(TransactionMapData tm) {
		log.info("进入OpenAccountService业务层");
		WaAccount waAccount = new WaAccount();
		waAccount.setUserNo(tm.get("userNo").toString());
		waAccount.setAccType(tm.get("accountType").toString());
		waAccount.setPasswd(tm.get("passwd").toString());
		
		//查询科目号
		WaItem wi = waItemService.getItem(waAccount.getAccType());
		waAccount.setItemNo(wi.getItemNo());
		waAccount.setBalDir(wi.getBalDir());
		//查询产品号
		WaProduct wp = waProductService.getProduct(wi.getItemNo());
		waAccount.setProductNo(wp.getProductNo());
		waAccount.setStatus("0");
		if("".equals(waAccount.getPasswd())){
			waAccount.setStatus("1");
		}
		waAccount.setAccNo(genAccNo(waAccount));
		waAccountDao.save(waAccount);
		
		tm.put(Constant.ACC_NO, waAccount.getAccNo());
		
		errorCodeService.genErrorReturn(tm, Constant.TRANS_SUCCESS);
		return tm;
	}
	
	public String genAccNo(WaAccount waAccount){
		return waAccount.getAccType() + sequenceManager.offerAccNoSeq();
	}
	
}
