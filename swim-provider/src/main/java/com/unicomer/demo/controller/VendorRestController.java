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
			@RequestHeader(name="X-Forwarded-For", defaultValue="none", required=false) String forwardedFor) {
		
		String localTransactionId=UUID.randomUUID().toString();
		long startTime = System.currentTimeMillis();		
		logger.startTrace(new TransactionLogInfoTrace(
				this.getClass().getCanonicalName(), 
				"",
				"",
				servletRequest.getRemoteAddr(), 
				"getVendors", 
				"Inicio de /vendors/all", 
				localTransactionId, 
				localTransactionId, 
				"", 
				"v1", 
				applicationName
			)
		);
		
		servletResponse.setStatus(HttpServletResponse.SC_OK);
		ResponseMessage response = new ResponseMessage();
		
		try{
			List<SwimVendorDomain> vendorList = new ArrayList<SwimVendorDomain>();
			Iterator<SwimVendorDomain> it = vendorService.findAll().iterator();
			while (it.hasNext()) {
				SwimVendorDomain vendor = it.next();
				vendor.add(linkTo(methodOn(VendorRestController.class).getVendorById(vendor.getVendorId(), servletRequest, servletResponse)).withSelfRel());
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
					this.getClass().getCanonicalName(), 
					"",
					e.getCause().getMessage(),
					servletRequest.getRemoteAddr(), 
					"getVendors", 
					"Ha ocurrido un problema: " + e.getMessage(), 
					localTransactionId, 
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
				this.getClass().getCanonicalName(), 
				response.getData().size() + " registros recuperados",
				"",
				servletRequest.getRemoteAddr(), 
				"getVendors", 
				"Fin de /vendors/all", 
				localTransactionId, 
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
			@PageableDefault(page=0, value=250, sort = { "name" }, direction = Direction.ASC) Pageable pageable) {
		servletResponse.setStatus(HttpServletResponse.SC_OK);
		ResponseMessage response = new ResponseMessage();
		
		String localTransactionId=UUID.randomUUID().toString();
		long startTime = System.currentTimeMillis();		
		logger.startTrace(new TransactionLogInfoTrace(
				this.getClass().getCanonicalName(), 
				"",
				"",
				servletRequest.getRemoteAddr(), 
				"getVendors", 
				"Inicio de /vendors", 
				localTransactionId, 
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
				vendor.add(linkTo(methodOn(VendorRestController.class).getVendorById(vendor.getVendorId(), null, servletResponse)).withSelfRel());
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
					this.getClass().getCanonicalName(), 
					"",
					e.getCause().getMessage(),
					servletRequest.getRemoteAddr(), 
					"getVendors", 
					"Ha ocurrido un problema: " + e.getMessage(), 
					localTransactionId, 
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
				this.getClass().getCanonicalName(), 
				"",
				"",
				servletRequest.getRemoteAddr(), 
				"getVendors", 
				"Fin de /vendors", 
				localTransactionId, 
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
	public ResponseMessage addVendor(@RequestBody SwimVendorDomain.RequestMessage request, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
		servletResponse.setStatus(HttpServletResponse.SC_CREATED);
		ResponseMessage response = new ResponseMessage();
		
		String localTransactionId=UUID.randomUUID().toString();
		long startTime = System.currentTimeMillis();		
		logger.startTrace(new TransactionLogInfoTrace(
				this.getClass().getCanonicalName(), 
				request.toString(),
				"",
				servletRequest.getRemoteAddr(), 
				"addVendor", 
				"Inicio de POST /vendors", 
				request.getTransaction(),
				localTransactionId,
				"", 
				"v1", 
				applicationName
			)
		);
		
		try{
			if(request.getTransaction().isEmpty())
				request.setTransaction(localTransactionId);
			
			SwimVendorDomain vendorResponse = vendorService.save(request.getData());
			ArrayList<SwimVendorDomain> vendorList = new ArrayList<SwimVendorDomain>();
			
			if (vendorResponse!=null){
				vendorResponse.add(linkTo(methodOn(VendorRestController.class).getVendorById(vendorResponse.getVendorId(), null, null)).withSelfRel());
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
					this.getClass().getCanonicalName(), 
					"",
					e.getCause().getMessage(),
					servletRequest.getRemoteAddr(), 
					"addVendor", 
					"Ha ocurrido un problema: " + e.getMessage(), 
					request.getTransaction(),
					localTransactionId,
					"", 
					"v1", 
					applicationName
				)
			);
		}
		response.setTransactionId(request.getTransaction());
		response.setDate(Calendar.getInstance().getTime());			
		
		long duration = System.currentTimeMillis() - startTime;
		logger.endTrace(new TransactionLogEndTrace(
				this.getClass().getCanonicalName(), 
				response.toString(),
				"",
				servletRequest.getRemoteAddr(), 
				"addVendor", 
				"Fin de POST /vendors", 
				request.getTransaction(),
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
	
	@RequestMapping(method = RequestMethod.PUT, value = "/vendors/{id}",
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
			consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseMessage updateVendor(@RequestBody SwimVendorDomain.RequestMessage request, @PathVariable String id, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
		servletResponse.setStatus(HttpServletResponse.SC_CREATED);
		ResponseMessage response = new ResponseMessage();
		
		String localTransactionId=UUID.randomUUID().toString();
		long startTime = System.currentTimeMillis();		
		logger.startTrace(new TransactionLogInfoTrace(
				this.getClass().getCanonicalName(), 
				request.toString(),
				"",
				servletRequest.getRemoteAddr(), 
				"updateVendor", 
				"Inicio de PUT /vendors/"+id,
				request.getTransaction(),
				localTransactionId,
				"", 
				"v1", 
				applicationName
			)
		);		
		
		try{
			if(request.getTransaction().isEmpty())
				request.setTransaction(localTransactionId);
			
			if (vendorService.exists(id)){
				SwimVendorDomain vendorResponse = vendorService.save(request.getData());
				ArrayList<SwimVendorDomain> vendorList = new ArrayList<SwimVendorDomain>();
				vendorResponse.add(linkTo(methodOn(VendorRestController.class).getVendorById(vendorResponse.getVendorId(), null, null)).withSelfRel());
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
					this.getClass().getCanonicalName(), 
					"",
					e.getCause().getMessage(),
					servletRequest.getRemoteAddr(), 
					"updateVendor", 
					"Ha ocurrido un problema: " + e.getMessage(), 
					request.getTransaction(),
					localTransactionId,
					"", 
					"v1", 
					applicationName
				)
			);
		}
		response.setTransactionId(request.getTransaction());
		response.setDate(Calendar.getInstance().getTime());
		
		long duration = System.currentTimeMillis() - startTime;
		logger.endTrace(new TransactionLogEndTrace(
				this.getClass().getCanonicalName(), 
				response.toString(),
				"",
				servletRequest.getRemoteAddr(), 
				"updateVendor", 
				"Fin de PUT /vendors/"+id,
				request.getTransaction(),
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
	
	@RequestMapping(method = RequestMethod.GET, value = "/vendors/{id}",
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseMessage getVendorById(@PathVariable String id, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
		servletResponse.setStatus(HttpServletResponse.SC_OK);
		ResponseMessage response = new ResponseMessage();
		SwimVendorDomain vendor=vendorService.findOne(id); 
		
		String localTransactionId=UUID.randomUUID().toString();
		long startTime = System.currentTimeMillis();		
		logger.startTrace(new TransactionLogInfoTrace(
				this.getClass().getCanonicalName(), 
				"",
				"",
				servletRequest.getRemoteAddr(), 
				"getVendorById", 
				"Inicio de GET /vendors/"+id,
				localTransactionId, 
				localTransactionId, 
				"", 
				"v1", 
				applicationName
			)
		);
		
		if (vendor!=null){
			ArrayList<SwimVendorDomain> vendorList = new ArrayList<SwimVendorDomain>();
			vendor.add(linkTo(methodOn(VendorRestController.class).getVendorById(vendor.getVendorId(), null, servletResponse)).withSelfRel());
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
				this.getClass().getCanonicalName(), 
				response.toString(),
				"",
				servletRequest.getRemoteAddr(), 
				"getVendorById", 
				"Fin de GET /vendors/"+id,
				localTransactionId, 
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
	public ResponseMessage deleteVendor(@PathVariable String id, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
		servletResponse.setStatus(HttpServletResponse.SC_NO_CONTENT);
		ResponseMessage response = new ResponseMessage();
		
		String localTransactionId=UUID.randomUUID().toString();
		long startTime = System.currentTimeMillis();		
		logger.startTrace(new TransactionLogInfoTrace(
				this.getClass().getCanonicalName(), 
				id,
				"",
				servletRequest.getRemoteAddr(), 
				"deleteVendor", 
				"Inicio de DELETE /vendors/"+id,
				localTransactionId, 
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
					this.getClass().getCanonicalName(), 
					"",
					e.getCause().getMessage(),
					servletRequest.getRemoteAddr(), 
					"deleteVendor", 
					"Ha ocurrido un problema: " + e.getMessage(), 
					localTransactionId, 
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
				this.getClass().getCanonicalName(), 
				response.toString(),
				"",
				servletRequest.getRemoteAddr(), 
				"deleteVendor", 
				"Fin de DELETE /vendors/"+id,
				localTransactionId, 
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
