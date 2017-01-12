package com.unicomer.demo.buying.service;

import java.util.List;

import com.unicomer.demo.common.entity.Vendor;


public interface VendorService {
//	public Page<Vendor> listAllByPage(Pageable pageable);
	
	public List<Vendor> findAll();
	
	public Vendor save(Vendor vendor);
	
	public boolean exists(String id);
	
	public Vendor findOne(String id);
	
	public void delete(String id);
}
