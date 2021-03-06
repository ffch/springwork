package com.cff.springwork.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cff.springwork.model.user.UserBind;
import com.cff.springwork.mybatis.service.UserBindService;
import com.cff.springwork.network.common.DateUtil;
import com.cff.springwork.network.common.TransConstant;
import com.cff.springwork.network.tcp.data.TransactionMapData;
import com.cff.springwork.web.cache.SequenceManager;
import com.cff.springwork.web.client.tcp.TransSocketClient;

@Service
public class AccountOpenService implements BusinessService{
	@Autowired
	TransSocketClient transSocketClient;
	@Autowired
	SequenceManager sequenceManager;
	@Autowired
	UserBindService userBindService;
	
	
	@Override
	public TransactionMapData trans(TransactionMapData tm) throws Exception {
		String reqFlow = sequenceManager.getReqFlow();
		tm.put("reqTransFlow", reqFlow);
		tm.put("transDate", DateUtil.formatNow8());
		tm.put("accountType", "1001");
		tm.setTransCode("000001");
		TransactionMapData res = transSocketClient.sendAndWaitResult(tm);
		if(TransConstant.SUCCESS_CODE.equals(res.get("errCode").toString())){
			UserBind userBind = new UserBind();
			userBind.setUserNo(res.get(TransConstant.USER_NO).toString());
			userBind.setHasAccount("0");
			userBind.setAccNo(res.get(TransConstant.ACC_NO).toString());
			userBindService.save(userBind );
		}
		return res;
	}
	
}
