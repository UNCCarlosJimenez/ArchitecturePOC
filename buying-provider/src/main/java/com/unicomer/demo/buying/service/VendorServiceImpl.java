package com.unicomer.demo.buying.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unicomer.demo.buying.dao.RiClient;
import com.unicomer.demo.buying.dao.SwimClient;
import com.unicomer.demo.common.entity.Vendor;
import com.unicomer.demo.swim.domain.SwimVendor;

@Service("vendorService")
public class VendorServiceImpl implements VendorService {
	@Autowired
	SwimClient swimClient;
	@Autowired
	RiClient riClient;

	@Override
	public List<Vendor> findAll() {
		System.out.println("buying-provider@VendorServiceImpl: Inicio de findAll()");
		List<Vendor> resultList = new ArrayList<Vendor>();
		/**
		 * 
		 */
		try {
			List<SwimVendor> swimVendors = swimClient.getVendors().getData();
			System.out.println("buying-provider@VendorServiceImpl: resultado de servicio swimVendors: " + swimVendors.size() + " elementos.");
			
			Iterator<SwimVendor> it = swimVendors.iterator();
			while (it.hasNext()) {
				SwimVendor swimVendor = it.next();
				Vendor vendor = new Vendor();

				vendor.setVendorId(swimVendor.getVendorId());
				vendor.setName(swimVendor.getName());
				resultList.add(vendor);
			}
		} catch (Exception e) {
			System.err.println("buying-provider@VendorServiceImpl: " + e.getMessage());
			e.printStackTrace();
		}
		System.out.println("buying-provider@VendorServiceImpl: Fin de findAll()");
		return resultList;
	}

	@Override
	public Vendor save(Vendor vendor) {
		Vendor result = new Vendor();

		return result;
	}

	@Override
	public boolean exists(String id) {
		try {
			List<SwimVendor> swimVendors = swimClient.getVendor(id).getData();
			if (swimVendors.size() > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Vendor findOne(String id) {
		Vendor result = null;
		try {
			List<SwimVendor> swimVendors = swimClient.getVendor(id).getData();
			if (swimVendors.size() > 0) {
				SwimVendor vendor = swimVendors.get(0);
				result = new Vendor();
				result.setVendorId(vendor.getVendorId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public void delete(String id) {
		try {
			swimClient.deleteVendor(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
