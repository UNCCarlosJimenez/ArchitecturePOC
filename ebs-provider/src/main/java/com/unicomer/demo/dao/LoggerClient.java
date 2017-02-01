/**
 * 
 */
package com.unicomer.demo.dao;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.unicomer.demo.common.entity.TransactionLogEndTrace;
import com.unicomer.demo.common.entity.TransactionLogInfoTrace;
import com.unicomer.demo.util.PropertiesLoader;

/**
 * @author carlosj_rodriguez
 *
 */
public class LoggerClient {
	private static PropertiesLoader properties = PropertiesLoader.getInstance();
	private String serviceEndpoint = properties.getProperty("logger.service.endpoint");
	private Integer connectTimeOut = Integer.valueOf(properties.getProperty("logger.service.connect-timeout"));
	private Integer responseTimeOut = Integer.valueOf(properties.getProperty("logger.service.response-timeout"));
	
	private WebResource getResource(String level) {
		ClientConfig config = new DefaultClientConfig();
		config.getProperties().put(ClientConfig.PROPERTY_CONNECT_TIMEOUT, connectTimeOut);
		config.getProperties().put(ClientConfig.PROPERTY_READ_TIMEOUT, responseTimeOut);

		Client client = Client.create(config);
		WebResource resource = client.resource(serviceEndpoint+level);
		return resource;
	}
	
	public void info (TransactionLogInfoTrace trace){
		try {
			ClientResponse response = getResource("info").accept(MediaType.TEXT_PLAIN).type(MediaType.APPLICATION_JSON)
					.header(HttpHeaders.CACHE_CONTROL, "false").post(ClientResponse.class, trace);
			
			System.out.println("[ebs-provider > LoggerClient] - HTTP error code : " + response.getStatus());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void error (TransactionLogInfoTrace trace){
		try {
			ClientResponse response = getResource("error").accept(MediaType.TEXT_PLAIN).type(MediaType.APPLICATION_JSON)
					.header(HttpHeaders.CACHE_CONTROL, "false").post(ClientResponse.class, trace);
			
			System.out.println("[ebs-provider > LoggerClient] - HTTP error code : " + response.getStatus());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void start (TransactionLogInfoTrace trace){
		try {
			ClientResponse response = getResource("start").accept(MediaType.TEXT_PLAIN).type(MediaType.APPLICATION_JSON)
					.header(HttpHeaders.CACHE_CONTROL, "false").post(ClientResponse.class, trace);
			
			System.out.println("[ebs-provider > LoggerClient] - HTTP error code : " + response.getStatus());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void end (TransactionLogEndTrace trace){
		try {
			ClientResponse response = getResource("end").accept(MediaType.TEXT_PLAIN).type(MediaType.APPLICATION_JSON)
					.header(HttpHeaders.CACHE_CONTROL, "false").post(ClientResponse.class, trace);
			
			System.out.println("[ebs-provider > LoggerClient] - HTTP error code : " + response.getStatus());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
