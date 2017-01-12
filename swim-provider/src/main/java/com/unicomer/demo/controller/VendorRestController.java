package com.unicomer.demo.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.unicomer.demo.domain.SwimVendor;
import com.unicomer.demo.domain.SwimVendor.ResponseMessage;
import com.unicomer.demo.service.VendorService;

@RestController
public class VendorRestController {
	@Autowired VendorService vendorService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/vendors/all",
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public SwimVendor.ResponseMessage getVendors(HttpServletResponse servletResponse) {
		servletResponse.setStatus(HttpServletResponse.SC_OK);
		ResponseMessage response = new ResponseMessage();
		
		try{
			List<SwimVendor> vendorList = new ArrayList<SwimVendor>();
			Iterator<SwimVendor> it = vendorService.findAll().iterator();
			while (it.hasNext()) {
				SwimVendor vendor = it.next();
				vendor.add(linkTo(methodOn(VendorRestController.class).getVendorById(vendor.getVendorId(), servletResponse)).withSelfRel());
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
		}
		
		response.setTransactionId(UUID.randomUUID().toString());
		response.setDate(Calendar.getInstance().getTime());		
		return response;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/vendors",
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public SwimVendor.ResponseMessage getVendorsPageable(HttpServletResponse servletResponse, Pageable pageable) {
		servletResponse.setStatus(HttpServletResponse.SC_OK);
		ResponseMessage response = new ResponseMessage();
		
		try{
			List<SwimVendor> vendorList = new ArrayList<SwimVendor>();
			Iterator<SwimVendor> it;
			
			if(pageable.getPageSize()==20 && pageable.getPageNumber()==0){
				System.out.println("vendorService.findAll()");
				it = vendorService.findAll().iterator();
			}else{
				System.out.println("vendorService.listAllByPage()");
				it = vendorService.listAllByPage(pageable).iterator();
			}
			
			while (it.hasNext()) {
				SwimVendor vendor = it.next();
				vendor.add(linkTo(methodOn(VendorRestController.class).getVendorById(vendor.getVendorId(), servletResponse)).withSelfRel());
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
		}
		
		response.setTransactionId(UUID.randomUUID().toString());
		response.setDate(Calendar.getInstance().getTime());		
		return response;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/vendors",
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
			consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseMessage addVendor(@RequestBody SwimVendor.RequestMessage request, HttpServletResponse servletResponse) {
		servletResponse.setStatus(HttpServletResponse.SC_CREATED);
		ResponseMessage response = new ResponseMessage();
		
		try{
			SwimVendor vendorResponse = vendorService.save(request.getData());
			ArrayList<SwimVendor> vendorList = new ArrayList<SwimVendor>();
			
			if (vendorResponse!=null){
				vendorResponse.add(linkTo(methodOn(VendorRestController.class).getVendorById(vendorResponse.getVendorId(), null)).withSelfRel());
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
		}
		
		if(request.getToken().isEmpty()){
			response.setTransactionId(UUID.randomUUID().toString());
		}else{
			response.setTransactionId(request.getToken());
		}
		
		response.setDate(Calendar.getInstance().getTime());		
		return response;
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/vendors/{id}",
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
			consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseMessage updateVendor(@RequestBody SwimVendor.RequestMessage request, @PathVariable String id, HttpServletResponse servletResponse) {
		servletResponse.setStatus(HttpServletResponse.SC_CREATED);
		ResponseMessage response = new ResponseMessage();
		
		try{
			if (vendorService.exists(id)){
				SwimVendor vendorResponse = vendorService.save(request.getData());
				ArrayList<SwimVendor> vendorList = new ArrayList<SwimVendor>();
				vendorResponse.add(linkTo(methodOn(VendorRestController.class).getVendorById(vendorResponse.getVendorId(), null)).withSelfRel());
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
		}
		
		if(request.getToken().isEmpty()){
			response.setTransactionId(UUID.randomUUID().toString());
		}else{
			response.setTransactionId(request.getToken());
		}
		
		response.setDate(Calendar.getInstance().getTime());
		return response;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/vendors/{id}",
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
			consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseMessage getVendorById(@PathVariable String id, HttpServletResponse servletResponse) {
		servletResponse.setStatus(HttpServletResponse.SC_OK);
		ResponseMessage response = new ResponseMessage();
		SwimVendor vendor=vendorService.findOne(id); 
		
		if (vendor!=null){
			ArrayList<SwimVendor> vendorList = new ArrayList<SwimVendor>();
			vendor.add(linkTo(methodOn(VendorRestController.class).getVendorById(vendor.getVendorId(), servletResponse)).withSelfRel());
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
		
		response.setTransactionId(UUID.randomUUID().toString());
		response.setDate(Calendar.getInstance().getTime());
		return response;
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/vendors/{id}")
	public ResponseMessage deleteVendor(@PathVariable String id, HttpServletResponse servletResponse) {
		servletResponse.setStatus(HttpServletResponse.SC_NO_CONTENT);
		ResponseMessage response = new ResponseMessage();
		
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
		}
		
		response.setTransactionId(UUID.randomUUID().toString());
		response.setDate(Calendar.getInstance().getTime());
		return response;
	}
}
