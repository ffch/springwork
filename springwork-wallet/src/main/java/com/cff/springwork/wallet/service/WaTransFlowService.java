package com.cff.springwork.wallet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cff.springwork.wallet.common.SequenceManager;
import com.cff.springwork.wallet.dao.WaTransFlowDao;
import com.cff.springwork.wallet.domain.WaTransFlow;
import com.cff.springwork.wallet.util.DateUtil;

@Service
public class WaTransFlowService {
	
	@Autowired
	WaTransFlowDao waTransFlowDao;
	@Autowired
	SequenceManager sequenceManager;
	
	public void save(WaTransFlow waTransFlow){
		String seq = DateUtil.formatNowFull() + sequenceManager.offerTransFlowSeq();
		waTransFlow.setTransFlow(seq);
		waTransFlowDao.save(waTransFlow);
	}
}
