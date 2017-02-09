/**
 * 
 */
package com.unicomer.process.buy.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.unicomer.demo.common.entity.UnicomerVendor;
import com.unicomer.process.buy.client.LoggerClient;
import com.unicomer.process.buy.entity.EventLog;
import com.unicomer.process.buy.service.VendorService;

/**
 * @author carlosj_rodriguez
 *
 */
@RestController
public class BuyServiceRestController {
	@Autowired VendorService vendorService;
	@Autowired LoggerClient logger;
	@Autowired HttpServletRequest httpRequest;
	@RequestMapping(method = RequestMethod.GET, value = "/vendors/{id}",
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<UnicomerVendor> getVendorById(@PathVariable String id){
		String localTransactionId=UUID.randomUUID().toString();
		long startTime = System.currentTimeMillis();
		try{
			logger.start(new EventLog(
					"Inicio de /vendors/"+id, 
					id, 
					httpRequest.getRemoteAddr()+":"+httpRequest.getRemotePort(), 
					localTransactionId, 
					localTransactionId, 
					"", 
					(System.currentTimeMillis() - startTime)));
			
			UnicomerVendor vendor = vendorService.findOne(id, localTransactionId, startTime);
			
			logger.end(new EventLog(
					"Fin de /vendors/"+id,
					id,
					httpRequest.getRemoteHost()+":"+httpRequest.getRemotePort(),
					localTransactionId, 
					localTransactionId, 
					"", 
					(System.currentTimeMillis() - startTime)));
			return ResponseEntity.ok(vendor);
		}catch (Exception e) {
			logger.end(new EventLog(
					"Error en /vendors/"+id+": "+e.getMessage(),
					id,
					httpRequest.getRemoteHost()+":"+httpRequest.getRemotePort(),
					localTransactionId, 
					localTransactionId, 
					"", 
					(System.currentTimeMillis() - startTime)));			
			logger.end(new EventLog(
					"Fin de /vendors/"+id, 
					id,
					httpRequest.getRemoteHost()+":"+httpRequest.getRemotePort(),
					localTransactionId, 
					localTransactionId, 
					"", 
					(System.currentTimeMillis() - startTime)));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
}
