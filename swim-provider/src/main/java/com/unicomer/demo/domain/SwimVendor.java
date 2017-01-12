package com.unicomer.demo.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.unicomer.demo.common.header.RequestHeader;
import com.unicomer.demo.common.header.ResponseHeader;

@Entity
@Table(name="VENDOR", schema="SUMMER")
@XmlRootElement(name="Vendor")
@JsonRootName(value="Vendor")
public class SwimVendor extends ResourceSupport {
	/**
     * Implementacion de JPA
     */
	@Id
	@Column(name = "VENDORID", nullable = false)
	@Size(min = 0, max = 6)
	@NotNull
	private String vendorId;
	
	@Column(name = "DESCRIPTION", nullable = false)
	@Size(min = 0, max = 25)
	@NotNull
	private String name;

	@Column(name = "ADDRESS", nullable = false)
	@Size(min = 0, max = 60)
	@NotNull
	private String address;

	@Column(name = "COUNTRY", nullable = false)
	@Size(min = 0, max = 30)
	@NotNull
	private String country;

	@Column(name = "CITY", nullable = false)
	@Size(min = 0, max = 30)
	@NotNull
	private String city;

	@Column(name = "STATE", nullable = false)
	@Size(min = 0, max = 2)
	@NotNull
	private String state;

	@Column(name = "ZIP_CODE", nullable = true)
	@Size(min = 0, max = 10)
	@NotNull
	private String zipCode;

	@Column(name = "POSTAL_CODE", nullable = true)
	@Size(min = 0, max = 6)
	@NotNull
	private String postalCode;

	@Column(name = "REP_PHONE", nullable = false)
	@Size(min = 0, max = 30)
	@NotNull
	private String phoneNumber;

	@Column(name = "REP_FAX", nullable = true)
	@Size(min = 0, max = 17)
	@NotNull
	private String faxNumber;

	@Column(name = "CHECK_PRIORITY", nullable = true)
	@Size(min = 0, max = 2)
	@NotNull
	private String priority;

	@Column(name = "DUNS_NUMBER", nullable = true)
	@Size(min = 0, max = 10)
	@NotNull
	private String dunsNumber;

	@Column(name = "ABBREVIATION", nullable = true)
	@Size(min = 0, max = 10)
	@NotNull
	private String abbreviation;

	@Column(name = "CONTACT_NAME", nullable = false)
	@Size(min = 0, max = 60)
	@NotNull
	private String contactName;

	@Column(name = "TYPE", nullable = false)
	@Size(min = 0, max = 10)
	@NotNull
	private String type;

	@Column(name = "SOCIAL_SEC_NBR", nullable = true)
	@Size(min = 0, max = 10)
	@NotNull
	private String socialSecurityNumber;

	@Column(name = "FEDERAL_TAX_ID", nullable = true)
	@Size(min = 0, max = 11)
	@NotNull
	private String taxId;

	@Column(name = "TERMS1", nullable = false)
	@Size(min = 0, max = 5)
	@NotNull
	private String terms1;

	@Column(name = "TERMS2", nullable = false)
	@Size(min = 0, max = 5)
	@NotNull
	private String terms2;

	@Column(name = "TERMS3", nullable = false)
	@Size(min = 0, max = 5)
	@NotNull
	private String terms3;

	@Column(name = "FACTOR_NUMBER", nullable = true)
	@Size(min = 0, max = 6)
	@NotNull
	private String factor;

	@Column(name = "FOB_POINT", nullable = true)
	@Size(min = 0, max = 11)
	@NotNull
	private String fobPoint;

	@Column(name = "SHIPPING_PT", nullable = true)
	@Size(min = 0, max = 11)
	@NotNull
	private String shipping;

	@Column(name = "SHIP_VIA", nullable = true)
	@Size(min = 0, max = 11)
	@NotNull
	private String shipVia;

	@Column(name = "MFG_PHONE", nullable = true)
	@Size(min = 0, max = 17)
	@NotNull
	private String phoneMfg;

