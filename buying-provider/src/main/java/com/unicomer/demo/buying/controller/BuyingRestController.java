package com.unicomer.demo.buying.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import com.unicomer.demo.buying.dao.LoggerClient;
import com.unicomer.demo.buying.dao.SwimClient;
import com.unicomer.demo.buying.service.VendorService;
import com.unicomer.demo.buying.service.VendorServiceImpl;
import com.unicomer.demo.buying.util.PropertiesLoader;
import com.unicomer.demo.common.entity.TransactionLogEndTrace;
import com.unicomer.demo.common.entity.TransactionLogInfoTrace;
import com.unicomer.demo.common.entity.UnicomerVendor;
import com.unicomer.demo.common.entity.UnicomerVendor.ResponseVendorMessage;

@Path("/")
@Consumes({"application/json", "application/xml"})
@Produces({"application/json", "application/xml"})
public class BuyingRestController {
	private static PropertiesLoader properties = PropertiesLoader.getInstance();
//	private static final String APP_NAME = "buy-provider@BuyingRestController";
	private String applicationName = properties.getProperty("spring.application.name");
	private VendorService vendorService = new VendorServiceImpl();
	private static LoggerClient logger = LoggerClient.getInstance();
	private static SwimClient swim = SwimClient.getInstance();
	
	@GET
	@Path("/vendors")
	public UnicomerVendor.ResponseVendorMessage getVendors(@Context HttpServletRequest servletRequest, @Context HttpServletResponse servletResponse) {
		String localTransactionId=UUID.randomUUID().toString();
		long startTime = System.currentTimeMillis();		
		logger.start(new TransactionLogInfoTrace(
				"",
				"",
				servletRequest.getRemoteAddr(),
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
			Iterator<UnicomerVendor> it = vendorService.findAll(localTransactionId, startTime).iterator();
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
			logger.error(new TransactionLogInfoTrace(
					"",
					e.getCause().getMessage(),
					servletRequest.getRemoteAddr(),
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
		logger.end(new TransactionLogEndTrace(
				"Registros recuperados: " + response.getData().size(),
				"",
				servletRequest.getRemoteAddr(),
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
		
	@GET
	@Path("/vendors/{id}")
	public UnicomerVendor.ResponseVendorMessage getVendor(@PathParam("id") String id, @Context HttpServletRequest servletRequest, @Context HttpServletResponse servletResponse){
		String localTransactionId=UUID.randomUUID().toString();
		long startTime = System.currentTimeMillis();		
		logger.start(new TransactionLogInfoTrace(
				"",
				"",
				servletRequest.getRemoteAddr(),
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
			logger.info(localTransactionId + " - Inicio de búsqueda en VendorService " + (System.currentTimeMillis() - startTime) + " milisegundos");
			
			
			
			vendorsResult.add(vendorService.findOne(id, localTransactionId, startTime));
			logger.info(localTransactionId + " - Fin de búsqueda en VendorService " + (System.currentTimeMillis() - startTime) + " milisegundos");
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
			
			logger.error(new TransactionLogInfoTrace(
					"",
					e.getCause().getMessage(),
					servletRequest.getRemoteAddr(),
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
		logger.end(new TransactionLogEndTrace(
				response.toString(),
				"",
				servletRequest.getRemoteAddr(),
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
	
	
	@POST
	@Path("/vendors")
	public UnicomerVendor.ResponseVendorMessage newVendor(UnicomerVendor.RequestVendorMessage request, @Context HttpServletRequest servletRequest, @Context HttpServletResponse servletResponse) {
		String localTransactionId=UUID.randomUUID().toString();
		
		long startTime = System.currentTimeMillis();		
		logger.start(new TransactionLogInfoTrace(
				"",
				"",
				servletRequest.getRemoteAddr(),
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
			logger.error(new TransactionLogInfoTrace(
					"",
					e.getCause().getMessage(),
					servletRequest.getRemoteAddr(),
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
		logger.end(new TransactionLogEndTrace(
				response.toString(),
				"",
				servletRequest.getRemoteAddr(),
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
	
	
	@PUT
	@Path("/vendors/{id}")
	public UnicomerVendor.ResponseVendorMessage updateVendor(@BeanParam UnicomerVendor.RequestVendorMessage request, @PathParam("id") String id, 
			@Context HttpServletRequest servletRequest, @Context HttpServletResponse servletResponse) {
		String localTransactionId=UUID.randomUUID().toString();
		
		long startTime = System.currentTimeMillis();		
		logger.start(new TransactionLogInfoTrace(
				request.toString(),
				id,
				servletRequest.getRemoteAddr(),
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
		List<UnicomerVendor> vendorsResult = new ArrayList<UnicomerVendor>();
		try{
			vendorsResult.add(vendorService.save(localTransactionId, request.getData()));
			
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
			logger.error(new TransactionLogInfoTrace(
					id,
					e.getCause().getMessage(),
					servletRequest.getRemoteAddr(),
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
		logger.end(new TransactionLogEndTrace(
				response.toString(),
				id,
				servletRequest.getRemoteAddr(),
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
	
	@DELETE
	@Path("/vendors/{id}")
	public UnicomerVendor.ResponseVendorMessage deleteVendor(@PathParam("id") String id, 
			@Context HttpServletRequest servletRequest, @Context HttpServletResponse servletResponse) {
		String localTransactionId=UUID.randomUUID().toString();
		
		long startTime = System.currentTimeMillis();		
		logger.start(new TransactionLogInfoTrace(
				id,
				id,
				servletRequest.getRemoteAddr(),
				"Inicio de DELETE /vendors/"+id, 
				localTransactionId, 
				localTransactionId, 
				"",
				"v1", 
				applicationName
			)
		);
		
		servletResponse.setStatus(HttpServletResponse.SC_NO_CONTENT);
		
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
			logger.error(new TransactionLogInfoTrace(
					id,
					e.getCause().getMessage(),
					servletRequest.getRemoteAddr(),
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
		logger.end(new TransactionLogEndTrace(
				response.toString(),
				id,
				servletRequest.getRemoteAddr(),
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
