package com.cff.springwork.wallet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cff.springwork.wallet.dao.WaItemDao;
import com.cff.springwork.wallet.domain.WaItem;

@Service
public class WaItemService {
	
	@Autowired
	WaItemDao waItemDao;
	
	public WaItem getItem(String acctType){
		List<WaItem> list = waItemDao.findByAcctType(acctType);
		if(list == null || list.size() <=0)return null;
		return list.get(0);
	}
}