	@Column(name = "MFG_FAX", nullable = true)
	@Size(min = 0, max = 17)
	@NotNull
	private String faxMfg;

	@Column(name = "GLOBAL_FREIGHT", nullable = true)
	@Size(min = 0, max = 5)
	@NotNull
	private String globalFreight;

	@Column(name = "GLOBAL_DISC", nullable = true)
	@Size(min = 0, max = 5)
	@NotNull
	private String globalDiscount;

	@Column(name = "MIN_SHIP_AMT", nullable = true)
	@Size(min = 0, max = 5)
	@NotNull
	private String minShip;

	@Column(name = "MIN_SHIP_UNITS", nullable = true)
	@Size(min = 0, max = 5)
	@NotNull
	private String minShipUnit;

	@Column(name = "CYCLE_REVIEW_CD", nullable = true)
	@Size(min = 0, max = 1)
	@NotNull
	private String reviewCycle;

	@Column(name = "TELEPHONE", nullable = true)
	@Size(min = 0, max = 17)
	@NotNull
	private String telephone;

	@Column(name = "LETTER_CREDIT_VENDOR", nullable = true)
	@Size(min = 0, max = 5)
	@NotNull
	private String letterCreditVendor;

	@Column(name = "RETURN_VENDOR_NAME", nullable = false)
	@Size(min = 0, max = 25)
	@NotNull
	private String returnVendorName;

	@Column(name = "RETURN_ADDRESS", nullable = false)
	@Size(min = 0, max = 60)
	@NotNull
	private String returnAddress;

	@Column(name = "RETURN_COUNTRY", nullable = false)
	@Size(min = 0, max = 30)
	@NotNull
	private String returnCountry;

	@Column(name = "RETURN_CITY", nullable = false)
	@Size(min = 0, max = 30)
	@NotNull
	private String returnCity;

	@Column(name = "RETURN_STATE", nullable = false)
	@Size(min = 0, max = 2)
	@NotNull
	private String returnState;

	@Column(name = "REMIT_VENDOR_NAME", nullable = false)
	@Size(min = 0, max = 25)
	@NotNull
	private String remitVendorName;

	@Column(name = "REMIT_ADDRESS", nullable = false)
	@Size(min = 0, max = 60)
	@NotNull
	private String remitAddress;

	@Column(name = "REMIT_COUNTRY", nullable = false)
	@Size(min = 0, max = 30)
	@NotNull
	private String remitCountry;

	@Column(name = "REMIT_CITY", nullable = false)
	@Size(min = 0, max = 30)
	@NotNull
	private String remitCity;

	@Column(name = "REMIT_STATE", nullable = false)
	@Size(min = 0, max = 2)
	@NotNull
	private String remitState;

	@Column(name = "RETURN_POSTAL_CODE", nullable = true)
	@Size(min = 0, max = 6)
	@NotNull
	private String returnPortalCode;

	@Column(name = "REMIT_POSTAL_CODE", nullable = true)
	@Size(min = 0, max = 6)
	@NotNull
	private String remitPostalCode;

	@Column(name = "RETURN_ZIP_CODE", nullable = true)
	@Size(min = 0, max = 10)
	@NotNull
	private String returnZipCode;

	@Column(name = "REMIT_ZIP_CODE", nullable = true)
	@Size(min = 0, max = 10)
	@NotNull
	private String remitZipCode;

	@Column(name = "EMAIL_VENDOR", nullable = true)
	@Size(min = 0, max = 50)
	@NotNull
	private String email;

	@Column(name = "REMIT_EMAIL", nullable = true)
	@Size(min = 0, max = 50)
	@NotNull
	private String remitEmail;

	@Column(name = "RETURN_EMAIL", nullable = true)
	@Size(min = 0, max = 50)
	@NotNull
	private String returnEmail;

	@Column(name = "STATUS", nullable = true)
	@Size(min = 0, max = 1)
	@NotNull
	private String status;

	@Column(name = "NIT", nullable = false)
	@Size(min = 0, max = 17)
	@NotNull
	private String nit;

