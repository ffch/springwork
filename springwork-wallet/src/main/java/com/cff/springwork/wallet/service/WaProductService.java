package com.cff.springwork.wallet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cff.springwork.wallet.dao.WaProductDao;
import com.cff.springwork.wallet.domain.WaProduct;

@Service
public class WaProductService {
	
	@Autowired
	WaProductDao waProductDao;
	
	public WaProduct getProduct(String itemNo){
		List<WaProduct> list = waProductDao.findByItemNo(itemNo);
		if(list == null || list.size() <=0)return null;
		return list.get(0);
	}
}
