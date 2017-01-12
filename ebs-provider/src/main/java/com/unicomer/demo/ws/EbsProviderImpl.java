package com.unicomer.demo.ws;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

import com.unicomer.demo.message.GetVendorRequest;
import com.unicomer.demo.message.GetVendorResponse;
import com.unicomer.demo.message.ObjectFactory;
import com.unicomer.demo.message.OmcInterfaceVendor;
import com.unicomer.demo.message.ResponseHeader;
import com.unicomer.demo.repository.VendorRepository;

@WebService(name = "EbsProvider", targetNamespace = "http://ws.demo.unicomer.com/")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({ com.unicomer.demo.message.ObjectFactory.class })
@Stateless
public class EbsProviderImpl implements EbsProvider {
	
	private VendorRepository vendorRepository = new VendorRepository();
	
	@WebMethod
	public GetVendorResponse getVendor(
			@WebParam(name = "GetVendorRequest", targetNamespace = "http://ws.demo.unicomer.com/", partName = "payload") GetVendorRequest payload) {
		ObjectFactory factory = new ObjectFactory();
		GetVendorResponse response = factory.createGetVendorResponse();
		ResponseHeader responseHeader = factory.createResponseHeader();
		
		try {
			responseHeader.setResponseCode(0);
			responseHeader.setResponseDescription("Transaccion exitosa");
			responseHeader.setServiceCode(0);
			responseHeader.setServiceDescription("Transacion exitosa");
			System.out.println("payload.getVendor().getId()=" + payload.getVendor().getId().toString());
						
			if (payload.getVendor().getId().isEmpty()) {
				System.out.println("findAll");
				List<OmcInterfaceVendor> vendorList = new ArrayList<OmcInterfaceVendor>();
				vendorList = vendorRepository.findAll();
				response.getVendor().addAll(vendorList);
			} else {
				System.out.println("findOne");
				OmcInterfaceVendor vendorResponse =  vendorRepository.findOne(Integer.valueOf(payload.getVendor().getId()));
				response.getVendor().add(vendorResponse);
			}
		} catch (Exception e) {
			responseHeader.setResponseCode(500);
			responseHeader.setResponseDescription("Ha ocurrido un problema");
			responseHeader.setServiceCode(500);
			responseHeader.setServiceDescription(e.getMessage());
			e.printStackTrace();
		}
		
		if(payload.getRequestHeader().getTransaction().isEmpty()){
			responseHeader.setTransaction(UUID.randomUUID().toString());
		}else{
			responseHeader.setTransaction(payload.getRequestHeader().getTransaction());
		}
		
		response.setResponseHeader(responseHeader);
		return response;
	}

}
