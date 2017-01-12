package com.unicomer.demo.buying.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.unicomer.demo.buying.service.VendorService;
import com.unicomer.demo.common.entity.Vendor;

@RestController
public class BuyingRestController {
	@Autowired VendorService vendorService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/vendors/",
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public List<Vendor> getVendors(HttpServletResponse servletResponse) {
		System.out.println("buying-provider@BuyingRestController: Inicio de servicio /vendors/");
		servletResponse.setStatus(HttpServletResponse.SC_OK);
		List<Vendor> vendorsResult = new ArrayList<Vendor>();
		try{
			Iterator<Vendor> it = vendorService.findAll().iterator();
			while (it.hasNext()) {
				Vendor vendor = it.next();
				vendorsResult.add(vendor);
			}
			if (vendorsResult.size()==0){
				servletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
			}
		}catch (Exception e) {
			servletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		
		System.out.println("buying-provider@BuyingRestController: Fin de servicio /vendors/");
		return vendorsResult;
	}
		
	@RequestMapping(method = RequestMethod.GET, value = "/vendors/{id}",
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public Vendor getVendor(@PathVariable(name="id") String id, HttpServletResponse servletResponse) {
		servletResponse.setStatus(HttpServletResponse.SC_OK);
		Vendor vendorResult = new Vendor();
		try{
			vendorResult = vendorService.findOne(id);
			if (vendorResult==null){
				servletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
			}
		}catch (Exception e) {
			servletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		
		return vendorResult;
	}
}
