/**
 * 
 */
package com.unicomer.process.buy.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.unicomer.config.RiClientConfiguration;
import com.unicomer.demo.ri.domain.RiVendor;

/**
 * @author carlosj_rodriguez
 *
 */
@FeignClient(name = "ri-provider", url = "${ri.service.endpoint}",	configuration = RiClientConfiguration.class)
public interface RiClient {
	
	@RequestMapping(value = "/riVendorDomains", 
			method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public com.unicomer.demo.ri.domain.RiVendor addVendor(@RequestBody RiVendor vendor);
	
	@RequestMapping(value = "/riVendorDomains/{id}", 
			method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public com.unicomer.demo.ri.domain.RiVendor updateVendor(@RequestBody RiVendor vendor, @PathVariable(name="id") String id);
	
	
	@RequestMapping(value = "/riVendorDomains/{id}", 
			method = RequestMethod.DELETE, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public void deleteVendor(@PathVariable(name="id") String id);
	
}
