package com.cff.springwork.wallet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cff.springwork.wallet.dao.WaLiqAccountDao;
import com.cff.springwork.wallet.domain.WaLiqAccount;

@Service
public class WaLiqAccountService {
	
	@Autowired
	WaLiqAccountDao waLiqAccountDao;
	
	public WaLiqAccount findByItemNo(String itemNo){
		return waLiqAccountDao.findByItemNo(itemNo);
	}
}
