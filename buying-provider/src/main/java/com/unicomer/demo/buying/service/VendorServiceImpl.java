package com.unicomer.demo.buying.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.unicomer.demo.buying.dao.EbsClient;
import com.unicomer.demo.buying.dao.LoggerClient;
import com.unicomer.demo.buying.dao.RiClient;
import com.unicomer.demo.buying.dao.SwimClient;
import com.unicomer.demo.buying.message.ebs.OmcInterfaceVendor;
import com.unicomer.demo.common.entity.UnicomerVendor;
import com.unicomer.demo.ri.domain.RiVendor;
import com.unicomer.demo.swim.domain.SwimVendor;

@Service("vendorService")
public class VendorServiceImpl implements VendorService {
	@Autowired
	SwimClient swimClient;
	@Autowired
	RiClient riClient;
	
	EbsClient ebsClient = new EbsClient();
	
	@Autowired LoggerClient logger;
	@Value("${spring.application.name}") String applicationName;
	@Value("${swim.date.format.pattern}") String dateFormatPattern;
	@Value("${swim.time.format.pattern}") String timeFormatPattern;

	@Override
	public List<UnicomerVendor> findAll() {
		System.out.println("buying-provider@VendorServiceImpl: Inicio de findAll()");
		List<UnicomerVendor> resultList = new ArrayList<UnicomerVendor>();
		/**
		 * 
		 */
		try {
			List<SwimVendor> swimVendors = swimClient.getVendors().getData();
			
			Iterator<SwimVendor> it = swimVendors.iterator();
			while (it.hasNext()) {
				SwimVendor swimVendor = it.next();
				resultList.add(SwimVendorToUnicomerVendor(swimVendor));
			}
		} catch (Exception e) {
			System.err.println("buying-provider@VendorServiceImpl: " + e.getMessage());
			e.printStackTrace();
		}
		System.out.println("buying-provider@VendorServiceImpl: Fin de findAll()");
		return resultList;
	}

	@Override
	public UnicomerVendor save(String transactionId, UnicomerVendor vendor) {
		UnicomerVendor result = new UnicomerVendor();
		
		try{
			//Swim
			SwimVendor.RequestMessage swimRequest = new SwimVendor.RequestMessage();
			swimRequest.setApplication(applicationName);
			swimRequest.setDate(Calendar.getInstance().getTime());
			swimRequest.setPosId("");
			swimRequest.setStore("");
			swimRequest.setTransaction(transactionId);
			swimRequest.setData(UnicomerVendorToSwimVendor(vendor));
			System.out.println("buying-provider@VendorServiceImpl: swimClient.addVendor(swimRequest)");
			swimClient.addVendor(swimRequest);
		}catch (Exception e) {
			System.err.println("buying-provider@VendorServiceImpl: " + e.getMessage());
			e.printStackTrace();
		}
		
		try{
			//Swim
			SwimVendor.RequestMessage swimRequest = new SwimVendor.RequestMessage();
			swimRequest.setApplication(applicationName);
			swimRequest.setDate(Calendar.getInstance().getTime());
			swimRequest.setPosId("");
			swimRequest.setStore("");
			swimRequest.setTransaction(transactionId);
			swimRequest.setData(UnicomerVendorToSwimVendor(vendor));
			System.out.println("buying-provider@VendorServiceImpl: swimClient.addVendor(swimRequest)");
			swimClient.addVendor(swimRequest);
		}catch (Exception e) {
			System.err.println("buying-provider@VendorServiceImpl: " + e.getMessage());
			e.printStackTrace();
		}
		
		try{
			//Ri
			System.out.println("buying-provider@VendorServiceImpl: riClient.addVendors(UnicomerVendorToRiVendor(vendor));");
			riClient.addVendors(UnicomerVendorToRiVendor(vendor));
		}catch (Exception e) {
			System.err.println("buying-provider@VendorServiceImpl: " + e.getMessage());
			e.printStackTrace();
		}
		
		try{
			//EBS
			System.out.println("buying-provider@VendorServiceImpl: ebsClient.newVendor(transactionId, UnicomerVendorToEbsVendor(vendor));");
			ebsClient.newVendor(transactionId, UnicomerVendorToEbsVendor(vendor));
		}catch (Exception e) {
			System.err.println("buying-provider@VendorServiceImpl: " + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean exists(String id) {
		try {
			List<SwimVendor> swimVendors = swimClient.getVendor(id).getData();
			if (swimVendors.size() > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public UnicomerVendor findOne(String id) {
		UnicomerVendor result = null;
		try {
			List<SwimVendor> swimVendors = swimClient.getVendor(id).getData();
			if (swimVendors.size() > 0) {
				result = SwimVendorToUnicomerVendor(swimVendors.get(0));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public void delete(String transactionId, String id) {
		try {
			swimClient.deleteVendor(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			riClient.deleteVendors(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			ebsClient.deleteVendor(transactionId, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private SwimVendor UnicomerVendorToSwimVendor(UnicomerVendor vendor){
		SwimVendor swimVendor = new SwimVendor();
		swimVendor.setVendorId(String.format("%06d", vendor.getVendorId()));
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
		swimVendor.setTerms1(vendor.getTerms().substring(0, 4));
		swimVendor.setTerms2(vendor.getTerms().substring(5, 9));
		swimVendor.setTerms3(vendor.getTerms().substring(10, 14));
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
		swimVendor.setLetterCreditVendor(" ");
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
		riVendor.setId(String.format("%06d", vendor.getVendorId()));
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
		riVendor.setPurchasesYTD(" ");
		riVendor.setPurchasesLTD(" ");
		riVendor.setSortCode(" ");
		riVendor.setSsnFirstDigits(vendor.getSocialSecurityNumber().substring(0, 2));
		riVendor.setSsnSecondDigits(vendor.getSocialSecurityNumber().substring(3, 4));
		riVendor.setSsnLastDigits(vendor.getSocialSecurityNumber().substring(5, 8));
		riVendor.setFactor(vendor.getFactor().toString());
		riVendor.setPaymentMethod(" ");
		riVendor.setPurchaseOrder(" ");
		riVendor.setShipToName(vendor.getShippingPoint());
		riVendor.setShipVia(vendor.getShippingVia());
				
		return riVendor;
	}
	
	
	
	private OmcInterfaceVendor UnicomerVendorToEbsVendor(UnicomerVendor vendor){
		OmcInterfaceVendor ebsVendor = new OmcInterfaceVendor();
		ebsVendor.setId(Integer.valueOf(vendor.getVendorId()));
		ebsVendor.setLookupCode(String.format("%06d", vendor.getVendorId()));
		ebsVendor.setName(vendor.getName());
		ebsVendor.setSegment("");
		ebsVendor.setTransactionType(vendor.getTerms());
		
		return ebsVendor;
	}
}
