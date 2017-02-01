/**
 * 
 */
package com.unicomer.demo.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.unicomer.config.LoggerClientConfiguration;
import com.unicomer.demo.common.entity.TransactionLogEndTrace;
import com.unicomer.demo.common.entity.TransactionLogInfoTrace;

/**
 * @author carlosj_rodriguez
 *
 */
@FeignClient(name = "logger-provider", url = "${logger.service.endpoint}",	configuration = LoggerClientConfiguration.class)
public interface LoggerClient {
	
	@RequestMapping(value = "/info", 
			method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.TEXT_PLAIN_VALUE)
	public void infoTrace(@RequestBody TransactionLogInfoTrace request);
	
	@RequestMapping(value = "/error", 
			method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.TEXT_PLAIN_VALUE)
	public void errorTrace(@RequestBody TransactionLogInfoTrace request);
	
	@RequestMapping(value = "/start", 
			method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.TEXT_PLAIN_VALUE)
	public void startTrace(@RequestBody TransactionLogInfoTrace request);
	
	@RequestMapping(value = "/end", 
			method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.TEXT_PLAIN_VALUE)
	public void endTrace(@RequestBody TransactionLogEndTrace request);
	
}
