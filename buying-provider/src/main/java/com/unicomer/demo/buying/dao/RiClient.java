package com.unicomer.demo.buying.dao;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.unicomer.config.RiClientConfiguration;
import com.unicomer.demo.buying.domain.ri.Vendor;

@FeignClient(name = "ri-provider", url = "${ri.service.endpoint}",	configuration = RiClientConfiguration.class)
// @EnableHypermediaSupport(type=EnableHypermediaSupport.HypermediaType.HAL)
public interface RiClient {
	@RequestMapping(value = "/riVendors", 
			method = RequestMethod.GET, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public Vendor[] getVendors();

}
