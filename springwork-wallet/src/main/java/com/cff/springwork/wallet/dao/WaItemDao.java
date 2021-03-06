package com.cff.springwork.wallet.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

import com.cff.springwork.wallet.domain.WaItem;
import com.cff.springwork.wallet.domain.WaProduct;


@CacheConfig(cacheNames = "waItem")
@Transactional
public interface WaItemDao extends CrudRepository<WaItem, String>{
	@Cacheable
	List<WaItem> findByAcctType(String acctType);
}