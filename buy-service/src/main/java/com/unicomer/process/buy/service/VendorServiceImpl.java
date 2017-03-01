/**
 * 
 */
package com.unicomer.process.buy.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.unicomer.demo.common.entity.UnicomerVendor;
import com.unicomer.demo.ri.domain.RiVendor;
import com.unicomer.demo.swim.domain.SwimVendor;
import com.unicomer.process.buy.client.LoggerClient;
import com.unicomer.process.buy.client.RiClient;
import com.unicomer.process.buy.client.SwimClient;
import com.unicomer.process.buy.entity.CommentLog;

/**
 * @author carlosj_rodriguez
 *
 */
@Service("vendorService")
public class VendorServiceImpl implements VendorService {
	
	@Value("${spring.application.name}")
	private String applicationName;
	@Value("${swim.date.format.pattern}")
	private String dateFormatPattern;
	@Value("${swim.time.format.pattern}")
	private String timeFormatPattern;
	
	@Autowired SwimClient swim;
	@Autowired RiClient ri;
	@Autowired LoggerClient logger;
	
	/* (non-Javadoc)
	 * @see com.unicomer.service.buy.service.VendorService#findAll(java.lang.String, long)
	 */
	@Override
	public List<UnicomerVendor> findAll(String transactionId, long startTime) {
		List<UnicomerVendor> result = new ArrayList<UnicomerVendor>();
		try{
			logger.info(new CommentLog("Inicio de busqueda de proveedores en Swim", transactionId, (System.currentTimeMillis() - startTime)));
			SwimVendor.ResponseMessage response = swim.getAllVendors(transactionId);
			logger.info(new CommentLog("Fin de busqueda de proveedores en Swim. " + response.getResponseDescription() , transactionId, (System.currentTimeMillis() - startTime)));
			
			if(response.getData()!=null && response.getData().size()>0){
				logger.info(new CommentLog("Respuesta en Swim: " + response.getData().size() + " registro(s)", transactionId, (System.currentTimeMillis() - startTime)));
				for(SwimVendor vendor : response.getData()){
					result.add(SwimVendorToUnicomerVendor(vendor));
				}
				logger.info(new CommentLog("VendorsService ha transformado la respuesta de Swim en estandar Unicomer", transactionId, (System.currentTimeMillis() - startTime)));
			}
		}catch (Exception ex) {
			logger.error(new CommentLog("Error buscando datos en Swim: " + ExceptionUtils.getStackTrace(ex), 
					transactionId, (System.currentTimeMillis() - startTime)));
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see com.unicomer.service.buy.service.VendorService#save(java.lang.String, com.unicomer.demo.common.entity.UnicomerVendor)
	 */
	@Override
	public UnicomerVendor save(String transactionId, long startTime, UnicomerVendor vendor) {
		UnicomerVendor result = new UnicomerVendor();
		//SWIM
		try{
			logger.info(new CommentLog("Inicio de envío de datos a Swim", transactionId, (System.currentTimeMillis() - startTime)));
			SwimVendor.RequestMessage request = new SwimVendor.RequestMessage();
			request.setApplication(applicationName);
			request.setTransaction(transactionId);
			request.setData(UnicomerVendorToSwimVendor(vendor));
			SwimVendor.ResponseMessage response = swim.addVendor(transactionId, request);
			logger.info(new CommentLog("Fin de envío de datos a Swim. " + response.getResponseDescription() , transactionId, (System.currentTimeMillis() - startTime)));
			
			if(response.getData()!=null && response.getData().size()>0){
				logger.info(new CommentLog("Respuesta en Swim: " + response.getData().size() + " registro(s)", transactionId, (System.currentTimeMillis() - startTime)));
				result = SwimVendorToUnicomerVendor(response.getData().get(0));	
			}
			
			logger.info(new CommentLog("VendorsService ha transformado la respuesta de Swim en estandar Unicomer", transactionId, (System.currentTimeMillis() - startTime)));
		}catch (Exception ex) {
			logger.error(new CommentLog("Error guardando datos en Swim: " + ExceptionUtils.getStackTrace(ex), 
					transactionId, (System.currentTimeMillis() - startTime)));
		}
		
		//RI
		try{
			logger.info(new CommentLog("Inicio de envío de datos a RI", transactionId, (System.currentTimeMillis() - startTime)));
			RiVendor response = ri.addVendor(transactionId, UnicomerVendorToRiVendor(vendor));
			logger.info(new CommentLog("Fin de envío de datos a RI. " + response.getId(), transactionId, (System.currentTimeMillis() - startTime)));
		}catch (Exception ex) {
			logger.error(new CommentLog("Error guardando datos en RI: " + ExceptionUtils.getStackTrace(ex), 
					transactionId, (System.currentTimeMillis() - startTime)));
		}
		
		return result;
	}
	
	
	/* (non-Javadoc)
	 * @see com.unicomer.service.buy.service.VendorService#update(java.lang.String, com.unicomer.demo.common.entity.UnicomerVendor)
	 */
	@Override
	public UnicomerVendor update(String transactionId, long startTime, UnicomerVendor vendor, String id) {
		UnicomerVendor result = new UnicomerVendor();
		//SWIM
		try{
			logger.info(new CommentLog("Inicio de envío de datos a Swim", transactionId, (System.currentTimeMillis() - startTime)));
			SwimVendor.RequestMessage request = new SwimVendor.RequestMessage();
			request.setApplication(applicationName);
			request.setTransaction(transactionId);
			request.setData(UnicomerVendorToSwimVendor(vendor));
			SwimVendor.ResponseMessage response = swim.updateVendor(transactionId, request, id);
			logger.info(new CommentLog("Fin de envío de datos a Swim. " + response.getResponseDescription() , transactionId, (System.currentTimeMillis() - startTime)));
			
			if(response.getData()!=null && response.getData().size()>0){
				logger.info(new CommentLog("Respuesta en Swim: " + response.getData().size() + " registro(s)", transactionId, (System.currentTimeMillis() - startTime)));
				result = SwimVendorToUnicomerVendor(response.getData().get(0));	
			}
			
			logger.info(new CommentLog("VendorsService ha transformado la respuesta de Swim en estandar Unicomer", transactionId, (System.currentTimeMillis() - startTime)));
		}catch (Exception ex) {
			logger.error(new CommentLog("Error guardando datos en Swim: " + ExceptionUtils.getStackTrace(ex), 
					transactionId, (System.currentTimeMillis() - startTime)));
		}
		
		//RI
		try{
			logger.info(new CommentLog("Inicio de envío de datos a RI", transactionId, (System.currentTimeMillis() - startTime)));
			RiVendor response = ri.updateVendor(transactionId, UnicomerVendorToRiVendor(vendor), id);
			logger.info(new CommentLog("Fin de envío de datos a RI. " + response.getId(), transactionId, (System.currentTimeMillis() - startTime)));
		}catch (Exception ex) {
			logger.error(new CommentLog("Error guardando datos en RI: " + ExceptionUtils.getStackTrace(ex), 
					transactionId, (System.currentTimeMillis() - startTime)));
		}
		
		return result;
	}

	/* (non-Javadoc)
	 * @see com.unicomer.service.buy.service.VendorService#exists(java.lang.String, java.lang.String, long)
	 */
	@Override
	public boolean exists(String id, String transactionId, long startTime) {
		try{
			logger.info(new CommentLog("Inicio de busqueda de proveedor en Swim", transactionId, (System.currentTimeMillis() - startTime)));
			SwimVendor.ResponseMessage response = swim.getVendor(transactionId, id);
			logger.info(new CommentLog("Fin de busqueda de proveedor en Swim. " + response.getResponseDescription() , transactionId, (System.currentTimeMillis() - startTime)));
			
			if(response.getData()!=null && response.getData().size()>0){
				logger.info(new CommentLog("Respuesta en Swim: " + response.getData().size() + " registro(s)", transactionId, (System.currentTimeMillis() - startTime)));
				return true;
			}else{
				logger.info(new CommentLog("No se ha encontrado un Vendor con id " + id, transactionId, (System.currentTimeMillis() - startTime)));
				return false;
			}
		}catch (Exception ex) {
			logger.error(new CommentLog("Error buscando datos en Swim: " + ExceptionUtils.getStackTrace(ex), 
					transactionId, (System.currentTimeMillis() - startTime)));
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see com.unicomer.service.buy.service.VendorService#findOne(java.lang.String, java.lang.String, long)
	 */
	@Override
	public UnicomerVendor findOne(String id, String transactionId, long startTime) {
		UnicomerVendor result = new UnicomerVendor();
		try{
			logger.info(new CommentLog("Inicio de busqueda de proveedor en Swim", transactionId, (System.currentTimeMillis() - startTime)));
			SwimVendor.ResponseMessage response = swim.getVendor(transactionId, id);
			logger.info(new CommentLog("Fin de busqueda de proveedor en Swim. " + response.getResponseDescription() , transactionId, (System.currentTimeMillis() - startTime)));
			
			if(response.getData()!=null && response.getData().size()>0){
				logger.info(new CommentLog("Respuesta en Swim: " + response.getData().size() + " registro(s)", transactionId, (System.currentTimeMillis() - startTime)));
				result = SwimVendorToUnicomerVendor(response.getData().get(0));	
			}
			
			logger.info(new CommentLog("VendorsService ha transformado la respuesta de Swim en estandar Unicomer", transactionId, (System.currentTimeMillis() - startTime)));
		}catch (Exception ex) {
			logger.error(new CommentLog("Error buscando datos en Swim: " + ExceptionUtils.getStackTrace(ex), 
					transactionId, (System.currentTimeMillis() - startTime)));
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see com.unicomer.service.buy.service.VendorService#delete(java.lang.String, java.lang.String)
	 */
	@Override
	public void delete(String transactionId, long startTime, String id) {
		//SWIM
		try{
			logger.info(new CommentLog("Inicio de borrado de datos en Swim", transactionId, (System.currentTimeMillis() - startTime)));
			swim.deleteVendor(transactionId, id);
		}catch (Exception ex) {
			logger.error(new CommentLog("Error borrando datos en Swim: " + ExceptionUtils.getStackTrace(ex), 
					transactionId, (System.currentTimeMillis() - startTime)));
		}
		
		//RI
		try{
			logger.info(new CommentLog("Inicio de borrado de datos a RI", transactionId, (System.currentTimeMillis() - startTime)));
			ri.deleteVendor(transactionId, id);
		}catch (Exception ex) {
			logger.error(new CommentLog("Error borrando datos en RI: " + ExceptionUtils.getStackTrace(ex), 
					transactionId, (System.currentTimeMillis() - startTime)));
		}
	}
	
	private SwimVendor UnicomerVendorToSwimVendor(UnicomerVendor vendor){
		SwimVendor swimVendor = new SwimVendor();
		swimVendor.setVendorId(vendor.getVendorId());
		swimVendor.setName(vendor.getName());
		swimVendor.setAddress(vendor.getAddress());
		swimVendor.setCountry(vendor.getCountry());
		swimVendor.setCity(vendor.getCity());
		swimVendor.setState(vendor.getState());
		swimVendor.setZipCode(vendor.getZipCode());
		swimVendor.setPostalCode(vendor.getPostalCode());
		swimVendor.setPhoneNumber(vendor.getRepresentativePhone());
		swimVendor.setFaxNumber(vendor.getRepresentativeFax());
		swimVendor.setPriority(vendor.getPriority().toString());
		swimVendor.setDunsNumber(vendor.getDunsNumber());
		swimVendor.setAbbreviation(vendor.getAbreviation());
		swimVendor.setContactName(vendor.getContactName());
		swimVendor.setType(vendor.getType());
		swimVendor.setSocialSecurityNumber(vendor.getSocialSecurityNumber());
		swimVendor.setTaxId(vendor.getTaxId());
		swimVendor.setTerms1("090");
		swimVendor.setTerms2("");
		swimVendor.setTerms3("");
		swimVendor.setFactor(vendor.getFactor().toString());
		swimVendor.setFobPoint(vendor.getFreeOnBoardPoint());
		swimVendor.setShipping(vendor.getShippingPoint());
		swimVendor.setShipVia(vendor.getShippingVia());
		swimVendor.setPhoneMfg(" ");
		swimVendor.setFaxMfg(" ");
		swimVendor.setGlobalFreight(vendor.getGlobalFreightPerc().toString());
		swimVendor.setGlobalDiscount(vendor.getGlobalDiscountPerc().toString());
		swimVendor.setMinShip(vendor.getMinShip().toString());
		swimVendor.setMinShipUnit(vendor.getMinShipUnit().toString());
		swimVendor.setReviewCycle(vendor.getReviewCycle());
		swimVendor.setTelephone(vendor.getRepresentativePhone());
		swimVendor.setLetterCreditVendor("0");
		swimVendor.setReturnVendorName(vendor.getReturnVendorName());
		swimVendor.setReturnAddress(vendor.getReturnAddress());
		swimVendor.setReturnCountry(vendor.getReturnCountry());
		swimVendor.setReturnCity(vendor.getReturnCity());
		swimVendor.setReturnState(vendor.getReturnState());
		swimVendor.setRemitVendorName(vendor.getRemitVendorName());
		swimVendor.setRemitAddress(vendor.getRemitAddress());
		swimVendor.setRemitCountry(vendor.getRemitCountry());
		swimVendor.setRemitCity(vendor.getRemitCity());
		swimVendor.setRemitState(vendor.getRemitState());
		swimVendor.setReturnPortalCode(vendor.getReturnPortalCode());
		swimVendor.setRemitPostalCode(vendor.getRemitPostalCode());
		swimVendor.setReturnZipCode(vendor.getReturnZipCode());
		swimVendor.setRemitZipCode(vendor.getRemitZipCode());
		swimVendor.setEmail(vendor.getRemitEmail());
		swimVendor.setRemitEmail(vendor.getRemitEmail());
		swimVendor.setReturnEmail(vendor.getReturnEmail());
		swimVendor.setStatus(vendor.getStatus());
		swimVendor.setNit(vendor.getDunsNumber());
		swimVendor.setWmsVendor(" ");
		swimVendor.setVendorStorage(" ");
		swimVendor.setFiscalReg(vendor.getFiscalReg());
		swimVendor.setLastChangeDate(new SimpleDateFormat(dateFormatPattern).format(Calendar.getInstance().getTime()));
		swimVendor.setLastChangeTime(new SimpleDateFormat(timeFormatPattern).format(Calendar.getInstance().getTime()));
		swimVendor.setLastChangeUser(applicationName);
		
		return swimVendor;
	}
	
	private UnicomerVendor SwimVendorToUnicomerVendor(SwimVendor vendor){
		UnicomerVendor unicomerVendor = new UnicomerVendor();
		unicomerVendor.setVendorId(vendor.getVendorId());
		unicomerVendor.setName(vendor.getName());
		unicomerVendor.setAddress(vendor.getAddress());
		unicomerVendor.setCountry(vendor.getCountry());
		unicomerVendor.setCity(vendor.getCity());
		unicomerVendor.setState(vendor.getState());
		unicomerVendor.setZipCode(vendor.getZipCode());
		unicomerVendor.setPostalCode(vendor.getPostalCode());
		unicomerVendor.setRepresentativePhone(vendor.getPhoneNumber());
		unicomerVendor.setRepresentativeFax(vendor.getFaxNumber());
		unicomerVendor.setPriority(0);
		unicomerVendor.setDunsNumber(vendor.getDunsNumber());
		unicomerVendor.setAbreviation(vendor.getAbbreviation());
		unicomerVendor.setContactName(vendor.getContactName());
		unicomerVendor.setType(vendor.getType());
		unicomerVendor.setSocialSecurityNumber(vendor.getSocialSecurityNumber());
		unicomerVendor.setTaxId(vendor.getTaxId());
		unicomerVendor.setTerms(vendor.getTerms1()+vendor.getTerms2()+vendor.getTerms3());
		unicomerVendor.setFactor(0);
		unicomerVendor.setFreeOnBoardPoint(vendor.getFobPoint());
		unicomerVendor.setShippingPoint(vendor.getShipping());
		unicomerVendor.setShippingVia(vendor.getShipVia());
		unicomerVendor.setGlobalFreightPerc(BigDecimal.ZERO);
		unicomerVendor.setGlobalDiscountPerc(BigDecimal.ZERO);
		unicomerVendor.setMinShip(0);
		unicomerVendor.setMinShipUnit(0);
		unicomerVendor.setReviewCycle(vendor.getReviewCycle());
		unicomerVendor.setReturnVendorName(vendor.getReturnVendorName());
		unicomerVendor.setReturnAddress(vendor.getReturnAddress());
		unicomerVendor.setReturnCountry(vendor.getReturnCountry());
		unicomerVendor.setReturnCity(vendor.getReturnCity());
		unicomerVendor.setReturnState(vendor.getReturnState());
		unicomerVendor.setReturnPortalCode(vendor.getReturnPortalCode());
		unicomerVendor.setReturnZipCode(vendor.getReturnZipCode());
		unicomerVendor.setReturnEmail(vendor.getReturnEmail());
		unicomerVendor.setRemitVendorName(vendor.getRemitVendorName());
		unicomerVendor.setRemitAddress(vendor.getRemitAddress());
		unicomerVendor.setRemitCountry(vendor.getRemitCountry());
		unicomerVendor.setRemitCity(vendor.getRemitCity());
		unicomerVendor.setRemitState(vendor.getRemitState());
		unicomerVendor.setRemitPostalCode(vendor.getRemitPostalCode());
		unicomerVendor.setRemitZipCode(vendor.getRemitZipCode());
		unicomerVendor.setRemitEmail(vendor.getRemitEmail());
		unicomerVendor.setStatus(vendor.getStatus());
		unicomerVendor.setFiscalReg(vendor.getFiscalReg());
		
		return unicomerVendor;
	}
	
	private RiVendor UnicomerVendorToRiVendor(UnicomerVendor vendor){
		RiVendor riVendor = new RiVendor();
		riVendor.setStatusCode(vendor.getStatus());
		riVendor.setFlag(" ");
//		riVendor.setId(String.format("%06d", vendor.getVendorId()));
		riVendor.setId(vendor.getVendorId());
		riVendor.setName(vendor.getName());
		riVendor.setAddressLine1(vendor.getAddress());
		riVendor.setAddressLine2(" ");
		riVendor.setCity(vendor.getCity());
		riVendor.setState(vendor.getState());
		riVendor.setCountry(vendor.getCountry());
		riVendor.setZipCode(vendor.getZipCode());
		riVendor.setPostalCode(vendor.getPostalCode());
		riVendor.setPhone(vendor.getRepresentativePhone() );
		riVendor.setTermPercent(vendor.getTerms());
		riVendor.setDaysInTerm(" ");
		riVendor.setTerms(vendor.getTerms());
		riVendor.setDunsNumber(vendor.getDunsNumber());
		riVendor.setBuyer(" ");
		riVendor.setPurchasesYTD("0.00");
		riVendor.setPurchasesLTD("0.00");
		riVendor.setSortCode(" ");
		riVendor.setSsnFirstDigits("0");
		riVendor.setSsnSecondDigits("0");
		riVendor.setSsnLastDigits("0");
		riVendor.setFactor(vendor.getFactor().toString());
		riVendor.setPaymentMethod(" ");
		riVendor.setPurchaseOrder(" ");
		riVendor.setShipToName(vendor.getShippingPoint());
		riVendor.setShipVia(vendor.getShippingVia());
				
		return riVendor;
	}
	
//	private OmcInterfaceVendor UnicomerVendorToEbsVendor(UnicomerVendor vendor){
//		OmcInterfaceVendor ebsVendor = new OmcInterfaceVendor();
//		ebsVendor.setId(Integer.valueOf(vendor.getVendorId()));
//		ebsVendor.setLookupCode(vendor.getVendorId());
//		ebsVendor.setName(vendor.getName());
//		ebsVendor.setSegment("");
//		ebsVendor.setTransactionType(vendor.getTerms());
//		
//		return ebsVendor;
//	}
}
