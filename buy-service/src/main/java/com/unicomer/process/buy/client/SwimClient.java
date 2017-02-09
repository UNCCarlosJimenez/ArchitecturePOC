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

import com.unicomer.config.SwimClientConfiguration;
import com.unicomer.demo.common.header.ResponseHeader;
import com.unicomer.demo.swim.domain.SwimVendor;

/**
 * @author carlosj_rodriguez
 *
 */
@FeignClient(name = "swim-provider", url = "${swim.service.endpoint}",	configuration = SwimClientConfiguration.class)
public interface SwimClient {
	
	@RequestMapping(value = "/vendors", 
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public SwimVendor.ResponseMessage getAllVendors();
		
	@RequestMapping(value = "/vendors/{id}", 
			method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public SwimVendor.ResponseMessage getVendor(@PathVariable(name="id") String id);
	
	@RequestMapping(value = "/vendors/", 
			method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public SwimVendor.ResponseMessage addVendor(@RequestBody SwimVendor.RequestMessage request);
	
	@RequestMapping(value = "/vendors/{id}", 
			method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public SwimVendor.ResponseMessage updateVendor(@RequestBody SwimVendor.RequestMessage request, @PathVariable(name="id") String id);
	
	
	@RequestMapping(value = "/vendors/{id}", 
			method = RequestMethod.DELETE, 
			consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseHeader deleteVendor(@PathVariable(name="id") String id);
	
}