package com.unicomer.demo.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.unicomer.demo.client.LoggerClient;
import com.unicomer.demo.common.entity.TransactionLogEndTrace;
import com.unicomer.demo.common.entity.TransactionLogInfoTrace;
import com.unicomer.demo.domain.SwimVendorDomain;
import com.unicomer.demo.domain.SwimVendorDomain.ResponseMessage;
import com.unicomer.demo.service.VendorService;

@RestController
public class VendorRestController {
	@Autowired VendorService vendorService;
	@Autowired LoggerClient logger;
	@Value("${spring.application.name}") String applicationName;
	
	@RequestMapping(method = RequestMethod.GET, value = "/vendors/all",
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public SwimVendorDomain.ResponseMessage getVendors(HttpServletRequest servletRequest, HttpServletResponse servletResponse,
			@RequestHeader(name="${general.global-transaction-header}", defaultValue="none", required=false) String globalTransactionId) {
		
		String localTransactionId=UUID.randomUUID().toString();
		if(globalTransactionId.equals("none"))
			globalTransactionId = localTransactionId;
		long startTime = System.currentTimeMillis();		
		TransactionLogInfoTrace info = new TransactionLogInfoTrace(
				"",
				"",
				servletRequest.getRemoteAddr(),
				"Inicio de /vendors/all", 
				globalTransactionId, 
				localTransactionId, 
				"", 
				"v1", 
				applicationName
			);
		info.setEventSource(Thread.currentThread().getStackTrace()[2].toString());
		logger.startTrace(info);
		
		servletResponse.setStatus(HttpServletResponse.SC_OK);
		ResponseMessage response = new ResponseMessage();
		
		try{
			List<SwimVendorDomain> vendorList = new ArrayList<SwimVendorDomain>();
			Iterator<SwimVendorDomain> it = vendorService.findAll().iterator();
			while (it.hasNext()) {
				SwimVendorDomain vendor = it.next();
				vendor.add(linkTo(methodOn(VendorRestController.class).getVendorById(vendor.getVendorId(), servletRequest, servletResponse, globalTransactionId)).withSelfRel());
				vendorList.add(vendor);
			}
			
			if (vendorList.size()>0){
				response.setData(vendorList);
				response.setResponseCode(0);
				response.setResponseDescription("Transaccion exitosa");
				response.setServiceCode(0);
				response.setServiceDescription("Transaccion exitosa");
			}else{
				response.setData(vendorList);
				response.setResponseCode(0);
				response.setResponseDescription("Transaccion exitosa");
				response.setServiceCode(404);
				response.setServiceDescription("No se encontraron datos");
				servletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
			}
		}catch (Exception e) {
			response.setData(null);
			response.setResponseCode(500);
			response.setResponseDescription("Ha ocurrido un problema");
			response.setServiceCode(500);
			response.setServiceDescription(e.getMessage());
			servletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			
			logger.errorTrace(new TransactionLogInfoTrace(
					"",
					e.getCause().getMessage(),
					servletRequest.getRemoteAddr(),
					"Ha ocurrido un problema: " + e.getMessage(), 
					globalTransactionId, 
					localTransactionId, 
					"", 
					"v1", 
					applicationName
				)
			);
		}
		
		response.setTransactionId(localTransactionId);
		response.setDate(Calendar.getInstance().getTime());		
		
		long duration = System.currentTimeMillis() - startTime;
		logger.endTrace(new TransactionLogEndTrace(
				response.getData().size() + " registros recuperados",
				"",
				servletRequest.getRemoteAddr(),
				"Fin de /vendors/all", 
				globalTransactionId, 
				localTransactionId, 
				"", 
				"v1", 
				applicationName,
				duration,
				servletResponse.getStatus()
			)
		);
		
		return response;
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/vendors",
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public SwimVendorDomain.ResponseMessage getVendorsPageable(HttpServletRequest servletRequest, HttpServletResponse servletResponse, 
			@PageableDefault(page=0, value=250, sort = { "name" }, direction = Direction.ASC) Pageable pageable,
			@RequestHeader(name="${general.global-transaction-header}", defaultValue="none", required=false) String globalTransactionId) {
		servletResponse.setStatus(HttpServletResponse.SC_OK);
		ResponseMessage response = new ResponseMessage();
		
		String localTransactionId=UUID.randomUUID().toString();
		if(globalTransactionId.equals("none"))
			globalTransactionId = localTransactionId;
		long startTime = System.currentTimeMillis();		
		logger.startTrace(new TransactionLogInfoTrace(
				"",
				"",
				servletRequest.getRemoteAddr(),
				"Inicio de /vendors", 
				globalTransactionId, 
				localTransactionId, 
				"", 
				"v1", 
				applicationName
			)
		);
		
		try{
			List<SwimVendorDomain> vendorList = new ArrayList<SwimVendorDomain>();
			Iterator<SwimVendorDomain> it = vendorService.listAllByPage(pageable).iterator();
			
			while (it.hasNext()) {
				SwimVendorDomain vendor = it.next();
				vendor.add(linkTo(methodOn(VendorRestController.class).getVendorById(vendor.getVendorId(), null, servletResponse, globalTransactionId)).withSelfRel());
				vendorList.add(vendor);
			}
			
			if (vendorList.size()>0){
				response.setData(vendorList);
				response.setResponseCode(0);
				response.setResponseDescription("Transaccion exitosa");
				response.setServiceCode(0);
				response.setServiceDescription("Transaccion exitosa");
			}else{
				response.setData(vendorList);
				response.setResponseCode(0);
				response.setResponseDescription("Transaccion exitosa");
				response.setServiceCode(404);
				response.setServiceDescription("No se encontraron datos");
				servletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
			}
		}catch (Exception e) {
			response.setData(null);
			response.setResponseCode(500);
			response.setResponseDescription("Ha ocurrido un problema");
			response.setServiceCode(500);
			response.setServiceDescription(e.getMessage());
			servletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			
			logger.errorTrace(new TransactionLogInfoTrace(
					"",
					e.getCause().getMessage(),
					servletRequest.getRemoteAddr(),
					"Ha ocurrido un problema: " + e.getMessage(), 
					globalTransactionId, 
					localTransactionId, 
					"", 
					"v1", 
					applicationName
				)
			);
			e.printStackTrace();
		}
		
		response.setTransactionId(localTransactionId);
		response.setDate(Calendar.getInstance().getTime());
		
		long duration = System.currentTimeMillis() - startTime;
		logger.endTrace(new TransactionLogEndTrace(
				"",
				"",
				servletRequest.getRemoteAddr(),
				"Fin de /vendors", 
				globalTransactionId, 
				localTransactionId, 
				"", 
				"v1", 
				applicationName,
				duration,
				servletResponse.getStatus()
			)
		);
		
		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/vendors",
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
			consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseMessage addVendor(@RequestBody SwimVendorDomain.RequestMessage request, HttpServletRequest servletRequest, HttpServletResponse servletResponse,
			@RequestHeader(name="${general.global-transaction-header}", defaultValue="none", required=false) String globalTransactionId) {
		servletResponse.setStatus(HttpServletResponse.SC_CREATED);
		ResponseMessage response = new ResponseMessage();
		
		String localTransactionId=UUID.randomUUID().toString();
		if(globalTransactionId.equals("none"))
			globalTransactionId = localTransactionId;
		long startTime = System.currentTimeMillis();		
		logger.startTrace(new TransactionLogInfoTrace(
				request.toString(),
				"",
				servletRequest.getRemoteAddr(),
				"Inicio de POST /vendors", 
				globalTransactionId,
				localTransactionId,
				request.getTransaction(),
				"v1", 
				applicationName
			)
		);
		
		try{
			SwimVendorDomain vendorResponse = vendorService.save(request.getData());
			ArrayList<SwimVendorDomain> vendorList = new ArrayList<SwimVendorDomain>();
			
			if (vendorResponse!=null){
				vendorResponse.add(linkTo(methodOn(VendorRestController.class).getVendorById(vendorResponse.getVendorId(), null, null, globalTransactionId)).withSelfRel());
				vendorList.add(vendorResponse);
				response.setData(vendorList);
				response.setResponseCode(0);
				response.setResponseDescription("Transaccion exitosa");
				response.setServiceCode(0);
				response.setServiceDescription("Transaccion exitosa");
			}else{
				response.setData(null);
				response.setResponseCode(0);
				response.setResponseDescription("Transaccion exitosa");
				response.setServiceCode(404);
				response.setServiceDescription("No se encontraron datos");
				servletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
			}
		}catch (Exception e) {
			response.setData(null);
			response.setResponseCode(500);
			response.setResponseDescription("Ha ocurrido un problema");
			response.setServiceCode(500);
			response.setServiceDescription(e.getMessage());
			servletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			
			logger.errorTrace(new TransactionLogInfoTrace(
					"",
					e.getCause().getMessage(),
					servletRequest.getRemoteAddr(),
					"Ha ocurrido un problema: " + e.getMessage(), 
					globalTransactionId,
					localTransactionId,
					request.getTransaction(),
					"v1", 
					applicationName
				)
			);
		}
		response.setTransactionId(localTransactionId);
		response.setDate(Calendar.getInstance().getTime());			
		
		long duration = System.currentTimeMillis() - startTime;
		logger.endTrace(new TransactionLogEndTrace(
				response.toString(),
				"",
				servletRequest.getRemoteAddr(),
				"Fin de POST /vendors", 
				globalTransactionId,
				localTransactionId,
				request.getTransaction(),
				"v1", 
				applicationName,
				duration,
				servletResponse.getStatus()
			)
		);
			
		return response;
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/vendors/{id}",
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
			consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseMessage updateVendor(@RequestBody SwimVendorDomain.RequestMessage request, @PathVariable String id, HttpServletRequest servletRequest, HttpServletResponse servletResponse,
			@RequestHeader(name="${general.global-transaction-header}", defaultValue="none", required=false) String globalTransactionId) {
		servletResponse.setStatus(HttpServletResponse.SC_CREATED);
		ResponseMessage response = new ResponseMessage();
		
		String localTransactionId=UUID.randomUUID().toString();
		if(globalTransactionId.equals("none"))
			globalTransactionId = localTransactionId;
		long startTime = System.currentTimeMillis();		
		logger.startTrace(new TransactionLogInfoTrace(
				request.toString(),
				"",
				servletRequest.getRemoteAddr(),
				"Inicio de PUT /vendors/"+id,
				globalTransactionId,
				localTransactionId,
				request.getTransaction(),
				"v1", 
				applicationName
			)
		);		
		
		try{
			if (vendorService.exists(id)){
				SwimVendorDomain vendorResponse = vendorService.save(request.getData());
				ArrayList<SwimVendorDomain> vendorList = new ArrayList<SwimVendorDomain>();
				vendorResponse.add(linkTo(methodOn(VendorRestController.class).getVendorById(vendorResponse.getVendorId(), null, null, globalTransactionId)).withSelfRel());
				vendorList.add(vendorResponse);
				response.setData(vendorList);
				response.setResponseCode(0);
				response.setResponseDescription("Transaccion exitosa");
				response.setServiceCode(0);
				response.setServiceDescription("Transaccion exitosa");
			}else{
				response.setData(null);
				response.setResponseCode(204);
				response.setResponseDescription("No se encontraron datos para actualizar");
				response.setServiceCode(204);
				response.setServiceDescription("No se encontraron datos para actualizar");
				servletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
			}
		}catch (Exception e) {
			response.setData(null);
			response.setResponseCode(500);
			response.setResponseDescription("Ha ocurrido un problema");
			response.setServiceCode(500);
			response.setServiceDescription(e.getMessage());
			servletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			
			logger.errorTrace(new TransactionLogInfoTrace(
					"",
					e.getCause().getMessage(),
					servletRequest.getRemoteAddr(),
					"Ha ocurrido un problema: " + e.getMessage(), 
					globalTransactionId,
					localTransactionId,
					request.getTransaction(), 
					"v1", 
					applicationName
				)
			);
		}
		response.setTransactionId(request.getTransaction());
		response.setDate(Calendar.getInstance().getTime());
		
		long duration = System.currentTimeMillis() - startTime;
		logger.endTrace(new TransactionLogEndTrace(
				response.toString(),
				"",
				servletRequest.getRemoteAddr(),
				"Fin de PUT /vendors/"+id,
				globalTransactionId,
				localTransactionId,
				request.getTransaction(), 
				"v1", 
				applicationName,
				duration,
				servletResponse.getStatus()
			)
		);
				
		return response;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/vendors/{id}",
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseMessage getVendorById(@PathVariable String id, HttpServletRequest servletRequest, HttpServletResponse servletResponse,
			@RequestHeader(name="${general.global-transaction-header}", defaultValue="none", required=false) String globalTransactionId) {
		servletResponse.setStatus(HttpServletResponse.SC_OK);
		ResponseMessage response = new ResponseMessage();
		SwimVendorDomain vendor=vendorService.findOne(id); 
		
		String localTransactionId=UUID.randomUUID().toString();
		if(globalTransactionId.equals("none"))
			globalTransactionId = localTransactionId;
		long startTime = System.currentTimeMillis();		
		logger.startTrace(new TransactionLogInfoTrace(
				"",
				"",
				servletRequest.getRemoteAddr(),
				"Inicio de GET /vendors/"+id,
				globalTransactionId,
				localTransactionId,
				"", 
				"v1", 
				applicationName
			)
		);
		
		if (vendor!=null){
			ArrayList<SwimVendorDomain> vendorList = new ArrayList<SwimVendorDomain>();
			vendor.add(linkTo(methodOn(VendorRestController.class).getVendorById(vendor.getVendorId(), null, servletResponse, globalTransactionId)).withSelfRel());
			vendorList.add(vendor);
			response.setData(vendorList);
			response.setResponseCode(0);
			response.setResponseDescription("Transaccion exitosa");
			response.setServiceCode(0);
			response.setServiceDescription("Transaccion exitosa");
		}else{
			response.setResponseCode(0);
			response.setResponseDescription("Transaccion exitosa");
			response.setServiceCode(404);
			response.setServiceDescription("No se encontraron datos");
			servletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		
		response.setTransactionId(localTransactionId);
		response.setDate(Calendar.getInstance().getTime());
		
		long duration = System.currentTimeMillis() - startTime;
		logger.endTrace(new TransactionLogEndTrace(
				response.toString(),
				"",
				servletRequest.getRemoteAddr(),
				"Fin de GET /vendors/"+id,
				globalTransactionId, 
				localTransactionId, 
				"", 
				"v1", 
				applicationName,
				duration,
				servletResponse.getStatus()
			)
		);
		
		return response;
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/vendors/{id}")
	public ResponseMessage deleteVendor(@PathVariable String id, HttpServletRequest servletRequest, HttpServletResponse servletResponse,
			@RequestHeader(name="${general.global-transaction-header}", defaultValue="none", required=false) String globalTransactionId) {
		servletResponse.setStatus(HttpServletResponse.SC_NO_CONTENT);
		ResponseMessage response = new ResponseMessage();
		
		String localTransactionId=UUID.randomUUID().toString();
		if(globalTransactionId.equals("none"))
			globalTransactionId = localTransactionId;
		long startTime = System.currentTimeMillis();		
		logger.startTrace(new TransactionLogInfoTrace(
				id,
				"",
				servletRequest.getRemoteAddr(),
				"Inicio de DELETE /vendors/"+id,
				globalTransactionId,
				localTransactionId,
				"", 
				"v1", 
				applicationName
			)
		);
		
		try{
			if (vendorService.exists(id)){
				vendorService.delete(id);
				response.setResponseCode(0);
				response.setResponseDescription("Transaccion exitosa");
				response.setServiceCode(0);
				response.setServiceDescription("Transaccion exitosa");
			}else{
				response.setResponseCode(204);
				response.setResponseDescription("No se encontraron datos para actualizar");
				response.setServiceCode(204);
				response.setServiceDescription("No se encontraron datos para actualizar");
				servletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
			}
		}catch (Exception e) {
			response.setResponseCode(500);
			response.setResponseDescription("Ha ocurrido un problema");
			response.setServiceCode(500);
			response.setServiceDescription(e.getMessage());
			servletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			
			logger.errorTrace(new TransactionLogInfoTrace(
					"",
					e.getCause().getMessage(),
					servletRequest.getRemoteAddr(),
					"Ha ocurrido un problema: " + e.getMessage(), 
					globalTransactionId, 
					localTransactionId, 
					"", 
					"v1", 
					applicationName
				)
			);
		}
		
		response.setTransactionId(localTransactionId);
		response.setDate(Calendar.getInstance().getTime());
		long duration = System.currentTimeMillis() - startTime;
		logger.endTrace(new TransactionLogEndTrace(
				response.toString(),
				"",
				servletRequest.getRemoteAddr(),
				"Fin de DELETE /vendors/"+id,
				globalTransactionId, 
				localTransactionId, 
				"", 
				"v1", 
				applicationName,
				duration,
				servletResponse.getStatus()
			)
		);
		
		return response;
	}
}
