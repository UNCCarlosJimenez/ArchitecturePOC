package com.unicomer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Logger;
import feign.Request;

@Configuration
public class SwimClientConfiguration {
	@Value("${swim.service.connect-timeout}") String connectionTimeout;
	@Value("${swim.service.response-timeout}") String responseTimeout;
	
    @Bean
    public Logger.Level feignLogger() {
        return Logger.Level.FULL;
    }
    @Bean
    public Request.Options options() {
    	System.out.println("buying-provider@SwimClientConfiguration: connectionTimeout=" + connectionTimeout);
    	System.out.println("buying-provider@SwimClientConfiguration: connectionTimeout=" + responseTimeout);
    	
    	Request.Options options = new Request.Options(Integer.valueOf(connectionTimeout), Integer.valueOf(responseTimeout));
    	System.out.println("buying-provider@SwimClientConfiguration: options.connectTimeoutMillis()=" + options.connectTimeoutMillis());
    	System.out.println("buying-provider@SwimClientConfiguration: options.readTimeoutMillis()=" + options.readTimeoutMillis());
    	System.out.println("buying-provider@SwimClientConfiguration: options.toString()=" + options.toString());
    	
    	return options;
    }
}
