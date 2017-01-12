package com.unicomer.demo.buying.dao;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.transport.WebServiceMessageSender;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;

import com.unicomer.demo.buying.message.ebs.GetVendorRequest;
import com.unicomer.demo.buying.message.ebs.GetVendorResponse;
import com.unicomer.demo.buying.message.ebs.RequestHeader;
import com.unicomer.demo.common.entity.Vendor;

@Component
public class EbsClient extends WebServiceGatewaySupport {
	@Value("${ebs.service.connect-timeout}")
	String connectionTimeout;
	
	@Value("${ebs.service.response-timeout}")
	String responseTimeout;
	
	@Value("${ebs.service.endpoint}")
	String serviceEndpoint;
	
	public GetVendorResponse getVendor(Vendor vendor) {
		GetVendorRequest request = new GetVendorRequest();
		RequestHeader header = new RequestHeader();

		header.setApplication("buying-provider");
		header.setTransaction(UUID.randomUUID().toString());

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
}