	@Column(name = "WHS", nullable = false)
	@Size(min = 0, max = 40)
	@NotNull
	private String wmsVendor;

	@Column(name = "VENDOR_STORAGE", nullable = false)
	@Size(min = 0, max = 3)
	@NotNull
	private String vendorStorage;

	@Column(name = "REG_FISCAL", nullable = false)
	@Size(min = 0, max = 8)
	@NotNull
	private String fiscalReg;

	@Column(name = "LAST_CHANGE_DATE", nullable = false)
	@Size(min = 0, max = 10)
	@NotNull
	// private java.sql.Date lastChangeDate;
	private String lastChangeDate;

	@Column(name = "LAST_CHANGE_TIME", nullable = false)
	@Size(min = 0, max = 8)
	@NotNull
	// private java.sql.Time lastChangeTime;
	private String lastChangeTime;

	@Column(name = "LAST_CHANGE_USER", nullable = false)
	@Size(min = 0, max = 20)
	@NotNull
	private String lastChangeUser;

	/**
	 * @return the id
	 */
	public String getVendorId() {
		return vendorId;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country
	 *            the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * @param zipCode
	 *            the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * @return the postalCode
	 */
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * @param postalCode
	 *            the postalCode to set
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber
	 *            the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the faxNumber
	 */
	public String getFaxNumber() {
		return faxNumber;
	}

	/**
	 * @param faxNumber
	 *            the faxNumber to set
	 */
	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}

	/**
	 * @return the priority
	 */
	public String getPriority() {
		return priority;
	}

	/**
	 * @param priority
	 *            the priority to set
	 */
	public void setPriority(String priority) {
		if (priority != null) {
			this.priority = priority;
		}
	}

	/**
	 * @return the dunsNumber
	 */
	public String getDunsNumber() {
		return dunsNumber;
	}

	/**
	 * @param dunsNumber
	 *            the dunsNumber to set
	 */
	public void setDunsNumber(String dunsNumber) {
		this.dunsNumber = dunsNumber;
	}

	/**
	 * @return the abbreviation
	 */
	public String getAbbreviation() {
		return abbreviation;
	}

	/**
	 * @param abbreviation
	 *            the abbreviation to set
	 */
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	/**
	 * @return the contactName
	 */
	public String getContactName() {
		return contactName;
	}

	/**
	 * @param contactName
	 *            the contactName to set
	 */
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the socialSecurityNumber
	 */
	public String getSocialSecurityNumber() {
		return socialSecurityNumber;
	}

	/**
	 * @param socialSecurityNumber
	 *            the socialSecurityNumber to set
	 */
	public void setSocialSecurityNumber(String socialSecurityNumber) {
		this.socialSecurityNumber = socialSecurityNumber;
	}

	/**
	 * @return the taxId
	 */
	public String getTaxId() {
		return taxId;
	}

