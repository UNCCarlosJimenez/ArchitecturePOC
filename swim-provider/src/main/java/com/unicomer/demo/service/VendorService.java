/**
 * 
 */
package com.unicomer.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.unicomer.demo.domain.SwimVendorDomain;

/**
 * @author oracle
 *
 */
public interface VendorService {
	public Page<SwimVendorDomain> listAllByPage(Pageable pageable);
	
	public List<SwimVendorDomain> findAll();
	
	public SwimVendorDomain save(SwimVendorDomain vendor);
	
	public boolean exists(String id);
	
	public SwimVendorDomain findOne(String id);
	
	public void delete(String id);
}
