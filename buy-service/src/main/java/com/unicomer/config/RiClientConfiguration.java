/**
 * 
 */
package com.unicomer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Logger;
import feign.Request;

/**
 * @author carlosj_rodriguez
 *
 */
@Configuration
public class RiClientConfiguration {
	@Value("${ri.service.connect-timeout}") String connectionTimeout;
	@Value("${ri.service.response-timeout}") String responseTimeout;
	
    @Bean
    public Logger.Level feignLogger() {
        return Logger.Level.FULL;
    }
    @Bean
    public Request.Options options() {
    	Request.Options options = new Request.Options(Integer.valueOf(connectionTimeout), Integer.valueOf(responseTimeout));    	
    	return options;
    }
}
