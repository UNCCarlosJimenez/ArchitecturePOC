package com.unicomer.demo.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.unicomer.demo.common.entity.UnicomerVendor;
import com.unicomer.demo.common.entity.UnicomerVendor.RequestVendorMessage;
import com.unicomer.demo.common.entity.UnicomerVendor.ResponseVendorMessage;
import com.unicomer.demo.util.PropertiesLoader;

public class BuyingProviderClient {
	private static PropertiesLoader properties = PropertiesLoader.getInstance();
	private String serviceEndpoint = properties.getProperty("buy.service.endpoint");
	private Integer connectTimeOut = Integer.valueOf(properties.getProperty("buy.service.connect-timeout"));
	private Integer responseTimeOut = Integer.valueOf(properties.getProperty("buy.service.response-timeout"));
	
	private WebResource getResource() {
		ClientConfig config = new DefaultClientConfig();
		config.getProperties().put(ClientConfig.PROPERTY_CONNECT_TIMEOUT, connectTimeOut);
		config.getProperties().put(ClientConfig.PROPERTY_READ_TIMEOUT, responseTimeOut);

		Client client = Client.create(config);
		WebResource resource = client.resource(serviceEndpoint);
		return resource;
	}
	
	private RequestVendorMessage getRequestMessage(){
		String localTransactionId=UUID.randomUUID().toString();
		RequestVendorMessage request = new RequestVendorMessage();
		
		request.setApplication("vendor-management-web");
		request.setDate(Calendar.getInstance().getTime());
		request.setPosId("");
		request.setStore("");
		request.setTransaction(localTransactionId);
		
		return request;
	}

	public List<UnicomerVendor> findAll() {
		List<UnicomerVendor> resultList = new ArrayList<UnicomerVendor>();
		ResponseVendorMessage result = new ResponseVendorMessage();
		try {
			ClientResponse response = getResource().accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON)
					.get(ClientResponse.class);

			if (response.getStatus() == ClientResponse.Status.OK.getStatusCode()) {
				result = response.getEntity(ResponseVendorMessage.class);
				resultList = result.getData();
			}else{
				System.out.println("HTTP error code : " + response.getStatus());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultList;
	}
	
	public void create(UnicomerVendor vendor) {
		RequestVendorMessage request = getRequestMessage();
		request.setData(vendor);
		
		try {
			ClientResponse response = getResource().accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON)
					.header(HttpHeaders.CACHE_CONTROL, "false").post(ClientResponse.class, request);

			if (response.getStatus() > 200) {
				System.out.println("HTTP error code : " + response.getStatus());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void edit(UnicomerVendor vendor) {
		RequestVendorMessage request = getRequestMessage();
		request.setData(vendor);
		
		try {
			ClientResponse response = getResource().accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON)
					.header(HttpHeaders.CACHE_CONTROL, "false").put(ClientResponse.class, request);
			
			if (response.getStatus() > 200) {
				System.out.println("HTTP error code : " + response.getStatus());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void remove(UnicomerVendor vendor) {
		RequestVendorMessage request = getRequestMessage();
		request.setData(vendor);
		
		try {
			ClientResponse response = getResource().accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON)
					.header(HttpHeaders.CACHE_CONTROL, "false").delete(ClientResponse.class, request);

			if (response.getStatus() > 200) {
				System.out.println("HTTP error code : " + response.getStatus());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public UnicomerVendor find(String id) {
		UnicomerVendor result = new UnicomerVendor();
		try {
			ClientResponse response = getResource().path(id).accept(MediaType.APPLICATION_JSON)
					.type(MediaType.APPLICATION_JSON).header(HttpHeaders.CACHE_CONTROL, "false")
					.get(ClientResponse.class);
			if (response.getStatus() != 200) {
				System.out.println("HTTP error code : " + response.getStatus());
			}
			
			ResponseVendorMessage responseMessage = response.getEntity(ResponseVendorMessage.class);
			if(responseMessage.getData().size()>0){
				result = responseMessage.getData().get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<UnicomerVendor> findRange(int[] range) {
		return new ArrayList<UnicomerVendor>();
	}

	public int count() {
		int result = 0;
		try {
			ClientResponse response = getResource().accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON)
					.header(HttpHeaders.CACHE_CONTROL, "false").get(ClientResponse.class);

			if (response.getStatus() != 200) {
				System.out.println("HTTP error code : " + response.getStatus());
			}

			ResponseVendorMessage responseMessage = response.getEntity(ResponseVendorMessage.class);
			result = responseMessage.getData().size();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
