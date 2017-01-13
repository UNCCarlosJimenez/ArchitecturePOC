package com.unicomer.demo.buying.dao;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.unicomer.config.RiClientConfiguration;
import com.unicomer.demo.ri.domain.RiVendor;

@FeignClient(name = "ri-provider", url = "${ri.service.endpoint}",	configuration = RiClientConfiguration.class)
// @EnableHypermediaSupport(type=EnableHypermediaSupport.HypermediaType.HAL)
public interface RiClient {
	@RequestMapping(value = "/riVendors", 
			method = RequestMethod.GET, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public com.unicomer.demo.ri.domain.RiVendor[] getVendors();
	
	
	@RequestMapping(value = "/riVendors", 
			method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public RiVendor addVendors(RiVendor vendor);
	
	@RequestMapping(value = "/riVendors/{id}", 
			method = RequestMethod.PUT, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public RiVendor updateVendors(RiVendor vendor, @PathVariable(name="id") String id);
	
	@RequestMapping(value = "/riVendors/{id}", 
			method = RequestMethod.DELETE, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public RiVendor deleteVendors(@PathVariable(name="id") String id);

}
