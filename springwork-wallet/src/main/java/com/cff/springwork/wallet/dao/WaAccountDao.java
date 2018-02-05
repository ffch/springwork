package com.cff.springwork.wallet.dao;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;

import com.cff.springwork.wallet.domain.WaAccount;

public interface WaAccountDao extends CrudRepository<WaAccount, String>{
	WaAccount findByAccNo(String accNo);
	WaAccount findByAccNoAndStatus(String accNo,String status);
	
	@Lock(value = LockModeType.PESSIMISTIC_WRITE) 
	WaAccount findWaAccountLockedByAccNo(String accNo);
}