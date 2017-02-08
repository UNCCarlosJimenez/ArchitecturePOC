package com.unicomer.demo.buying.dao;

import com.unicomer.demo.buying.util.PropertiesLoader;
import com.unicomer.demo.common.entity.UnicomerVendor;
import com.unicomer.demo.ws.AddVendorRequestType0;
import com.unicomer.demo.ws.AddVendorResponse;
import com.unicomer.demo.ws.DeleteVendorRequest;
import com.unicomer.demo.ws.DeleteVendorResponse;
import com.unicomer.demo.ws.EbsProviderProxy;
import com.unicomer.demo.ws.GetVendorRequest;
import com.unicomer.demo.ws.GetVendorResponse;
import com.unicomer.demo.ws.OmcInterfaceVendor;
import com.unicomer.demo.ws.RequestHeader;
import com.unicomer.demo.ws.UpdateVendorRequest;
import com.unicomer.demo.ws.UpdateVendorResponse;

public class EbsClient {
	private static final String APP_NAME = "buy-provider@EbsClient";
	private static PropertiesLoader properties = PropertiesLoader.getInstance();
	private String serviceEndpoint = properties.getProperty("ebs.service.endpoint");
//	private Integer connectTimeOut = Integer.valueOf(properties.getProperty("ebs.service.connect-timeout"));
//	private Integer responseTimeOut = Integer.valueOf(properties.getProperty("ebs.service.response-timeout"));
	private String applicationName = properties.getProperty("spring.application.name");
	EbsProviderProxy proxy = new EbsProviderProxy(serviceEndpoint);
	private static EbsClient instance;
	
	public static EbsClient getInstance (){
		if (instance == null)
			synchronized (EbsClient.class){
				if (instance == null)
					instance = new EbsClient();
			}
		return instance;
	}
	
	public GetVendorResponse getVendor(String transactionId, UnicomerVendor vendor) {
		GetVendorRequest request = new GetVendorRequest();
		RequestHeader header = new RequestHeader();
		GetVendorResponse response = new GetVendorResponse();
		
		header.setApplication(applicationName);
		header.setTransaction(transactionId);		
		request.setRequestHeader(header);
		request.getVendor().setId(vendor.getVendorId());
		
		try{
			response = proxy.getVendor(request);
		}catch (Exception e) {
			System.err.println(APP_NAME + ": " + e.getMessage());
			e.printStackTrace();
		}
		
		return response;
	}
	
	
	public AddVendorResponse newVendor(String transactionId, OmcInterfaceVendor vendor) {
		AddVendorRequestType0 request = new AddVendorRequestType0();
		AddVendorResponse response = new AddVendorResponse();
		RequestHeader header = new RequestHeader();
		
		header.setApplication(applicationName);
		header.setTransaction(transactionId);
		
		request.setRequestHeader(header);
		request.setVendor(vendor);
		
		try{
			response = proxy.addVendor(request);
		}catch (Exception e) {
			System.err.println(APP_NAME + ": " + e.getMessage());
			e.printStackTrace();
		}
		
		return response;
	}
	
	public UpdateVendorResponse updateVendor(String transactionId, OmcInterfaceVendor vendor) {
		UpdateVendorRequest request = new UpdateVendorRequest();
		UpdateVendorResponse response = new UpdateVendorResponse();
		RequestHeader header = new RequestHeader();
		
		header.setApplication(applicationName);
		header.setTransaction(transactionId);
		
		request.setRequestHeader(header);
		request.setVendor(vendor);
		
		try{
			response = proxy.updateVendor(request);
		}catch (Exception e) {
			System.err.println(APP_NAME + ": " + e.getMessage());
			e.printStackTrace();
		}
		
		return response;
	}
	
	public DeleteVendorResponse deleteVendor(String transactionId, String id) {
		DeleteVendorRequest request = new DeleteVendorRequest();
		DeleteVendorResponse response = null;
		RequestHeader header = new RequestHeader();
		
		header.setApplication(applicationName);
		header.setTransaction(transactionId);
		
		request.setRequestHeader(header);
		OmcInterfaceVendor vendor = new OmcInterfaceVendor();
		vendor.setId(Integer.valueOf(id));
		request.setVendor(vendor);
		
		try{
			response = proxy.deleteVendor(request);
		}catch (Exception e) {
			System.err.println(APP_NAME + ": " + e.getMessage());
			e.printStackTrace();
		}
		
		return response;
	}
}
