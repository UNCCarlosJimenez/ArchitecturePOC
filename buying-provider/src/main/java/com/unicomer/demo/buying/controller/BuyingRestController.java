package com.unicomer.demo.buying.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.unicomer.demo.buying.dao.LoggerClient;
import com.unicomer.demo.buying.service.VendorService;
import com.unicomer.demo.common.entity.TransactionLogEndTrace;
import com.unicomer.demo.common.entity.TransactionLogInfoTrace;
import com.unicomer.demo.common.entity.UnicomerVendor;

@RestController
public class BuyingRestController {
	@Autowired VendorService vendorService;
	@Autowired LoggerClient logger;
	@Value("${spring.application.name}") String applicationName;
	
	@RequestMapping(method = RequestMethod.GET, value = "/vendors/",
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public List<UnicomerVendor> getVendors(HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
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
		try{
			Iterator<UnicomerVendor> it = vendorService.findAll().iterator();
			while (it.hasNext()) {
				UnicomerVendor vendor = it.next();
				vendorsResult.add(vendor);
			}
			if (vendorsResult.size()==0){
				servletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
			}
		}catch (Exception e) {
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
			
			e.printStackTrace();
		}
		
		long duration = System.currentTimeMillis() - startTime;
		logger.endTrace(new TransactionLogEndTrace(
				this.getClass().getCanonicalName(), 
				vendorsResult.size() + " registros recuperados",
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
		return vendorsResult;
	}
		
	@RequestMapping(method = RequestMethod.GET, value = "/vendors/{id}",
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public UnicomerVendor getVendor(@PathVariable(name="id") String id, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
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
		UnicomerVendor vendorResult = new UnicomerVendor();
		try{
			vendorResult = vendorService.findOne(id);
			if (vendorResult==null){
				servletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
			}
		}catch (Exception e) {
			servletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		
		long duration = System.currentTimeMillis() - startTime;
		logger.endTrace(new TransactionLogEndTrace(
				this.getClass().getCanonicalName(), 
				vendorResult.toString(),
				"",
				servletRequest.getRemoteAddr(), 
				"getVendor", 
				"Fin de /vendors/"+id, 
				localTransactionId, 
				localTransactionId, 
				"", 
				"v1", 
				applicationName,
				duration,
				servletResponse.getStatus()
			)
		);
		return vendorResult;
	}
}
