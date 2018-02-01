package com.cff.springwork.wallet.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

import com.cff.springwork.wallet.domain.WaProduct;


@CacheConfig(cacheNames = "waProduct")
@Transactional
public interface WaProductDao extends CrudRepository<WaProduct, String>{
	@Cacheable
	List<WaProduct> findByItemNo(String itemNo);
}