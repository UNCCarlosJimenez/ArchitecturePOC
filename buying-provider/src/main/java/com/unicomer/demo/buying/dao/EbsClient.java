package com.unicomer.demo.buying.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.transport.WebServiceMessageSender;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;

import com.unicomer.demo.buying.message.ebs.AddVendorRequest;
import com.unicomer.demo.buying.message.ebs.AddVendorResponse;
import com.unicomer.demo.buying.message.ebs.DeleteVendorRequest;
import com.unicomer.demo.buying.message.ebs.DeleteVendorResponse;
import com.unicomer.demo.buying.message.ebs.GetVendorRequest;
import com.unicomer.demo.buying.message.ebs.GetVendorResponse;
import com.unicomer.demo.buying.message.ebs.OmcInterfaceVendor;
import com.unicomer.demo.buying.message.ebs.RequestHeader;
import com.unicomer.demo.buying.message.ebs.UpdateVendorRequest;
import com.unicomer.demo.buying.message.ebs.UpdateVendorResponse;
import com.unicomer.demo.common.entity.UnicomerVendor;

@Component
public class EbsClient extends WebServiceGatewaySupport {
	@Value("${ebs.service.connect-timeout}")
	String connectionTimeout;
	
	@Value("${ebs.service.response-timeout}")
	String responseTimeout;
	
	@Value("${ebs.service.endpoint}")
	String serviceEndpoint;
	
	@Value("${spring.application.name}") 
	String applicationName;
	
	public GetVendorResponse getVendor(String transactionId, UnicomerVendor vendor) {
		GetVendorRequest request = new GetVendorRequest();
		RequestHeader header = new RequestHeader();

		header.setApplication(applicationName);
		header.setTransaction(transactionId);
		
		request.setRequestHeader(header);
		request.getVendor().setId(vendor.getVendorId());

		WebServiceTemplate wsTemplate = this.getWebServiceTemplate();
		WebServiceMessageSender[] senders = wsTemplate.getMessageSenders();
		for (WebServiceMessageSender sender : senders) {
			HttpComponentsMessageSender httpSender = (HttpComponentsMessageSender) sender;
			httpSender.setReadTimeout(Integer.parseInt(responseTimeout));
			httpSender.setConnectionTimeout(Integer.parseInt(connectionTimeout));
		}

		return (GetVendorResponse) wsTemplate.marshalSendAndReceive(serviceEndpoint, request);
	}
	
	
	public AddVendorResponse newVendor(String transactionId, OmcInterfaceVendor vendor) {
		AddVendorRequest request = new AddVendorRequest();
		RequestHeader header = new RequestHeader();
		
		header.setApplication(applicationName);
		header.setTransaction(transactionId);
		
		request.setRequestHeader(header);
		request.setVendor(vendor);
		
		WebServiceTemplate wsTemplate = this.getWebServiceTemplate();
		WebServiceMessageSender[] senders = wsTemplate.getMessageSenders();
		for (WebServiceMessageSender sender : senders) {
			HttpComponentsMessageSender httpSender = (HttpComponentsMessageSender) sender;
			httpSender.setReadTimeout(Integer.parseInt(responseTimeout));
			httpSender.setConnectionTimeout(Integer.parseInt(connectionTimeout));
		}
		
		return (AddVendorResponse) wsTemplate.marshalSendAndReceive(serviceEndpoint, request);
	}
	
	public UpdateVendorResponse updateVendor(String transactionId, OmcInterfaceVendor vendor) {
		UpdateVendorRequest request = new UpdateVendorRequest();
		RequestHeader header = new RequestHeader();
		
		header.setApplication(applicationName);
		header.setTransaction(transactionId);
		
		request.setRequestHeader(header);
		request.setVendor(vendor);
		
		WebServiceTemplate wsTemplate = this.getWebServiceTemplate();
		WebServiceMessageSender[] senders = wsTemplate.getMessageSenders();
		for (WebServiceMessageSender sender : senders) {
			HttpComponentsMessageSender httpSender = (HttpComponentsMessageSender) sender;
			httpSender.setReadTimeout(Integer.parseInt(responseTimeout));
			httpSender.setConnectionTimeout(Integer.parseInt(connectionTimeout));
		}
		
		return (UpdateVendorResponse) wsTemplate.marshalSendAndReceive(serviceEndpoint, request);
	}
	
	public DeleteVendorResponse deleteVendor(String transactionId, String id) {
		DeleteVendorRequest request = new DeleteVendorRequest();
		RequestHeader header = new RequestHeader();
		
		header.setApplication(applicationName);
		header.setTransaction(transactionId);
		
		request.setRequestHeader(header);
		OmcInterfaceVendor vendor = new OmcInterfaceVendor();
		vendor.setId(Integer.valueOf(id));
		request.setVendor(vendor);
		
		WebServiceTemplate wsTemplate = this.getWebServiceTemplate();
		WebServiceMessageSender[] senders = wsTemplate.getMessageSenders();
		for (WebServiceMessageSender sender : senders) {
			HttpComponentsMessageSender httpSender = (HttpComponentsMessageSender) sender;
			httpSender.setReadTimeout(Integer.parseInt(responseTimeout));
			httpSender.setConnectionTimeout(Integer.parseInt(connectionTimeout));
		}
		
		return (DeleteVendorResponse) wsTemplate.marshalSendAndReceive(serviceEndpoint, request);
	}
}
