package com.cff.springwork.wallet.service;

import java.util.List;

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

import net.sf.json.JSONArray;

@Service
public class AccountQueryService extends BusiNessService {
	private final static Logger log = LoggerFactory.getLogger(AccountQueryService.class);
	@Autowired
	WaTransFlowService waTransFlowService;
	@Autowired
	WaAccountDao waAccountDao;
	
	@Override
	public void doTrans(TransactionMapData tm) throws BussinessException {
		log.info("进入AccountQueryService业务层");
		String userNo = tm.get("userNo").toString();
		if (StringUtil.isEmpty(userNo)) {
			errorCodeService.genErrorReturn(tm, Constant.EXP_PARAM_ERROR);
			throw new BussinessException(Constant.EXP_PARAM_ERROR);
		}
		
		List<WaAccount> waAccount = waAccountDao.findByUserNo(userNo);
		
		JSONArray ja = new JSONArray();
		for(int i = 0;i< waAccount.size(); i++){
			ja.add(waAccount.get(i));
		}
		tm.put("accDetails", ja);
		tm.put("totalNum", waAccount.size());
		errorCodeService.genErrorReturn(tm, Constant.TRANS_SUCCESS);
		
	}
}
