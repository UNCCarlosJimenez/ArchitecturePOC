package com.unicomer.demo.buying.service;

import java.util.List;

import com.unicomer.demo.common.entity.UnicomerVendor;


public interface VendorService {
//	public Page<Vendor> listAllByPage(Pageable pageable);
	
	public List<UnicomerVendor> findAll();
	
	public UnicomerVendor save(String transactionId, UnicomerVendor vendor);
	
	public boolean exists(String id);
	
	public UnicomerVendor findOne(String id);
	
	public void delete(String transactionId, String id);
}
