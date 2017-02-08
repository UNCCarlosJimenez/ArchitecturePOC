/**
 * 
 */
package com.unicomer.demo.buying.dao;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;

import org.glassfish.jersey.client.ClientProperties;

import com.unicomer.demo.buying.util.PropertiesLoader;
import com.unicomer.demo.common.entity.TransactionLogEndTrace;
import com.unicomer.demo.common.entity.TransactionLogInfoTrace;

/**
 * @author carlosj_rodriguez
 *
 */
public class LoggerClient {
	private static final String APP_NAME = "buy-provider";
	private static PropertiesLoader properties = PropertiesLoader.getInstance();
	private String serviceEndpoint = properties.getProperty("logger.service.endpoint");
	private Integer connectTimeOut = Integer.valueOf(properties.getProperty("logger.service.connect-timeout"));
	private Integer responseTimeOut = Integer.valueOf(properties.getProperty("logger.service.response-timeout"));
	private String accept = properties.getProperty("logger.service.accept");
	private String contentType = properties.getProperty("logger.service.content-type");
	private static LoggerClient instance;
	
	public static LoggerClient getInstance(){
		if (instance == null)
			synchronized (LoggerClient.class){
				if (instance == null)
					instance = new LoggerClient();
			}
		return instance;
	}
	
	private Invocation.Builder getResource(String level) {
		long startTime = System.currentTimeMillis();
		Client client = ClientBuilder.newClient();
		client.property(ClientProperties.CONNECT_TIMEOUT, connectTimeOut);
		client.property(ClientProperties.READ_TIMEOUT, responseTimeOut);
		WebTarget webTarget = client.target(serviceEndpoint).path(level);
		Invocation.Builder invocationBuilder =  webTarget.request(accept);
		
		long duration = System.currentTimeMillis() - startTime;
		System.out.println("Conectando a Logstash en " + duration + " milisegundos");
		return invocationBuilder;
	}
	
	public void info (TransactionLogInfoTrace trace){
		try {
			trace.setEventSource(Thread.currentThread().getStackTrace()[3].toString());
			
			int status = getResource("info").post(Entity.entity(trace, contentType)).getStatus();
			System.out.println("[" + APP_NAME + " > LoggerClient] - HTTP error code : " + status);
		} catch (Exception e) {
			System.err.println("[" + APP_NAME + " > LoggerClient] - Error: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void info (String message){
		try {
			getResource("info").post(Entity.entity(message, contentType)).getStatus();
		} catch (Exception e) {
			System.err.println("[" + APP_NAME + " > LoggerClient] - Error: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void error (TransactionLogInfoTrace trace){
		try {
			trace.setEventSource(Thread.currentThread().getStackTrace()[3].toString());
			
			int status = getResource("error").post(Entity.entity(trace, contentType)).getStatus();			
			System.out.println("[" + APP_NAME + " > LoggerClient] - HTTP error code : " + status);
		} catch (Exception e) {
			System.err.println("[" + APP_NAME + " > LoggerClient] - Error: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void start (TransactionLogInfoTrace trace){
		try {
			trace.setEventSource(Thread.currentThread().getStackTrace()[3].toString());
			
			int status = getResource("start").post(Entity.entity(trace, contentType)).getStatus();			
			System.out.println("[" + APP_NAME + " > LoggerClient] - HTTP error code : " + status);
		} catch (Exception e) {
			System.err.println("[" + APP_NAME + " > LoggerClient] - Error: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void end (TransactionLogEndTrace trace){
		try {
			trace.setEventSource(Thread.currentThread().getStackTrace()[3].toString());
			
			int status = getResource("end").post(Entity.entity(trace, contentType)).getStatus();
			
			System.out.println("[" + APP_NAME + " > LoggerClient] - HTTP error code : " + status);
		} catch (Exception e) {
			System.err.println("[" + APP_NAME + " > LoggerClient] - Error: " + e.getMessage());
			e.printStackTrace();
		}
	}		
}
