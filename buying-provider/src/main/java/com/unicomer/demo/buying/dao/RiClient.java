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
import com.unicomer.demo.ri.domain.RiVendor;

public class RiClient {
//	private static final String APP_NAME = "buy-provider";
	private static PropertiesLoader properties = PropertiesLoader.getInstance();
	private String serviceEndpoint = properties.getProperty("ri.service.endpoint");
	private Integer connectTimeOut = Integer.valueOf(properties.getProperty("ri.service.connect-timeout"));
	private Integer responseTimeOut = Integer.valueOf(properties.getProperty("ri.service.response-timeout"));
	private static RiClient instance;
	
	public static RiClient getInstance (){
		if (instance == null)
			synchronized (RiClient.class){
				if (instance == null)
					instance = new RiClient();
			}
		return instance;
	}
	
	private Invocation.Builder getResource() {
		Client client = ClientBuilder.newClient();
		client.property(ClientProperties.CONNECT_TIMEOUT, connectTimeOut);
		client.property(ClientProperties.READ_TIMEOUT, responseTimeOut);
		WebTarget webTarget = client.target(serviceEndpoint).path("riVendorDomains");
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON_TYPE);
		
		return invocationBuilder;
	}
	
	private Invocation.Builder getResource(String id) {
		Client client = ClientBuilder.newClient();
		client.property(ClientProperties.CONNECT_TIMEOUT, connectTimeOut);
		client.property(ClientProperties.READ_TIMEOUT, responseTimeOut);
		WebTarget webTarget = client.target(serviceEndpoint).path("riVendors").path(id);
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON_TYPE);
		
		return invocationBuilder;
	}
	
	public com.unicomer.demo.ri.domain.RiVendor[] getVendors(){
		com.unicomer.demo.ri.domain.RiVendor[] result = null;
		try {
			Response response = getResource().get();
			
			if (response.getStatus() == Response.Status.OK.getStatusCode()) {
				result = response.readEntity(com.unicomer.demo.ri.domain.RiVendor[].class);
			}else{
				System.out.println("RiClient - getVendors() - HTTP error code : " + response.getStatus());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public com.unicomer.demo.ri.domain.RiVendor getVendor(String id){
		com.unicomer.demo.ri.domain.RiVendor result = new RiVendor();
		try {
			Response response = getResource(id).get();
			
			if (response.getStatus() == Response.Status.OK.getStatusCode()) {
				result = response.readEntity(com.unicomer.demo.ri.domain.RiVendor.class);
			}else{
				System.out.println("RiClient - getVendor() - HTTP error code : " + response.getStatus());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public com.unicomer.demo.ri.domain.RiVendor[] addVendor(RiVendor vendor){
		com.unicomer.demo.ri.domain.RiVendor[] result = null;
		try {
			Response response = getResource().post(Entity.entity(vendor, MediaType.APPLICATION_JSON));
			
			if (response.getStatus() == Response.Status.CREATED.getStatusCode()) {
				result = response.readEntity(com.unicomer.demo.ri.domain.RiVendor[].class);
			}else{
				System.out.println("RiClient - addVendor("+ vendor.getId() +") - HTTP error code : " + response.getStatus());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public com.unicomer.demo.ri.domain.RiVendor[] updateVendor(RiVendor vendor){
		com.unicomer.demo.ri.domain.RiVendor[] result = null;
		try {
			Response response = getResource(vendor.getId()).put(Entity.entity(vendor, MediaType.APPLICATION_JSON));
			
			if (response.getStatus() == Response.Status.CREATED.getStatusCode()) {
				result = response.readEntity(com.unicomer.demo.ri.domain.RiVendor[].class);
			}else{
				System.out.println("RiClient - updateVendor("+ vendor.getId() +") - HTTP error code : " + response.getStatus());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public void deleteVendor(String id){
		try {
			Response response = getResource(id).delete();
			
			System.out.println("RiClient - deleteVendor("+ id +") - HTTP error code : " + response.getStatus());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
