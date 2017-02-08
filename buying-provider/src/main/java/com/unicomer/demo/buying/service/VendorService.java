package com.unicomer.demo.buying.service;

import java.util.List;

import com.unicomer.demo.common.entity.UnicomerVendor;


public interface VendorService {
//	public Page<Vendor> listAllByPage(Pageable pageable);
	
	public List<UnicomerVendor> findAll(String transactionId, long startTime);
	
	public UnicomerVendor save(String transactionId, UnicomerVendor vendor);
	
	public boolean exists(String id, String transactionId, long startTime);
	
	public UnicomerVendor findOne(String id, String transactionId, long startTime);
	
	public void delete(String transactionId, String id);
}
