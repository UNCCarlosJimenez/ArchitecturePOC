package com.unicomer.demo.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.unicomer.demo.common.entity.Vendor;
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

	public List<Vendor> findAll() {
		List<Vendor> resultList = new ArrayList<Vendor>();
		try {
			ClientResponse response = getResource().accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON)
					.get(ClientResponse.class);

			if (response.getStatus() == ClientResponse.Status.OK.getStatusCode()) {
				Vendor[] vendorList = response.getEntity(Vendor[].class);
				resultList = Arrays.asList(vendorList);
			}else{
				System.out.println("HTTP error code : " + response.getStatus());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultList;
	}

	public void create(Vendor vendor) {
		try {
			ClientResponse response = getResource().accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON)
					.header(HttpHeaders.CACHE_CONTROL, "false").post(ClientResponse.class, vendor);

			if (response.getStatus() > 200) {
				System.out.println("HTTP error code : " + response.getStatus());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void edit(Vendor vendor) {
		try {
			ClientResponse response = getResource().accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON)
					.header(HttpHeaders.CACHE_CONTROL, "false").put(ClientResponse.class, vendor);

			if (response.getStatus() > 200) {
				System.out.println("HTTP error code : " + response.getStatus());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void remove(Vendor vendor) {
		try {
			ClientResponse response = getResource().accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON)
					.header(HttpHeaders.CACHE_CONTROL, "false").delete(ClientResponse.class, vendor);

			if (response.getStatus() > 200) {
				System.out.println("HTTP error code : " + response.getStatus());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Vendor find(Integer id) {
		Vendor result = new Vendor();
		try {
			ClientResponse response = getResource().path(id.toString()).accept(MediaType.APPLICATION_JSON)
					.type(MediaType.APPLICATION_JSON).header(HttpHeaders.CACHE_CONTROL, "false")
					.get(ClientResponse.class);
			if (response.getStatus() != 200) {
				System.out.println("HTTP error code : " + response.getStatus());
			}
			
			result = response.getEntity(Vendor.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<Vendor> findRange(int[] range) {
		return new ArrayList<Vendor>();
	}

	public int count() {
		int result = 0;
		try {
			ClientResponse response = getResource().accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON)
					.header(HttpHeaders.CACHE_CONTROL, "false").get(ClientResponse.class);

			if (response.getStatus() != 200) {
				System.out.println("HTTP error code : " + response.getStatus());
			}

			Vendor[] vendorList = response.getEntity(Vendor[].class);
			result = vendorList.length;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
