package com.unicomer.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.unicomer.demo.domain.SwimVendorDomain;
import com.unicomer.demo.repository.SwimVendorRepository;

@Service("vendorService")
public class VendorServiceImpl implements VendorService {
	@Autowired SwimVendorRepository vendorRepository;	
	
	@Override
	public Page<SwimVendorDomain> listAllByPage(Pageable pageable) {
		return vendorRepository.findAll(pageable);
	}

	@Override
	public List<SwimVendorDomain> findAll() {
		return vendorRepository.findAll();
	}

	@Override
	public SwimVendorDomain save(SwimVendorDomain vendor) {
		return vendorRepository.save(vendor);
	}

	@Override
	public boolean exists(String id) {
		return vendorRepository.exists(id);
	}

	@Override
	public SwimVendorDomain findOne(String id) {
		return vendorRepository.findOne(id);
	}

	@Override
	public void delete(String id) {
		vendorRepository.delete(id);
	}

}
