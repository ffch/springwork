package com.cff.springwork.wallet.dao;

import org.springframework.data.repository.CrudRepository;

import com.cff.springwork.wallet.domain.WaTransFlow;


public interface WaTransFlowDao extends CrudRepository<WaTransFlow, String>{
	WaTransFlow findByTransFlow(String transFlow);
}