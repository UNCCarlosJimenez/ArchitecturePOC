package com.unicomer.demo.ws;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import com.unicomer.demo.common.entity.TransactionLogEndTrace;
import com.unicomer.demo.common.entity.TransactionLogInfoTrace;
import com.unicomer.demo.dao.LoggerClient;
import com.unicomer.demo.domain.OmcInterfaceVendorDomain;
import com.unicomer.demo.message.AddVendorRequest;
import com.unicomer.demo.message.AddVendorResponse;
import com.unicomer.demo.message.DeleteVendorRequest;
import com.unicomer.demo.message.DeleteVendorResponse;
import com.unicomer.demo.message.GetVendorRequest;
import com.unicomer.demo.message.GetVendorResponse;
import com.unicomer.demo.message.ObjectFactory;
import com.unicomer.demo.message.OmcInterfaceVendor;
import com.unicomer.demo.message.ResponseHeader;
import com.unicomer.demo.message.UpdateVendorRequest;
import com.unicomer.demo.message.UpdateVendorResponse;
import com.unicomer.demo.repository.VendorRepository;

@WebService(name = "EbsProvider", targetNamespace = "http://ws.demo.unicomer.com/")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({ com.unicomer.demo.message.ObjectFactory.class })
@Stateless
public class EbsProviderImpl implements EbsProvider {
	@Resource
	WebServiceContext wsContext;
	private LoggerClient logger = new LoggerClient();
	private VendorRepository vendorRepository = new VendorRepository();
	
	@WebMethod
	public GetVendorResponse getVendor(
			@WebParam(name = "GetVendorRequest", targetNamespace = "http://ws.demo.unicomer.com/", partName = "payload") GetVendorRequest payload) {
		MessageContext mc = wsContext.getMessageContext();
	    HttpServletRequest servletRequest = (HttpServletRequest)mc.get(MessageContext.SERVLET_REQUEST); 
		
		ObjectFactory factory = new ObjectFactory();
		GetVendorResponse response = factory.createGetVendorResponse();
		ResponseHeader responseHeader = factory.createResponseHeader();
		String localTransactionId=UUID.randomUUID().toString();
		
		long startTime = System.currentTimeMillis();		
		logger.start(new TransactionLogInfoTrace(
				this.getClass().getCanonicalName(), 
				payload.toString(),
				payload.getVendor().getId(),
				servletRequest.getRemoteAddr(), 
				"getVendor", 
				"Inicio de consulta de proveedores", 
				payload.getRequestHeader().getTransaction(), 
				localTransactionId,
				"",
				"v1", 
				servletRequest.getContextPath()
			)
		);
		
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
			
			logger.error(new TransactionLogInfoTrace(
					this.getClass().getCanonicalName(), 
					e.getCause().getMessage(),
					String.valueOf(payload.getVendor().getId()),
					servletRequest.getRemoteAddr(), 
					"getVendor", 
					e.getMessage(), 
					payload.getRequestHeader().getTransaction(), 
					localTransactionId,
					"",
					"v1", 
					servletRequest.getContextPath()
				)
			);
			
			e.printStackTrace();
		}
		
		responseHeader.setTransaction(localTransactionId);
		response.setResponseHeader(responseHeader);
		
		long duration = System.currentTimeMillis() - startTime;
		logger.end(new TransactionLogEndTrace(
				this.getClass().getCanonicalName(), 
				response.toString(),
				payload.getVendor().getId(),
				servletRequest.getRemoteAddr(), 
				"getVendor", 
				"Fin de consulta de proveedores", 
				payload.getRequestHeader().getTransaction(), 
				localTransactionId,
				"",
				"v1", 
				servletRequest.getContextPath(),
				duration,
				responseHeader.getResponseCode()
			)
		);
		
