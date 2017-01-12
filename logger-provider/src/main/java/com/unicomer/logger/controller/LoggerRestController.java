/**
 * 
 */
package com.unicomer.logger.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.unicomer.demo.common.entity.TransactionLogEndTrace;
import com.unicomer.demo.common.entity.TransactionLogInfoTrace;
import com.unicomer.demo.common.header.ResponseHeader;
import com.unicomer.logger.domain.TransactionLogDomain;
import com.unicomer.logger.service.LoggerService;

/**
 * @author carlosj_rodriguez
 *
 */
@RestController
public class LoggerRestController {
	private final Logger logger = LoggerFactory.getLogger(LoggerRestController.class);
	
	@Autowired LoggerService loggerService;
	
	@RequestMapping(method = RequestMethod.POST, value = "/logs/start",
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
			consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseHeader startTrace(@RequestBody TransactionLogInfoTrace request, 
			HttpServletRequest servletRequest, HttpServletResponse servletResponse,
			@RequestHeader(name="X-Forwarded-For", defaultValue="none", required=false) String forwardedFor) {
		ResponseHeader response = new ResponseHeader();
		try{
			if (!forwardedFor.equals("none")){
				request.setServerLocation(forwardedFor);
			}else{
				request.setServerLocation(servletRequest.getRemoteAddr());
			}
			loggerService.saveStartTrace(request);
			
			servletResponse.setStatus(HttpServletResponse.SC_CREATED);
			response.setResponseCode(0);
			response.setResponseDescription("Transaccion exitosa");
			response.setServiceCode(0);
			response.setServiceDescription("Transaccion exitosa");
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			servletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.setResponseCode(500);
			response.setResponseDescription("Ha ocurrido un problema");
			response.setServiceCode(500);
			response.setServiceDescription(e.getMessage());
		}
		
		response.setTransactionId(UUID.randomUUID().toString());
		response.setDate(Calendar.getInstance().getTime());
		return response;
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/logs/end",
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
			consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseHeader endTrace(@RequestBody TransactionLogEndTrace request, 
			HttpServletRequest servletRequest, HttpServletResponse servletResponse,
			@RequestHeader(name="X-Forwarded-For", defaultValue="none", required=false) String forwardedFor) {
		ResponseHeader response = new ResponseHeader();
		try{
			if (!forwardedFor.equals("none")){
				request.setServerLocation(forwardedFor);
			}else{
				request.setServerLocation(servletRequest.getRemoteAddr());
			}
			loggerService.saveEndTrace(request);
			
			servletResponse.setStatus(HttpServletResponse.SC_CREATED);
			response.setResponseCode(0);
			response.setResponseDescription("Transaccion exitosa");
			response.setServiceCode(0);
			response.setServiceDescription("Transaccion exitosa");
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			servletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.setResponseCode(500);
			response.setResponseDescription("Ha ocurrido un problema");
			response.setServiceCode(500);
			response.setServiceDescription(e.getMessage());
		}
		
		response.setTransactionId(UUID.randomUUID().toString());
		response.setDate(Calendar.getInstance().getTime());
		return response;
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/logs/info",
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
			consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseHeader infoTrace(@RequestBody TransactionLogInfoTrace request, 
			HttpServletRequest servletRequest, HttpServletResponse servletResponse,
			@RequestHeader(name="X-Forwarded-For", defaultValue="none", required=false) String forwardedFor) {
		ResponseHeader response = new ResponseHeader();
		try{
			if (!forwardedFor.equals("none")){
				request.setServerLocation(forwardedFor);
			}else{
				request.setServerLocation(servletRequest.getRemoteAddr());
			}
			loggerService.saveInfoTrace(request);
			
			servletResponse.setStatus(HttpServletResponse.SC_CREATED);
			response.setResponseCode(0);
			response.setResponseDescription("Transaccion exitosa");
			response.setServiceCode(0);
			response.setServiceDescription("Transaccion exitosa");
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			servletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.setResponseCode(500);
			response.setResponseDescription("Ha ocurrido un problema");
			response.setServiceCode(500);
			response.setServiceDescription(e.getMessage());
		}
		
		response.setTransactionId(UUID.randomUUID().toString());
		response.setDate(Calendar.getInstance().getTime());
		return response;
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/logs/error",
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
			consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseHeader errorTrace(@RequestBody TransactionLogInfoTrace request, 
			HttpServletRequest servletRequest, HttpServletResponse servletResponse,
			@RequestHeader(name="X-Forwarded-For", defaultValue="none", required=false) String forwardedFor) {
		ResponseHeader response = new ResponseHeader();
		try{
			if (!forwardedFor.equals("none")){
				request.setServerLocation(forwardedFor);
			}else{
				request.setServerLocation(servletRequest.getRemoteAddr());
			}
			loggerService.saveErrorTrace(request);
			
			servletResponse.setStatus(HttpServletResponse.SC_CREATED);
			response.setResponseCode(0);
			response.setResponseDescription("Transaccion exitosa");
			response.setServiceCode(0);
			response.setServiceDescription("Transaccion exitosa");
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			servletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.setResponseCode(500);
			response.setResponseDescription("Ha ocurrido un problema");
			response.setServiceCode(500);
			response.setServiceDescription(e.getMessage());
		}
		
		response.setTransactionId(UUID.randomUUID().toString());
		response.setDate(Calendar.getInstance().getTime());
		return response;
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/logs",
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
			consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public List<TransactionLogDomain> startTrace(HttpServletResponse servletResponse, Pageable pageable) {
		List<TransactionLogDomain> response = new ArrayList<TransactionLogDomain>();
		try{
			if(pageable.getPageSize()==20 && pageable.getPageNumber()==0){
				logger.info("loggerService.findAll()");
				response=loggerService.findAll();
			}else{
				logger.info("loggerService.listAllByPage("+pageable.toString()+")");
				response=loggerService.listAllByPage(pageable);
			}
			servletResponse.setStatus(HttpServletResponse.SC_OK);
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			servletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/logs/{globalReferenceId}",
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
			consumes={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public List<TransactionLogDomain> startTrace(@PathVariable(name="globalReferenceId") String globalReferenceId, HttpServletResponse servletResponse) {
		List<TransactionLogDomain> response = new ArrayList<TransactionLogDomain>();
		try{
			logger.info("globalReferenceId="+globalReferenceId);
			response=loggerService.findByGlobalReferenceId(globalReferenceId);
			servletResponse.setStatus(HttpServletResponse.SC_OK);
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			servletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		return response;
	}
	
}
