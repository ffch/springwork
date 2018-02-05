package com.cff.springwork.wallet.dao;

import org.springframework.data.repository.CrudRepository;

import com.cff.springwork.wallet.domain.WaAccountFlow;


public interface WaAccountFlowDao extends CrudRepository<WaAccountFlow, String>{
	WaAccountFlow findByAccFlow(String accFlow);
}