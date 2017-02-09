/**
 * 
 */
package com.unicomer.process.buy.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.unicomer.config.LoggerClientConfiguration;
import com.unicomer.process.buy.entity.CommentLog;
import com.unicomer.process.buy.entity.EventLog;

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
	public void info(@RequestBody EventLog request);
	
	@RequestMapping(value = "/info", 
			method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.TEXT_PLAIN_VALUE)
	public void info(@RequestBody CommentLog request);
	
	@RequestMapping(value = "/error", 
			method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.TEXT_PLAIN_VALUE)
	public void error(@RequestBody EventLog request);
	
	@RequestMapping(value = "/error", 
			method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.TEXT_PLAIN_VALUE)
	public void error(@RequestBody CommentLog request);
	
	@RequestMapping(value = "/start", 
			method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.TEXT_PLAIN_VALUE)
	public void start(@RequestBody EventLog request);
	
	@RequestMapping(value = "/end", 
			method = RequestMethod.POST, 
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.TEXT_PLAIN_VALUE)
	public void end(@RequestBody EventLog request);
	
}