	/**
	 * @param taxId
	 *            the taxId to set
	 */
	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}

	/**
	 * @return the terms1
	 */
	public String getTerms1() {
		return terms1;
	}

	/**
	 * @param terms1
	 *            the terms1 to set
	 */
	public void setTerms1(String terms1) {
		this.terms1 = terms1;
	}

	/**
	 * @return the terms2
	 */
	public String getTerms2() {
		return terms2;
	}

	/**
	 * @param terms2
	 *            the terms2 to set
	 */
	public void setTerms2(String terms2) {
		this.terms2 = terms2;
	}

	/**
	 * @return the terms3
	 */
	public String getTerms3() {
		return terms3;
	}

	/**
	 * @param terms3
	 *            the terms3 to set
	 */
	public void setTerms3(String terms3) {
		this.terms3 = terms3;
	}

	/**
	 * @return the factor
	 */
	public String getFactor() {
		return factor;
	}

	/**
	 * @param factor
	 *            the factor to set
	 */
	public void setFactor(String factor) {
		this.factor = factor;
	}

	/**
	 * @return the fobPoint
	 */
	public String getFobPoint() {
		return fobPoint;
	}

	/**
	 * @param fobPoint
	 *            the fobPoint to set
	 */
	public void setFobPoint(String fobPoint) {
		this.fobPoint = fobPoint;
	}

	/**
	 * @return the shipping
	 */
	public String getShipping() {
		return shipping;
	}

	/**
	 * @param shipping
	 *            the shipping to set
	 */
	public void setShipping(String shipping) {
		this.shipping = shipping;
	}

	/**
	 * @return the shipVia
	 */
	public String getShipVia() {
		return shipVia;
	}

	/**
	 * @param shipVia
	 *            the shipVia to set
	 */
	public void setShipVia(String shipVia) {
		this.shipVia = shipVia;
	}

	/**
	 * @return the phoneMfg
	 */
	public String getPhoneMfg() {
		return phoneMfg;
	}

	/**
	 * @param phoneMfg
	 *            the phoneMfg to set
	 */
	public void setPhoneMfg(String phoneMfg) {
		this.phoneMfg = phoneMfg;
	}

	/**
	 * @return the faxMfg
	 */
	public String getFaxMfg() {
		return faxMfg;
	}

	/**
	 * @param faxMfg
	 *            the faxMfg to set
	 */
	public void setFaxMfg(String faxMfg) {
		this.faxMfg = faxMfg;
	}

	/**
	 * @return the globalFreight
	 */
	public String getGlobalFreight() {
		return globalFreight;
	}

	/**
	 * @param globalFreight
	 *            the globalFreight to set
	 */
	public void setGlobalFreight(String globalFreight) {
		if (priority != null) {
			this.globalFreight = globalFreight;
		}
	}

	/**
	 * @return the globalDiscount
	 */
	public String getGlobalDiscount() {
		return globalDiscount;
	}

	/**
	 * @param globalDiscount
	 *            the globalDiscount to set
	 */
	public void setGlobalDiscount(String globalDiscount) {
		if (priority != null) {
			this.globalDiscount = globalDiscount;
		}
	}

	/**
	 * @return the minShip
	 */
	public String getMinShip() {
		return minShip;
	}

	/**
	 * @param minShip
	 *            the minShip to set
	 */
	public void setMinShip(String minShip) {
		if (minShip != null) {
			this.minShip = minShip;
		}
	}

	/**
	 * @return the minShipUnit
	 */
	public String getMinShipUnit() {
		return minShipUnit;
	}

	/**
	 * @param minShipUnit
	 *            the minShipUnit to set
	 */
	public void setMinShipUnit(String minShipUnit) {
		if (priority != null) {
			this.minShipUnit = minShipUnit;
		}
	}

	/**
	 * @return the reviewCycle
	 */
	public String getReviewCycle() {
		return reviewCycle;
	}

	/**
	 * @param reviewCycle
	 *            the reviewCycle to set
	 */
	public void setReviewCycle(String reviewCycle) {
		this.reviewCycle = reviewCycle;
	}

	/**
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * @param telephone
	 *            the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * @return the letterCreditVendor
	 */
	public String getLetterCreditVendor() {
		return letterCreditVendor;
	}

	/**
	 * @param letterCreditVendor
	 *            the letterCreditVendor to set
	 */
	public void setLetterCreditVendor(String letterCreditVendor) {
		if (priority != null) {
			this.letterCreditVendor = letterCreditVendor;
		}
	}

	/**
	 * @return the returnVendorName
	 */
	public String getReturnVendorName() {
		return returnVendorName;
	}

	/**
	 * @param returnVendorName
	 *            the returnVendorName to set
	 */
	public void setReturnVendorName(String returnVendorName) {
		this.returnVendorName = returnVendorName;
	}

	/**
	 * @return the returnAddress
	 */
	public String getReturnAddress() {
		return returnAddress;
	}

	/**
	 * @param returnAddress
	 *            the returnAddress to set
	 */
	public void setReturnAddress(String returnAddress) {
		this.returnAddress = returnAddress;
	}

	/**
	 * @return the returnCountry
	 */
	public String getReturnCountry() {
		return returnCountry;
	}

	/**
	 * @param returnCountry
	 *            the returnCountry to set
	 */
	public void setReturnCountry(String returnCountry) {
		this.returnCountry = returnCountry;
	}

	/**
	 * @return the returnCity
	 */
	public String getReturnCity() {
		return returnCity;
	}

	/**
	 * @param returnCity
	 *            the returnCity to set
	 */
	public void setReturnCity(String returnCity) {
		this.returnCity = returnCity;
	}

	/**
	 * @return the returnState
	 */
	public String getReturnState() {
		return returnState;
	}

	/**
	 * @param returnState
	 *            the returnState to set
	 */
	public void setReturnState(String returnState) {
		this.returnState = returnState;
	}

	/**
	 * @return the remitVendorName
	 */
	public String getRemitVendorName() {
		return remitVendorName;
	}

	/**
	 * @param remitVendorName
	 *            the remitVendorName to set
	 */
	public void setRemitVendorName(String remitVendorName) {
		this.remitVendorName = remitVendorName;
	}

	/**
	 * @return the remitAddress
	 */
	public String getRemitAddress() {
		return remitAddress;
	}

	/**
	 * @param remitAddress
	 *            the remitAddress to set
	 */
	public void setRemitAddress(String remitAddress) {
		this.remitAddress = remitAddress;
	}

	/**
	 * @return the remitCountry
	 */
	public String getRemitCountry() {
		return remitCountry;
	}

	/**
	 * @param remitCountry
	 *            the remitCountry to set
	 */
	public void setRemitCountry(String remitCountry) {
		this.remitCountry = remitCountry;
	}

	/**
	 * @return the remitCity
	 */
	public String getRemitCity() {
		return remitCity;
	}

	/**
	 * @param remitCity
	 *            the remitCity to set
	 */
	public void setRemitCity(String remitCity) {
		this.remitCity = remitCity;
	}

	/**
	 * @return the remitState
	 */
	public String getRemitState() {
		return remitState;
	}

	/**
	 * @param remitState
	 *            the remitState to set
	 */
	public void setRemitState(String remitState) {
		this.remitState = remitState;
	}

	/**
	 * @return the returnPortalCode
	 */
	public String getReturnPortalCode() {
		return returnPortalCode;
	}

	/**
	 * @param returnPortalCode
	 *            the returnPortalCode to set
	 */
	public void setReturnPortalCode(String returnPortalCode) {
		this.returnPortalCode = returnPortalCode;
	}

	/**
	 * @return the remitPostalCode
	 */
	public String getRemitPostalCode() {
		return remitPostalCode;
	}

	/**
	 * @param remitPostalCode
	 *            the remitPostalCode to set
	 */
	public void setRemitPostalCode(String remitPostalCode) {
		this.remitPostalCode = remitPostalCode;
	}

	/**
	 * @return the returnZipCode
	 */
	public String getReturnZipCode() {
		return returnZipCode;
	}

	/**
	 * @param returnZipCode
	 *            the returnZipCode to set
	 */
	public void setReturnZipCode(String returnZipCode) {
		this.returnZipCode = returnZipCode;
	}

	/**
	 * @return the remitZipCode
	 */
	public String getRemitZipCode() {
		return remitZipCode;
	}

	/**
	 * @param remitZipCode
	 *            the remitZipCode to set
	 */
	public void setRemitZipCode(String remitZipCode) {
		this.remitZipCode = remitZipCode;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the remitEmail
	 */
	public String getRemitEmail() {
		return remitEmail;
	}

	/**
	 * @param remitEmail
	 *            the remitEmail to set
	 */
	public void setRemitEmail(String remitEmail) {
		this.remitEmail = remitEmail;
	}

	/**
	 * @return the returnEmail
	 */
	public String getReturnEmail() {
		return returnEmail;
	}

	/**
	 * @param returnEmail
	 *            the returnEmail to set
	 */
	public void setReturnEmail(String returnEmail) {
		this.returnEmail = returnEmail;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the nit
	 */
	public String getNit() {
		return nit;
	}

	/**
	 * @param nit
	 *            the nit to set
	 */
	public void setNit(String nit) {
		this.nit = nit;
	}

	/**
	 * @return the wmsVendor
	 */
	public String getWmsVendor() {
		return wmsVendor;
	}

	/**
	 * @param wmsVendor
	 *            the wmsVendor to set
	 */
	public void setWmsVendor(String wmsVendor) {
		this.wmsVendor = wmsVendor;
	}

	/**
	 * @return the vendorStorage
	 */
	public String getVendorStorage() {
		return vendorStorage;
	}

	/**
	 * @param vendorStorage
	 *            the vendorStorage to set
	 */
	public void setVendorStorage(String vendorStorage) {
		this.vendorStorage = vendorStorage;
	}

	/**
	 * @return the fiscalReg
	 */
	public String getFiscalReg() {
		return fiscalReg;
	}

	/**
	 * @param fiscalReg
	 *            the fiscalReg to set
	 */
	public void setFiscalReg(String fiscalReg) {
		this.fiscalReg = fiscalReg;
	}

	/**
	 * @return the lastChangeDate
	 */
	public String getLastChangeDate() {
		return lastChangeDate;
	}

	/**
	 * @param lastChangeDate
	 *            the lastChangeDate to set
	 */
	public void setLastChangeDate(String lastChangeDate) {
		this.lastChangeDate = lastChangeDate;
	}

	/**
	 * @return the lastChangeTime
	 */
	public String getLastChangeTime() {
		return lastChangeTime;
	}

	/**
	 * @param lastChangeTime
	 *            the lastChangeTime to set
	 */
	public void setLastChangeTime(String lastChangeTime) {
		this.lastChangeTime = lastChangeTime;
	}

	/**
	 * @return the lastChangeUser
	 */
	public String getLastChangeUser() {
		return lastChangeUser;
	}

	/**
	 * @param lastChangeUser
	 *            the lastChangeUser to set
	 */
	public void setLastChangeUser(String lastChangeUser) {
		this.lastChangeUser = lastChangeUser;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("id=" + this.vendorId);
		sb.append(", ");
		sb.append("name=" + this.name);
		sb.append(", ");
		sb.append("address=" + this.address);
		sb.append(", ");
		sb.append("country=" + this.country);
		sb.append(", ");
		sb.append("city=" + this.city);
		sb.append(", ");
		sb.append("state=" + this.state);
		sb.append(", ");
		sb.append("zipCode=" + this.zipCode);
		sb.append(", ");
		sb.append("postalCode=" + this.postalCode);
		sb.append(", ");
		sb.append("phoneNumber=" + this.phoneNumber);
		sb.append(", ");
		sb.append("faxNumber=" + this.faxNumber);
		sb.append(", ");
		sb.append("priority=" + this.priority);
		sb.append(", ");
		sb.append("dunsNumber=" + this.dunsNumber);
		sb.append(", ");
		sb.append("abbreviation=" + this.abbreviation);
		sb.append(", ");
		sb.append("contactName=" + this.contactName);
		sb.append(", ");
		sb.append("type=" + this.type);
		sb.append(", ");
		sb.append("socialSecurityNumber=" + this.socialSecurityNumber);
		sb.append(", ");
		sb.append("taxId=" + this.taxId);
		sb.append(", ");
		sb.append("terms1=" + this.terms1);
		sb.append(", ");
		sb.append("terms2=" + this.terms2);
		sb.append(", ");
		sb.append("terms3=" + this.terms3);
		sb.append(", ");
		sb.append("factor=" + this.factor);
		sb.append(", ");
		sb.append("fobPoint=" + this.fobPoint);
		sb.append(", ");
		sb.append("shipping=" + this.shipping);
		sb.append(", ");
		sb.append("shipVia=" + this.shipVia);
		sb.append(", ");
		sb.append("phoneMfg=" + this.phoneMfg);
		sb.append(", ");
		sb.append("faxMfg=" + this.faxMfg);
		sb.append(", ");
		sb.append("globalFreight=" + this.globalFreight);
		sb.append(", ");
		sb.append("globalDiscount=" + this.globalDiscount);
		sb.append(", ");
		sb.append("minShip=" + this.minShip);
		sb.append(", ");
		sb.append("minShipUnit=" + this.minShipUnit);
		sb.append(", ");
		sb.append("reviewCycle=" + this.reviewCycle);
		sb.append(", ");
		sb.append("telephone=" + this.telephone);
		sb.append(", ");
		sb.append("letterCreditVendor=" + this.letterCreditVendor);
		sb.append(", ");
		sb.append("returnVendorName=" + this.returnVendorName);
		sb.append(", ");
		sb.append("returnAddress=" + this.returnAddress);
		sb.append(", ");
		sb.append("returnCountry=" + this.returnCountry);
		sb.append(", ");
		sb.append("returnCity=" + this.returnCity);
		sb.append(", ");
		sb.append("returnState=" + this.returnState);
		sb.append(", ");
		sb.append("remitVendorName=" + this.remitVendorName);
		sb.append(", ");
		sb.append("remitAddress=" + this.remitAddress);
		sb.append(", ");
		sb.append("remitCountry=" + this.remitCountry);
		sb.append(", ");
		sb.append("remitCity=" + this.remitCity);
		sb.append(", ");
		sb.append("remitState=" + this.remitState);
		sb.append(", ");
		sb.append("returnPortalCode=" + this.returnPortalCode);
		sb.append(", ");
		sb.append("remitPostalCode=" + this.remitPostalCode);
		sb.append(", ");
		sb.append("returnZipCode=" + this.returnZipCode);
		sb.append(", ");
		sb.append("remitZipCode=" + this.remitZipCode);
		sb.append(", ");
		sb.append("email=" + this.email);
		sb.append(", ");
		sb.append("remitEmail=" + this.remitEmail);
		sb.append(", ");
		sb.append("returnEmail=" + this.returnEmail);
		sb.append(", ");
		sb.append("status=" + this.status);
		sb.append(", ");
		sb.append("nit=" + this.nit);
		sb.append(", ");
		sb.append("wmsVendor=" + this.wmsVendor);
		sb.append(", ");
		sb.append("vendorStorage=" + this.vendorStorage);
		sb.append(", ");
		sb.append("fiscalReg=" + this.fiscalReg);
		sb.append(", ");
		sb.append("lastChangeDate=" + this.lastChangeDate);
		sb.append(", ");
		sb.append("lastChangeTime=" + this.lastChangeTime);
		sb.append(", ");
		sb.append("lastChangeUser=" + this.lastChangeUser);
		sb.append(", ");

		return sb.toString();
	}	

	/**
	 * Mensaje de peticion para operaciones del recurso Vendor
	 * 
	 * @author oracle
	 *
	 */
	public static class RequestMessage extends RequestHeader implements Serializable {
		private static final long serialVersionUID = 325842498732514519L;
		private SwimVendor data;

		/**
		 * @return the Vendor
		 */
		public SwimVendor getData() {
			return data;
		}

		/**
		 * @param requestEntity
		 *            the Vendor to set
		 */
		public void setData(SwimVendor data) {
			this.data = data;
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder(super.toString());
			if (this.data != null) {
				sb.append(", data=[{");
				sb.append(this.data.toString());
				sb.append("}]");
			}
			return sb.toString();
		}
	}

	/**
	 * Mensaje de respuesta de peticiones de recurso Vendor
	 * 
	 * @author oracle
	 *
	 */
	public static class ResponseMessage extends ResponseHeader implements Serializable {
		private static final long serialVersionUID = 325842498732514519L;
		private List<SwimVendor> data;

		/**
		 * @return the Vendor
		 */
		public List<SwimVendor> getData() {
			return data;
		}

		/**
		 * @param requestEntity
		 *            the Vendor to set
		 */
		public void setData(List<SwimVendor> data) {
			this.data = data;
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder(super.toString());
			if (this.data != null) {
				sb.append(", data=[{");
				sb.append(this.data.toString());
				sb.append("}]");
			}
			return sb.toString();
		}
	}

}
