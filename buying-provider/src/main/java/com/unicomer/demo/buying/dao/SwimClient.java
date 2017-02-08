package com.unicomer.demo.buying.dao;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientProperties;

import com.unicomer.demo.buying.util.PropertiesLoader;
import com.unicomer.demo.swim.domain.SwimVendor;
import com.unicomer.demo.swim.domain.SwimVendor.ResponseMessage;

public class SwimClient {
	private static final String APP_NAME = "buy-provider@SwimClient";
	private static PropertiesLoader properties = PropertiesLoader.getInstance();
	private String serviceEndpoint = properties.getProperty("swim.service.endpoint");
	private Integer connectTimeOut = Integer.valueOf(properties.getProperty("swim.service.connect-timeout"));
	private Integer responseTimeOut = Integer.valueOf(properties.getProperty("swim.service.response-timeout"));
	private static LoggerClient logger = LoggerClient.getInstance();
	private static Invocation.Builder builder;
	private static SwimClient instance;
	
	public static SwimClient getInstance (){
		if (instance == null)
			synchronized (SwimClient.class){
				if (instance == null)
					instance = new SwimClient();
			}
		return instance;
	}
	
	/**
	 * 
	 */
	public SwimClient() {
		connect();
	}
	
	private void connect() {
		long startTime = System.currentTimeMillis();
		Client client = ClientBuilder.newClient();
		client.property(ClientProperties.CONNECT_TIMEOUT, connectTimeOut);
		client.property(ClientProperties.READ_TIMEOUT, responseTimeOut);
		WebTarget webTarget = client.target(serviceEndpoint).path("vendors");
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON_TYPE);
		
		builder = invocationBuilder;
		
		long duration = System.currentTimeMillis() - startTime;
		logger.info("Conectando a SWIM en " + duration + " milisegundos");
	}
	
	private Invocation.Builder getResource(){
		return builder;
	}
	
	private Invocation.Builder getResource(String id) {
		Client client = ClientBuilder.newClient();
		client.property(ClientProperties.CONNECT_TIMEOUT, connectTimeOut);
		client.property(ClientProperties.READ_TIMEOUT, responseTimeOut);
		WebTarget webTarget = client.target(serviceEndpoint).path("vendors").path(id);
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON_TYPE);
		
		return invocationBuilder;
	}
	
	public SwimVendor.ResponseMessage getVendors(){
		SwimVendor.ResponseMessage result = new ResponseMessage();
		try {
			Response response = getResource().get();
			
			if (response.getStatus() == Response.Status.OK.getStatusCode()) {
				result = response.readEntity(SwimVendor.ResponseMessage.class);
			}else{
				System.out.println(APP_NAME + " - getVendors() - HTTP error code : " + response.getStatus());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public SwimVendor.ResponseMessage getVendor(String id, String transactionId, long startTime){
		logger.info(transactionId + " - Inicio de SwimClient.getVendor en " + (System.currentTimeMillis() - startTime) + " milisegundos");
		SwimVendor.ResponseMessage result = new ResponseMessage();
		try {
			logger.info(transactionId + " - Inicio de consumo de servicio de Swim en " + (System.currentTimeMillis() - startTime) + " milisegundos");
			Response response = getResource(id).get();
			logger.info(transactionId + " - Fin de consumo de servicio de Swim en " + (System.currentTimeMillis() - startTime) + " milisegundos");
			
			if (response.getStatus() == Response.Status.OK.getStatusCode()) {
				result = response.readEntity(SwimVendor.ResponseMessage.class);
			}else{
				System.out.println(APP_NAME + " - getVendor("+id+") - HTTP error code : " + response.getStatus());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		logger.info(transactionId + " - Fin de SwimClient.getVendor en " + (System.currentTimeMillis() - startTime) + " milisegundos");
		return result;
	}
	
	public SwimVendor.ResponseMessage addVendor(SwimVendor.RequestMessage request){
		SwimVendor.ResponseMessage result = new ResponseMessage();
		try {
			Response response = getResource().post(Entity.entity(request, MediaType.APPLICATION_JSON));
			
			if (response.getStatus() == Response.Status.CREATED.getStatusCode()) {
				result = response.readEntity(SwimVendor.ResponseMessage.class);
			}else{
				System.out.println(APP_NAME + " - addVendor("+request.getData().getVendorId()+") - HTTP error code : " + response.getStatus());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public SwimVendor.ResponseMessage updateVendor(SwimVendor.RequestMessage request){
		SwimVendor.ResponseMessage result = new ResponseMessage();
		try {
			Response response = getResource(request.getData().getVendorId()).put(Entity.entity(request, MediaType.APPLICATION_JSON));
			
			if (response.getStatus() == Response.Status.CREATED.getStatusCode()) {
				result = response.readEntity(SwimVendor.ResponseMessage.class);
			}else{
				System.out.println(APP_NAME + " - updateVendor("+request.getData().getVendorId()+") - HTTP error code : " + response.getStatus());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public SwimVendor.ResponseMessage deleteVendor(String id){
		SwimVendor.ResponseMessage result = new ResponseMessage();
		try {
			Response response = getResource(id).delete();
			
			if (response.getStatus() == Response.Status.NO_CONTENT.getStatusCode()) {
				result = response.readEntity(SwimVendor.ResponseMessage.class);
			}else{
				System.out.println(APP_NAME + " - deleteVendor("+id+") - HTTP error code : " + response.getStatus());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
