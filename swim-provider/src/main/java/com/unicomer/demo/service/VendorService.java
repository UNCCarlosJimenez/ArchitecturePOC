/**
 * 
 */
package com.unicomer.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.unicomer.demo.domain.SwimVendor;
import com.unicomer.demo.service.VendorService;

/**
 * @author oracle
 *
 */
public interface VendorService {
	public Page<SwimVendor> listAllByPage(Pageable pageable);
	
	public List<SwimVendor> findAll();
	
	public SwimVendor save(SwimVendor vendor);
	
	public boolean exists(String id);
	
	public SwimVendor findOne(String id);
	
	public void delete(String id);
}
