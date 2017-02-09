/**
 * 
 */
package com.unicomer.process.buy.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	public ResponseEntity<UnicomerVendor.ResponseVendorMessage> getVendorById(@PathVariable String id){
		String requestPath = httpRequest.getMethod() + " " + httpRequest.getRequestURI();
		String localTransactionId=UUID.randomUUID().toString();
		long startTime = System.currentTimeMillis();
		
		UnicomerVendor.ResponseVendorMessage response = new UnicomerVendor.ResponseVendorMessage();
		response.setDate(Calendar.getInstance().getTime());
		response.setTransactionId(localTransactionId);
		response.setResponseCode(100);
		response.setResponseDescription("Transaccion exitosa");
		try{
			logger.start(new EventLog(
					"Inicio de " + requestPath, 
					id, 
					httpRequest.getRemoteAddr()+":"+httpRequest.getRemotePort(), 
					localTransactionId, 
					localTransactionId, 
					"", 
					(System.currentTimeMillis() - startTime)));
			
			UnicomerVendor vendor = vendorService.findOne(id, localTransactionId, startTime);
			if(vendor.getVendorId()==null){
				response.setResponseCode(HttpStatus.NOT_FOUND.value());
				response.setResponseDescription("No se encontró el Vendor con ID " + id);
				
				logger.error(new EventLog(
						"Error en " + requestPath +": Datos no encontrados",
						id,
						httpRequest.getRemoteHost()+":"+httpRequest.getRemotePort(),
						localTransactionId, 
						localTransactionId, 
						"", 
						(System.currentTimeMillis() - startTime)));
				
				logger.end(new EventLog(
						"Fin de " + requestPath,
						id,
						httpRequest.getRemoteHost()+":"+httpRequest.getRemotePort(),
						localTransactionId, 
						localTransactionId, 
						"", 
						(System.currentTimeMillis() - startTime)));
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
			}
			
			List<UnicomerVendor> listVendor = new ArrayList<UnicomerVendor>();
			listVendor.add(vendor);
			response.setData(listVendor);
			logger.end(new EventLog(
					"Fin de " + requestPath,
					id,
					httpRequest.getRemoteHost()+":"+httpRequest.getRemotePort(),
					localTransactionId, 
					localTransactionId, 
					"", 
					(System.currentTimeMillis() - startTime)));
			return ResponseEntity.ok(response);
		}catch (Exception ex) {
			response.setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.setResponseDescription("Ha ocurrido un problema. Para mayor información consulte el log del servicio.");
			
			logger.error(new EventLog(
					"Error en " + requestPath +": "+ ExceptionUtils.getStackTrace(ex),
					id,
					httpRequest.getRemoteHost()+":"+httpRequest.getRemotePort(),
					localTransactionId, 
					localTransactionId, 
					"", 
					(System.currentTimeMillis() - startTime)));
			logger.end(new EventLog(
					"Fin de " + requestPath , 
					id,
					httpRequest.getRemoteHost()+":"+httpRequest.getRemotePort(),
					localTransactionId, 
					localTransactionId, 
					"", 
					(System.currentTimeMillis() - startTime)));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/vendors",
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<UnicomerVendor.ResponseVendorMessage> getVendors(){
		String requestPath = httpRequest.getMethod() + " " + httpRequest.getRequestURI();
		String localTransactionId=UUID.randomUUID().toString();
		long startTime = System.currentTimeMillis();
		
		UnicomerVendor.ResponseVendorMessage response = new UnicomerVendor.ResponseVendorMessage();
		response.setDate(Calendar.getInstance().getTime());
		response.setTransactionId(localTransactionId);
		response.setResponseCode(100);
		response.setResponseDescription("Transaccion exitosa");
		try{
			logger.start(new EventLog(
					"Inicio de " + requestPath, 
					"",
					httpRequest.getRemoteAddr()+":"+httpRequest.getRemotePort(), 
					localTransactionId, 
					localTransactionId, 
					"", 
					(System.currentTimeMillis() - startTime)));
			
			response.setData(vendorService.findAll(localTransactionId, startTime));
			if(response.getData()==null){
				response.setResponseCode(HttpStatus.NOT_FOUND.value());
				response.setResponseDescription("No se encontraron datos");
				
				logger.error(new EventLog(
						"Error en " + requestPath + ": Datos no encontrados",
						"",
						httpRequest.getRemoteHost()+":"+httpRequest.getRemotePort(),
						localTransactionId, 
						localTransactionId, 
						"", 
						(System.currentTimeMillis() - startTime)));
				
				logger.end(new EventLog(
						"Fin de " + requestPath,
						"",
						httpRequest.getRemoteHost()+":"+httpRequest.getRemotePort(),
						localTransactionId, 
						localTransactionId, 
						"", 
						(System.currentTimeMillis() - startTime)));
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
			}
			
			logger.end(new EventLog(
					"Fin de " + requestPath,
					"",
					httpRequest.getRemoteHost()+":"+httpRequest.getRemotePort(),
					localTransactionId, 
					localTransactionId, 
					"", 
					(System.currentTimeMillis() - startTime)));
			return ResponseEntity.ok(response);
		}catch (Exception ex) {
			response.setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.setResponseDescription("Ha ocurrido un problema. Para mayor información consulte el log del servicio.");
			
			logger.error(new EventLog(
					"Error en " + requestPath + ": " + ExceptionUtils.getStackTrace(ex),
					"",
					httpRequest.getRemoteHost()+":"+httpRequest.getRemotePort(),
					localTransactionId, 
					localTransactionId, 
					"", 
					(System.currentTimeMillis() - startTime)));
			logger.end(new EventLog(
					"Fin de " + requestPath, 
					"",
					httpRequest.getRemoteHost()+":"+httpRequest.getRemotePort(),
					localTransactionId, 
					localTransactionId, 
					"", 
					(System.currentTimeMillis() - startTime)));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/vendors",
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
			consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<UnicomerVendor.ResponseVendorMessage> addVendor(@RequestBody UnicomerVendor.RequestVendorMessage request){
		String requestPath = httpRequest.getMethod() + " " + httpRequest.getRequestURI();
		String localTransactionId=UUID.randomUUID().toString();
		long startTime = System.currentTimeMillis();
		
		UnicomerVendor.ResponseVendorMessage response = new UnicomerVendor.ResponseVendorMessage();
		response.setDate(Calendar.getInstance().getTime());
		response.setTransactionId(localTransactionId);
		response.setResponseCode(100);
		response.setResponseDescription("Transaccion exitosa");
		try{
			logger.start(new EventLog(
					"Inicio de " + requestPath, 
					request.getData().getVendorId(),
					httpRequest.getRemoteAddr()+":"+httpRequest.getRemotePort(), 
					localTransactionId, 
					localTransactionId, 
					request.getTransaction(), 
					(System.currentTimeMillis() - startTime)));
			
			UnicomerVendor vendor = vendorService.save(localTransactionId, startTime, request.getData());
			if(vendor.getVendorId()==null){
				response.setResponseCode(HttpStatus.BAD_REQUEST.value());
				response.setResponseDescription("Error al guardar el registro");
				
				logger.error(new EventLog(
						"Error en " + requestPath +": Datos no encontrados",
						request.getData().getVendorId(),
						httpRequest.getRemoteHost()+":"+httpRequest.getRemotePort(),
						localTransactionId, 
						localTransactionId, 
						request.getTransaction(), 
						(System.currentTimeMillis() - startTime)));
				
				logger.end(new EventLog(
						"Fin de " + requestPath,
						request.getData().getVendorId(),
						httpRequest.getRemoteHost()+":"+httpRequest.getRemotePort(),
						localTransactionId, 
						localTransactionId, 
						request.getTransaction(), 
						(System.currentTimeMillis() - startTime)));
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
			}
			
			List<UnicomerVendor> listVendor = new ArrayList<UnicomerVendor>();
			listVendor.add(vendor);
			response.setData(listVendor);
			
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(vendor.getVendorId()).toUri();			
			logger.end(new EventLog(
					"Fin de " + requestPath,
					request.getData().getVendorId(),
					httpRequest.getRemoteHost()+":"+httpRequest.getRemotePort(),
					localTransactionId, 
					localTransactionId, 
					request.getTransaction(), 
					(System.currentTimeMillis() - startTime)));
			return ResponseEntity.created(location).body(response);
		}catch (Exception ex) {
			response.setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.setResponseDescription("Ha ocurrido un problema. Para mayor información consulte el log del servicio.");
			
			logger.error(new EventLog(
					"Error en " + requestPath +": "+ ExceptionUtils.getStackTrace(ex),
					request.getData().getVendorId(),
					httpRequest.getRemoteHost()+":"+httpRequest.getRemotePort(),
					localTransactionId, 
					localTransactionId, 
					request.getTransaction(), 
					(System.currentTimeMillis() - startTime)));
			logger.end(new EventLog(
					"Fin de " + requestPath , 
					request.getData().getVendorId(),
					httpRequest.getRemoteHost()+":"+httpRequest.getRemotePort(),
					localTransactionId, 
					localTransactionId, 
					request.getTransaction(), 
					(System.currentTimeMillis() - startTime)));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
	
	@RequestMapping(method = RequestMethod.PUT, value = "/vendors/{id}",
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
			consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<UnicomerVendor.ResponseVendorMessage> updateVendor(@RequestBody UnicomerVendor.RequestVendorMessage request, @PathVariable String id){
		String requestPath = httpRequest.getMethod() + " " + httpRequest.getRequestURI();
		String localTransactionId=UUID.randomUUID().toString();
		long startTime = System.currentTimeMillis();
		
		UnicomerVendor.ResponseVendorMessage response = new UnicomerVendor.ResponseVendorMessage();
		response.setDate(Calendar.getInstance().getTime());
		response.setTransactionId(localTransactionId);
		response.setResponseCode(100);
		response.setResponseDescription("Transaccion exitosa");
		try{
			logger.start(new EventLog(
					"Inicio de " + requestPath, 
					request.getData().getVendorId(),
					httpRequest.getRemoteAddr()+":"+httpRequest.getRemotePort(), 
					localTransactionId, 
					localTransactionId, 
					request.getTransaction(), 
					(System.currentTimeMillis() - startTime)));
			
			UnicomerVendor vendor = new UnicomerVendor();
			if(vendorService.exists(id, localTransactionId, startTime)){
				vendor = vendorService.save(localTransactionId, startTime, request.getData());
				
				if(vendor.getVendorId()==null){
					response.setResponseCode(HttpStatus.BAD_REQUEST.value());
					response.setResponseDescription("Error al guardar el registro");
					
					logger.error(new EventLog(
							"Error en " + requestPath +": Datos no encontrados",
							request.getData().getVendorId(),
							httpRequest.getRemoteHost()+":"+httpRequest.getRemotePort(),
							localTransactionId, 
							localTransactionId, 
							request.getTransaction(), 
							(System.currentTimeMillis() - startTime)));
					
					logger.end(new EventLog(
							"Fin de " + requestPath,
							request.getData().getVendorId(),
							httpRequest.getRemoteHost()+":"+httpRequest.getRemotePort(),
							localTransactionId, 
							localTransactionId, 
							request.getTransaction(), 
							(System.currentTimeMillis() - startTime)));
					return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
				}
			}else{
				response.setResponseCode(HttpStatus.NOT_FOUND.value());
				response.setResponseDescription("No se encontró registro para actualizar");
				
				logger.error(new EventLog(
						"Error en " + requestPath +": Datos no encontrados",
						id,
						httpRequest.getRemoteHost()+":"+httpRequest.getRemotePort(),
						localTransactionId, 
						localTransactionId, 
						request.getTransaction(), 
						(System.currentTimeMillis() - startTime)));
				logger.end(new EventLog(
						"Fin de " + requestPath,
						id,
						httpRequest.getRemoteHost()+":"+httpRequest.getRemotePort(),
						localTransactionId, 
						localTransactionId, 
						request.getTransaction(), 
						(System.currentTimeMillis() - startTime)));
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
			}
			
			List<UnicomerVendor> listVendor = new ArrayList<UnicomerVendor>();
			listVendor.add(vendor);
			response.setData(listVendor);
			
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(vendor.getVendorId()).toUri();			
			logger.end(new EventLog(
					"Fin de " + requestPath,
					request.getData().getVendorId(),
					httpRequest.getRemoteHost()+":"+httpRequest.getRemotePort(),
					localTransactionId, 
					localTransactionId, 
					request.getTransaction(), 
					(System.currentTimeMillis() - startTime)));
			return ResponseEntity.created(location).body(response);
		}catch (Exception ex) {
			response.setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.setResponseDescription("Ha ocurrido un problema. Para mayor información consulte el log del servicio.");
			
			logger.error(new EventLog(
					"Error en " + requestPath +": "+ ExceptionUtils.getStackTrace(ex),
					request.getData().getVendorId(),
					httpRequest.getRemoteHost()+":"+httpRequest.getRemotePort(),
					localTransactionId, 
					localTransactionId, 
					request.getTransaction(), 
					(System.currentTimeMillis() - startTime)));
			logger.end(new EventLog(
					"Fin de " + requestPath , 
					request.getData().getVendorId(),
					httpRequest.getRemoteHost()+":"+httpRequest.getRemotePort(),
					localTransactionId, 
					localTransactionId, 
					request.getTransaction(), 
					(System.currentTimeMillis() - startTime)));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/vendors/{id}",
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<UnicomerVendor.ResponseVendorMessage> deleteVendor(@PathVariable String id){
		String requestPath = httpRequest.getMethod() + " " + httpRequest.getRequestURI();
		String localTransactionId=UUID.randomUUID().toString();
		long startTime = System.currentTimeMillis();
		
		UnicomerVendor.ResponseVendorMessage response = new UnicomerVendor.ResponseVendorMessage();
		response.setDate(Calendar.getInstance().getTime());
		response.setTransactionId(localTransactionId);
		response.setResponseCode(100);
		response.setResponseDescription("Transaccion exitosa");
		try{
			logger.start(new EventLog(
					"Inicio de " + requestPath, 
					id,
					httpRequest.getRemoteAddr()+":"+httpRequest.getRemotePort(), 
					localTransactionId, 
					localTransactionId, 
					"", 
					(System.currentTimeMillis() - startTime)));
			
			if(vendorService.exists(id, localTransactionId, startTime)){
				vendorService.delete(localTransactionId, startTime, id);
			}else{
				response.setResponseCode(HttpStatus.NOT_FOUND.value());
				response.setResponseDescription("No se encontró registro para actualizar");
				
				logger.error(new EventLog(
						"Error en " + requestPath +": Datos no encontrados",
						id,
						httpRequest.getRemoteHost()+":"+httpRequest.getRemotePort(),
						localTransactionId, 
						localTransactionId, 
						"", 
						(System.currentTimeMillis() - startTime)));
				logger.end(new EventLog(
						"Fin de " + requestPath,
						id,
						httpRequest.getRemoteHost()+":"+httpRequest.getRemotePort(),
						localTransactionId, 
						localTransactionId, 
						"", 
						(System.currentTimeMillis() - startTime)));
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
			}
			
			logger.end(new EventLog(
					"Fin de " + requestPath,
					id,
					httpRequest.getRemoteHost()+":"+httpRequest.getRemotePort(),
					localTransactionId, 
					localTransactionId, 
					"",
					(System.currentTimeMillis() - startTime)));
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
		}catch (Exception ex) {
			response.setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.setResponseDescription("Ha ocurrido un problema. Para mayor información consulte el log del servicio.");
			
			logger.error(new EventLog(
					"Error en " + requestPath +": "+ ExceptionUtils.getStackTrace(ex),
					id,
					httpRequest.getRemoteHost()+":"+httpRequest.getRemotePort(),
					localTransactionId, 
					localTransactionId, 
					"", 
					(System.currentTimeMillis() - startTime)));
			logger.end(new EventLog(
					"Fin de " + requestPath , 
					id,
					httpRequest.getRemoteHost()+":"+httpRequest.getRemotePort(),
					localTransactionId, 
					localTransactionId, 
					"", 
					(System.currentTimeMillis() - startTime)));
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}
	
}
