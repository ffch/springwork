package com.cff.springwork.wallet.dao;

import org.springframework.data.repository.CrudRepository;

import com.cff.springwork.wallet.domain.WaAccount;

public interface WaAccountDao extends CrudRepository<WaAccount, String>{
	WaAccount findByAccNo(String accNo);
}