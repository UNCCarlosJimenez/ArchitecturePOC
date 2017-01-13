package com.unicomer.demo.buying.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.unicomer.demo.buying.dao.LoggerClient;
import com.unicomer.demo.buying.service.VendorService;
import com.unicomer.demo.common.entity.TransactionLogEndTrace;
import com.unicomer.demo.common.entity.TransactionLogInfoTrace;
import com.unicomer.demo.common.entity.UnicomerVendor;
import com.unicomer.demo.common.entity.UnicomerVendor.RequestVendorMessage;
import com.unicomer.demo.common.entity.UnicomerVendor.ResponseVendorMessage;

@RestController
public class BuyingRestController {
	@Autowired VendorService vendorService;
	@Autowired LoggerClient logger;
	@Value("${spring.application.name}") String applicationName;
	
	@RequestMapping(method = RequestMethod.GET, value = "/vendors",
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public UnicomerVendor.ResponseVendorMessage getVendors(HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
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
		
		servletResponse.setStatus(HttpServletResponse.SC_OK);
		List<UnicomerVendor> vendorsResult = new ArrayList<UnicomerVendor>();
		ResponseVendorMessage response = new ResponseVendorMessage();
		try{
			Iterator<UnicomerVendor> it = vendorService.findAll().iterator();
			while (it.hasNext()) {
				UnicomerVendor vendor = it.next();
				vendorsResult.add(vendor);
			}
			
			response.setData(vendorsResult);
			response.setResponseCode(0);
			response.setResponseDescription("Transaccion exitosa");
			response.setServiceCode(0);
			response.setServiceDescription("Transaccion exitosa");
			if (vendorsResult.size()==0){
				response.setServiceCode(404);
				response.setServiceDescription("swim-provider no retornó resultados");
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
				response.toString(),
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
		
	@RequestMapping(method = RequestMethod.GET, value = "/vendors/{id}",
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public UnicomerVendor.ResponseVendorMessage getVendor(@PathVariable(name="id") String id, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
		String localTransactionId=UUID.randomUUID().toString();
		long startTime = System.currentTimeMillis();		
		logger.startTrace(new TransactionLogInfoTrace(
				this.getClass().getCanonicalName(), 
				"",
				"",
				servletRequest.getRemoteAddr(), 
				"getVendor", 
				"Inicio de /vendors/"+id, 
				localTransactionId, 
				localTransactionId, 
				"", 
				"v1", 
				applicationName
			)
		);
		
		servletResponse.setStatus(HttpServletResponse.SC_OK);
		List<UnicomerVendor> vendorsResult = new ArrayList<UnicomerVendor>();
		ResponseVendorMessage response = new ResponseVendorMessage();
		try{
			vendorsResult.add(vendorService.findOne(id));
			response.setData(vendorsResult);
			response.setResponseCode(0);
			response.setResponseDescription("Transaccion exitosa");
			response.setServiceCode(0);
			response.setServiceDescription("Transaccion exitosa");
			
			if (vendorsResult.size()==0){
				response.setServiceCode(404);
				response.setServiceDescription("swim-provider no retornó datos");
				servletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
			}
		}catch (Exception e) {
			response.setResponseCode(500);
			response.setResponseDescription("Ha ocurrido un problema");
			response.setServiceCode(500);
			response.setServiceDescription(e.getMessage());
			
			servletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
			
			logger.errorTrace(new TransactionLogInfoTrace(
					this.getClass().getCanonicalName(), 
					"",
					e.getCause().getMessage(),
					servletRequest.getRemoteAddr(), 
					"getVendor", 
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
				"getVendor", 
				"Fin de /vendor/"+id, 
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
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public UnicomerVendor.ResponseVendorMessage newVendor(@RequestBody RequestVendorMessage request, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
		String localTransactionId=UUID.randomUUID().toString();
		
		long startTime = System.currentTimeMillis();		
		logger.startTrace(new TransactionLogInfoTrace(
				this.getClass().getCanonicalName(), 
				"",
				"",
				servletRequest.getRemoteAddr(), 
				"newVendor", 
				"Inicio de POST /vendors", 
				localTransactionId, 
				localTransactionId, 
				request.getTransaction(),
				"v1", 
				applicationName
			)
		);
		
		servletResponse.setStatus(HttpServletResponse.SC_CREATED);
		List<UnicomerVendor> vendorsResult = new ArrayList<UnicomerVendor>();
		ResponseVendorMessage response = new ResponseVendorMessage();
		try{
			vendorsResult.add(vendorService.save(localTransactionId, request.getData()));
			
			response.setData(vendorsResult);
			response.setResponseCode(0);
			response.setResponseDescription("Transaccion exitosa");
			response.setServiceCode(0);
			response.setServiceDescription("Transaccion exitosa");
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
					"newVendor", 
					"Ha ocurrido un problema: " + e.getMessage(), 
					localTransactionId, 
					localTransactionId, 
					request.getTransaction(),
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
				response.toString(),
				"",
				servletRequest.getRemoteAddr(), 
				"newVendor", 
				"Fin de POST /vendors",
				localTransactionId, 
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
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public UnicomerVendor.ResponseVendorMessage updateVendor(@RequestBody RequestVendorMessage request, @PathVariable(name="id") String id,
			HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
		String localTransactionId=UUID.randomUUID().toString();
		
		long startTime = System.currentTimeMillis();		
		logger.startTrace(new TransactionLogInfoTrace(
				this.getClass().getCanonicalName(), 
				request.toString(),
				id,
				servletRequest.getRemoteAddr(), 
				"updateVendor", 
				"Inicio de PUT /vendors/"+id, 
				localTransactionId, 
				localTransactionId, 
				request.getTransaction(),
				"v1", 
				applicationName
			)
		);
		
		servletResponse.setStatus(HttpServletResponse.SC_CREATED);
		ResponseVendorMessage response = new ResponseVendorMessage();
		try{
			response.setResponseCode(0);
			response.setResponseDescription("Transaccion exitosa");
			response.setServiceCode(0);
			response.setServiceDescription("Transaccion exitosa");
		}catch (Exception e) {
			response.setResponseCode(500);
			response.setResponseDescription("Ha ocurrido un problema");
			response.setServiceCode(500);
			response.setServiceDescription(e.getMessage());
			
			servletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			logger.errorTrace(new TransactionLogInfoTrace(
					this.getClass().getCanonicalName(), 
					id,
					e.getCause().getMessage(),
					servletRequest.getRemoteAddr(), 
					"updateVendor", 
					"Ha ocurrido un problema: " + e.getMessage(), 
					localTransactionId, 
					localTransactionId, 
					request.getTransaction(),
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
				response.toString(),
				id,
				servletRequest.getRemoteAddr(), 
				"updateVendor", 
				"Fin de PUT /vendors/"+id,
				localTransactionId, 
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
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/vendors/{id}",
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public UnicomerVendor.ResponseVendorMessage deleteVendor(@PathVariable(name="id") String id,
			HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
		String localTransactionId=UUID.randomUUID().toString();
		
		long startTime = System.currentTimeMillis();		
		logger.startTrace(new TransactionLogInfoTrace(
				this.getClass().getCanonicalName(), 
				id,
				id,
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
		
		servletResponse.setStatus(HttpServletResponse.SC_CREATED);
		
		ResponseVendorMessage response = new ResponseVendorMessage();
		try{
			vendorService.delete(localTransactionId, id);
			response.setResponseCode(0);
			response.setResponseDescription("Transaccion exitosa");
			response.setServiceCode(0);
			response.setServiceDescription("Transaccion exitosa");
		}catch (Exception e) {
			response.setResponseCode(500);
			response.setResponseDescription("Ha ocurrido un problema");
			response.setServiceCode(500);
			response.setServiceDescription(e.getMessage());
			
			servletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			logger.errorTrace(new TransactionLogInfoTrace(
					this.getClass().getCanonicalName(), 
					id,
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
			
			e.printStackTrace();
		}
		
		response.setTransactionId(localTransactionId);
		response.setDate(Calendar.getInstance().getTime());
		
		long duration = System.currentTimeMillis() - startTime;
		logger.endTrace(new TransactionLogEndTrace(
				this.getClass().getCanonicalName(), 
				response.toString(),
				id,
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
