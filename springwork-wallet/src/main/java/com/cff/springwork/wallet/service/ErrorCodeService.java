package com.cff.springwork.wallet.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cff.springwork.wallet.dao.ErrorCodeDao;
import com.cff.springwork.wallet.domain.ErrorCode;
import com.cff.springwork.wallet.trans.data.TransactionMapData;

@Service
public class ErrorCodeService {
	@Autowired
	ErrorCodeDao errorCodeDao;
	Map<String,ErrorCode> errMap = new HashMap<String,ErrorCode>();
	
	
	@PostConstruct
	public void init(){
		Iterable<ErrorCode> errs = errorCodeDao.findAll();
		errs.forEach(single ->{
			errMap.put(single.getErrName(),single);
		});  
	}
	
	public ErrorCode getErrCode(String errName){
		if(errMap == null || errMap.size() <=0){
			ErrorCode tmp = new ErrorCode();
			tmp.setErrName("UNKNOW");
			tmp.setErrCode("11111111");
			tmp.setErrMsg("未知错误");
			return tmp;
		}
		return errMap.get(errName);
	}
	
	public void genErrorReturn(TransactionMapData tm,String errorName){
		if(errMap == null || errMap.size() <=0){
			tm.put("errCode", "11111111");
			tm.put("errMsg", "未知错误");
		}else{
			ErrorCode ec = errMap.get(errorName);
			tm.put("errCode", ec.getErrCode());
			tm.put("errMsg", ec.getErrMsg());
		}
	}
}
