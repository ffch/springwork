package com.cff.springwork.wallet.dao;

import javax.transaction.Transactional;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;
import com.cff.springwork.wallet.domain.WaLiqAccount;


@CacheConfig(cacheNames = "waLiqAccount")
@Transactional
public interface WaLiqAccountDao extends CrudRepository<WaLiqAccount, String>{
	@Cacheable
	WaLiqAccount findByItemNo(String itemNo);
}