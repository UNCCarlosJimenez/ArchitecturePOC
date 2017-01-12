package com.unicomer.demo.buying.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.unicomer.demo.swim.domain.SwimVendor;
import com.unicomer.demo.swim.domain.SwimVendor.ResponseMessage;


//@Component("swimClient")
public class SwimClientImpl {//implements SwimClient {
	@Value("${swim.service.endpoint}")
	String serviceEndpoint;
	
	@Value("${swim.service.connect-timeout}")
	String connectTimeout;
	
	@Value("${swim.service.response-timeout}")
	String responseTimeout;
	
	private ClientHttpRequestFactory getClientHttpRequestFactory() {
		HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
		clientHttpRequestFactory.setConnectTimeout(Integer.valueOf(connectTimeout));
		clientHttpRequestFactory.setReadTimeout(Integer.valueOf(responseTimeout));		
		return clientHttpRequestFactory;
	}
		
//	@Override
	public List<SwimVendor> getVendors() {
		RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
		SwimVendor.ResponseMessage response = new ResponseMessage();
		try{
			response = restTemplate.getForObject(serviceEndpoint, SwimVendor.ResponseMessage.class);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response.getData();
	}
	
//	@Override
	public SwimVendor getVendor() {
		RestTemplate restTemplate = new RestTemplate(getClientHttpRequestFactory());
		SwimVendor.ResponseMessage response = new ResponseMessage();
		try{
			response = restTemplate.getForObject(serviceEndpoint, SwimVendor.ResponseMessage.class);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response.getData().get(0);
	}

}