		return response;
	}
	
	
	@WebMethod
	public AddVendorResponse addVendor(
			@WebParam(name = "AddVendorRequest", targetNamespace = "http://ws.demo.unicomer.com/", partName = "payload") AddVendorRequest payload) {
		MessageContext mc = wsContext.getMessageContext();
	    HttpServletRequest servletRequest = (HttpServletRequest)mc.get(MessageContext.SERVLET_REQUEST); 
		
		ObjectFactory factory = new ObjectFactory();
		AddVendorResponse response = factory.createAddVendorResponse();
		ResponseHeader responseHeader = factory.createResponseHeader();
		String localTransactionId=UUID.randomUUID().toString();
		
		long startTime = System.currentTimeMillis();		
		logger.start(new TransactionLogInfoTrace(
				this.getClass().getCanonicalName(), 
				payload.toString(),
				String.valueOf(payload.getVendor().getId()),
				servletRequest.getRemoteAddr(), 
				"addVendor", 
				"Inicio de creacion de proveedores", 
				payload.getRequestHeader().getTransaction(), 
				localTransactionId,
				"",
				"v1", 
				servletRequest.getContextPath()
			)
		);
		
		try {
			responseHeader.setResponseCode(0);
			responseHeader.setResponseDescription("Transaccion exitosa");
			responseHeader.setServiceCode(0);
			responseHeader.setServiceDescription("Transacion exitosa");
			
			OmcInterfaceVendor vendorResponse =  vendorRepository.save(entityToDomain(payload.getVendor()));
			response.setVendor(vendorResponse);
		} catch (Exception e) {
			responseHeader.setResponseCode(500);
			responseHeader.setResponseDescription("Ha ocurrido un problema");
			responseHeader.setServiceCode(500);
			responseHeader.setServiceDescription(e.getMessage());
			
			logger.error(new TransactionLogInfoTrace(
					this.getClass().getCanonicalName(), 
					e.getCause().getMessage(),
					String.valueOf(payload.getVendor().getId()),
					servletRequest.getRemoteAddr(), 
					"addVendor", 
					e.getMessage(), 
					payload.getRequestHeader().getTransaction(), 
					localTransactionId,
					"",
					"v1", 
					servletRequest.getContextPath()
				)
			);
			
			e.printStackTrace();
		}
		
		responseHeader.setTransaction(localTransactionId);
		response.setResponseHeader(responseHeader);
		
		long duration = System.currentTimeMillis() - startTime;
		logger.end(new TransactionLogEndTrace(
				this.getClass().getCanonicalName(), 
				response.toString(),
				String.valueOf(payload.getVendor().getId()),
				servletRequest.getRemoteAddr(), 
				"addVendor", 
				"Fin de creacion de proveedores", 
				payload.getRequestHeader().getTransaction(), 
				localTransactionId,
				"",
				"v1", 
				servletRequest.getContextPath(),
				duration,
				responseHeader.getResponseCode()
			)
		);
		
		return response;
	}
	
	
	@WebMethod
	public UpdateVendorResponse updateVendor(
			@WebParam(name = "UpdateVendorRequest", targetNamespace = "http://ws.demo.unicomer.com/", partName = "payload") UpdateVendorRequest payload) {
		MessageContext mc = wsContext.getMessageContext();
	    HttpServletRequest servletRequest = (HttpServletRequest)mc.get(MessageContext.SERVLET_REQUEST); 
		
		ObjectFactory factory = new ObjectFactory();
		UpdateVendorResponse response = factory.createUpdateVendorResponse();
		ResponseHeader responseHeader = factory.createResponseHeader();
		String localTransactionId=UUID.randomUUID().toString();
		
		long startTime = System.currentTimeMillis();		
		logger.start(new TransactionLogInfoTrace(
				this.getClass().getCanonicalName(), 
				payload.toString(),
				String.valueOf(payload.getVendor().getId()),
				servletRequest.getRemoteAddr(), 
				"updateVendor", 
				"Inicio de actualizacion de proveedores", 
				payload.getRequestHeader().getTransaction(), 
				localTransactionId,
				"",
				"v1", 
				servletRequest.getContextPath()
			)
		);
		
		try {
			responseHeader.setResponseCode(0);
			responseHeader.setResponseDescription("Transaccion exitosa");
			responseHeader.setServiceCode(0);
			responseHeader.setServiceDescription("Transacion exitosa");
			
			OmcInterfaceVendor vendorResponse =  vendorRepository.save(entityToDomain(payload.getVendor()));
			response.setVendor(vendorResponse);
		} catch (Exception e) {
			responseHeader.setResponseCode(500);
			responseHeader.setResponseDescription("Ha ocurrido un problema");
			responseHeader.setServiceCode(500);
			responseHeader.setServiceDescription(e.getMessage());
			
			logger.error(new TransactionLogInfoTrace(
					this.getClass().getCanonicalName(), 
					e.getCause().getMessage(),
					String.valueOf(payload.getVendor().getId()),
					servletRequest.getRemoteAddr(), 
					"updateVendor", 
					e.getMessage(), 
					payload.getRequestHeader().getTransaction(), 
					localTransactionId,
					"",
					"v1", 
					servletRequest.getContextPath()
				)
			);
			
			e.printStackTrace();
		}
		
		responseHeader.setTransaction(localTransactionId);
		response.setResponseHeader(responseHeader);
		
		long duration = System.currentTimeMillis() - startTime;
		logger.end(new TransactionLogEndTrace(
				this.getClass().getCanonicalName(), 
				response.toString(),
				String.valueOf(payload.getVendor().getId()),
				servletRequest.getRemoteAddr(), 
				"updateVendor", 
				"Fin de actualizacion de proveedores", 
				payload.getRequestHeader().getTransaction(), 
				localTransactionId,
				"",
				"v1", 
				servletRequest.getContextPath(),
				duration,
				responseHeader.getResponseCode()
			)
		);
		
		return response;
	}
	
	
	
	@WebMethod
	public DeleteVendorResponse deleteVendor(
			@WebParam(name = "DeleteVendorRequest", targetNamespace = "http://ws.demo.unicomer.com/", partName = "payload") DeleteVendorRequest payload) {
		MessageContext mc = wsContext.getMessageContext();
	    HttpServletRequest servletRequest = (HttpServletRequest)mc.get(MessageContext.SERVLET_REQUEST); 
		
		ObjectFactory factory = new ObjectFactory();
		DeleteVendorResponse response = factory.createDeleteVendorResponse();
		ResponseHeader responseHeader = factory.createResponseHeader();
		String localTransactionId=UUID.randomUUID().toString();
		
		long startTime = System.currentTimeMillis();		
		logger.start(new TransactionLogInfoTrace(
				this.getClass().getCanonicalName(), 
				payload.toString(),
				String.valueOf(payload.getVendor().getId()),
				servletRequest.getRemoteAddr(), 
				"deleteVendor", 
				"Inicio de eliminacion de proveedores", 
				payload.getRequestHeader().getTransaction(), 
				localTransactionId,
				"",
				"v1", 
				servletRequest.getContextPath()
			)
		);
		
		try {
			responseHeader.setResponseCode(0);
			responseHeader.setResponseDescription("Transaccion exitosa");
			responseHeader.setServiceCode(0);
			responseHeader.setServiceDescription("Transacion exitosa");
			
			vendorRepository.delete(entityToDomain(payload.getVendor()));
		} catch (Exception e) {
			responseHeader.setResponseCode(500);
			responseHeader.setResponseDescription("Ha ocurrido un problema");
			responseHeader.setServiceCode(500);
			responseHeader.setServiceDescription(e.getMessage());
			
			logger.error(new TransactionLogInfoTrace(
					this.getClass().getCanonicalName(), 
					e.getCause().getMessage(),
					String.valueOf(payload.getVendor().getId()),
					servletRequest.getRemoteAddr(), 
					"deleteVendor", 
					e.getMessage(), 
					payload.getRequestHeader().getTransaction(), 
					localTransactionId,
					"",
					"v1", 
					servletRequest.getContextPath()
				)
			);
			
			e.printStackTrace();
		}
		
		responseHeader.setTransaction(localTransactionId);
		response.setResponseHeader(responseHeader);
		
		long duration = System.currentTimeMillis() - startTime;
		logger.end(new TransactionLogEndTrace(
				this.getClass().getCanonicalName(), 
				response.toString(),
				String.valueOf(payload.getVendor().getId()),
				servletRequest.getRemoteAddr(), 
				"deleteVendor", 
				"Fin de eliminacion de proveedores", 
				payload.getRequestHeader().getTransaction(), 
				localTransactionId,
				"",
				"v1", 
				servletRequest.getContextPath(),
				duration,
				responseHeader.getResponseCode()
			)
		);
		
		return response;
	}
	
	
	private OmcInterfaceVendorDomain entityToDomain (OmcInterfaceVendor vendor){
		OmcInterfaceVendorDomain domain = new OmcInterfaceVendorDomain();
		domain.setId(vendor.getId());
		domain.setLookupCode(vendor.getLookupCode());
		domain.setName(vendor.getName());
		domain.setSegment(vendor.getSegment());
		domain.setTransactionType(vendor.getTransactionType());
			
		return domain;
	}
	
}