/**
 * 
 */
package com.unicomer.process.buy.service;

import java.util.List;

import com.unicomer.demo.common.entity.UnicomerVendor;

/**
 * @author carlosj_rodriguez
 *
 */
public interface VendorService {
	public List<UnicomerVendor> findAll(String transactionId, long startTime);
	
	public UnicomerVendor save(String transactionId, long startTime, UnicomerVendor vendor);
	
	public UnicomerVendor update(String transactionId, long startTime, UnicomerVendor vendor, String id);
	
	public boolean exists(String id, String transactionId, long startTime);
	
	public UnicomerVendor findOne(String id, String transactionId, long startTime);
	
	public void delete(String transactionId, long startTime, String id);
	
}
