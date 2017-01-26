package com.unicomer.demo.buying.dao;

import com.unicomer.demo.buying.message.ebs.GetVendorRequest;
import com.unicomer.demo.buying.message.ebs.GetVendorResponse;
import com.unicomer.demo.buying.message.ebs.ObjectFactory;
import com.unicomer.demo.buying.message.ebs.RequestHeader;
import com.unicomer.demo.buying.util.PropertiesLoader;
import com.unicomer.demo.common.entity.UnicomerVendor;

public class EbsClient {
	private static final String APP_NAME = "buy-provider@EbsClient";
	private static PropertiesLoader properties = PropertiesLoader.getInstance();
	private String serviceEndpoint = properties.getProperty("ebs.service.endpoint");
	private Integer connectTimeOut = Integer.valueOf(properties.getProperty("ebs.service.connect-timeout"));
	private Integer responseTimeOut = Integer.valueOf(properties.getProperty("ebs.service.response-timeout"));
	private String applicationName = properties.getProperty("spring.application.name");
	
	
	public GetVendorResponse getVendor(String transactionId, UnicomerVendor vendor) {
		ObjectFactory factory = new ObjectFactory();
				
		GetVendorRequest request = factory.createGetVendorRequest();
		RequestHeader header = factory.createRequestHeader();
		
		GetVendorResponse response = factory.createGetVendorResponse();
		
		header.setApplication(applicationName);
		header.setTransaction(transactionId);		
		request.setRequestHeader(header);
		request.getVendor().setId(vendor.getVendorId());
		
		
				
		return response;
	}
	
//	
//	public AddVendorResponse newVendor(String transactionId, OmcInterfaceVendor vendor) {
//		AddVendorRequest request = new AddVendorRequest();
//		RequestHeader header = new RequestHeader();
//		
//		header.setApplication(applicationName);
//		header.setTransaction(transactionId);
//		
//		request.setRequestHeader(header);
//		request.setVendor(vendor);
//		
//		WebServiceTemplate wsTemplate = this.getWebServiceTemplate();
//		WebServiceMessageSender[] senders = wsTemplate.getMessageSenders();
//		for (WebServiceMessageSender sender : senders) {
//			HttpComponentsMessageSender httpSender = (HttpComponentsMessageSender) sender;
//			httpSender.setReadTimeout(Integer.parseInt(responseTimeout));
//			httpSender.setConnectionTimeout(Integer.parseInt(connectionTimeout));
//		}
//		
//		return (AddVendorResponse) wsTemplate.marshalSendAndReceive(serviceEndpoint, request);
//	}
//	
//	public UpdateVendorResponse updateVendor(String transactionId, OmcInterfaceVendor vendor) {
//		UpdateVendorRequest request = new UpdateVendorRequest();
//		RequestHeader header = new RequestHeader();
//		
//		header.setApplication(applicationName);
//		header.setTransaction(transactionId);
//		
//		request.setRequestHeader(header);
//		request.setVendor(vendor);
//		
//		WebServiceTemplate wsTemplate = this.getWebServiceTemplate();
//		WebServiceMessageSender[] senders = wsTemplate.getMessageSenders();
//		for (WebServiceMessageSender sender : senders) {
//			HttpComponentsMessageSender httpSender = (HttpComponentsMessageSender) sender;
//			httpSender.setReadTimeout(Integer.parseInt(responseTimeout));
//			httpSender.setConnectionTimeout(Integer.parseInt(connectionTimeout));
//		}
//		
//		return (UpdateVendorResponse) wsTemplate.marshalSendAndReceive(serviceEndpoint, request);
//	}
//	
//	public DeleteVendorResponse deleteVendor(String transactionId, String id) {
//		DeleteVendorRequest request = new DeleteVendorRequest();
//		RequestHeader header = new RequestHeader();
//		
//		header.setApplication(applicationName);
//		header.setTransaction(transactionId);
//		
//		request.setRequestHeader(header);
//		OmcInterfaceVendor vendor = new OmcInterfaceVendor();
//		vendor.setId(Integer.valueOf(id));
//		request.setVendor(vendor);
//		
//		WebServiceTemplate wsTemplate = this.getWebServiceTemplate();
//		WebServiceMessageSender[] senders = wsTemplate.getMessageSenders();
//		for (WebServiceMessageSender sender : senders) {
//			HttpComponentsMessageSender httpSender = (HttpComponentsMessageSender) sender;
//			httpSender.setReadTimeout(Integer.parseInt(responseTimeout));
//			httpSender.setConnectionTimeout(Integer.parseInt(connectionTimeout));
//		}
//		
//		return (DeleteVendorResponse) wsTemplate.marshalSendAndReceive(serviceEndpoint, request);
//	}
}
