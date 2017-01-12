package com.unicomer.demo.controller;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.unicomer.demo.domain.RiVendor;
import com.unicomer.demo.repository.RiVendorJpa;

@RestController
public class VendorRestController {
	@Autowired RiVendorJpa vendorJpaRepo;
	@Value(value="${spring.jpa.properties.hibernate.dialect}") String hibernateDialect;
	@Value("${application.environment}") String environmentName;
	@Value("${application.instances}") Integer numberOfInstances;
	
	@RequestMapping(method = RequestMethod.GET, value = "/config/dialect",
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public String getDialect(){
		return hibernateDialect;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/config/custom",
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public String getCustom(){
		return environmentName;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/config/instances/{times}",
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public Integer getInstances(@PathVariable Integer times){
		return numberOfInstances*times;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/vendors",
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public Set<RiVendor> getVendors() {
		Set<RiVendor> vendors = new HashSet<RiVendor>();
		Iterator<RiVendor> it = vendorJpaRepo.findAll().iterator();
		while (it.hasNext()) {
			vendors.add(it.next());
		}
		return vendors;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/vendors/{id}",
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public RiVendor getVendorById(@PathVariable String id) {
		return vendorJpaRepo.findOne(id);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/vendors",
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
			consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public RiVendor addVendor(@RequestBody RiVendor vendor) {
		System.out.println("****************************");
		System.out.println("ri-provider: Vendor="+vendor.toString());
		System.out.println("****************************");
		return vendorJpaRepo.save(vendor);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/vendors",
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
			consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public RiVendor updateVendor(@RequestBody RiVendor vendor) {
		return vendorJpaRepo.save(vendor);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/vendors/{id}")
	public void deleteTeam(@PathVariable String id) {
		vendorJpaRepo.delete(id);
	}
}
