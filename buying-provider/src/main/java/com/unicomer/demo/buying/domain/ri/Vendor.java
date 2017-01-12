package com.unicomer.demo.buying.domain.ri;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonRootName;

@XmlRootElement(name="Vendor")
@JsonRootName(value="Vendor")
public class Vendor {	
	private String statusCode;
	private String flag;
	private String id;
	private String name;
	private String addressLine1;
	private String addressLine2;
	private String city;
	private String state;
	private String country;
	private String zipCode;
	private String postalCode;
	private String phone;
	private String termPercent;
	private String daysInTerm;
	private String terms;
	private String dunsNumber;
	private String buyer;
	private String purchasesYTD;
	private String purchasesLTD;
	private String sortCode;
	private String ssnFirstDigits;
	private String ssnSecondDigits;
	private String ssnLastDigits;
	private String factor;
	private String paymentMethod;
	private String purchaseOrder;
	private String shipToName;
	private String shipVia;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTermPercent() {
		return termPercent;
	}

	public void setTermPercent(String termPercent) {
		this.termPercent = termPercent;
	}

	public String getDaysInTerm() {
		return daysInTerm;
	}

	public void setDaysInTerm(String daysInTerm) {
		this.daysInTerm = daysInTerm;
	}

	public String getTerms() {
		return terms;
	}

	public void setTerms(String terms) {
		this.terms = terms;
	}

	public String getDunsNumber() {
		return dunsNumber;
	}

	public void setDunsNumber(String dunsNumber) {
		this.dunsNumber = dunsNumber;
	}

	public String getBuyer() {
		return buyer;
	}

	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}

	public String getPurchasesYTD() {
		return purchasesYTD;
	}

	public void setPurchasesYTD(String purchasesYTD) {
		this.purchasesYTD = purchasesYTD;
	}

	public String getPurchasesLTD() {
		return purchasesLTD;
	}

	public void setPurchasesLTD(String purchasesLTD) {
		this.purchasesLTD = purchasesLTD;
	}

	public String getSortCode() {
		return sortCode;
	}

	public void setSortCode(String sortCode) {
		this.sortCode = sortCode;
	}

	public String getSsnFirstDigits() {
		return ssnFirstDigits;
	}

	public void setSsnFirstDigits(String ssnFirstDigits) {
		this.ssnFirstDigits = ssnFirstDigits;
	}

	public String getSsnSecondDigits() {
		return ssnSecondDigits;
	}

	public void setSsnSecondDigits(String ssnSecondDigits) {
		this.ssnSecondDigits = ssnSecondDigits;
	}

	public String getSsnLastDigits() {
		return ssnLastDigits;
	}

	public void setSsnLastDigits(String ssnLastDigits) {
		this.ssnLastDigits = ssnLastDigits;
	}

	public String getFactor() {
		return factor;
	}

	public void setFactor(String factor) {
		this.factor = factor;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getPurchaseOrder() {
		return purchaseOrder;
	}

	public void setPurchaseOrder(String purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}

	public String getShipToName() {
		return shipToName;
	}

	public void setShipToName(String shipToName) {
		this.shipToName = shipToName;
	}

	public String getShipVia() {
		return shipVia;
	}

	public void setShipVia(String shipVia) {
		this.shipVia = shipVia;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("id=" + this.id);
		sb.append(", ");
		sb.append("statusCode=" + this.statusCode);
		sb.append(", ");
		sb.append("flag=" + this.flag);
		sb.append(", ");		
		sb.append("name=" + this.name);
		sb.append(", ");
		sb.append("addressLine1=" + this.addressLine1);
		sb.append(", ");
		sb.append("addressLine2=" + this.addressLine2);
		sb.append(", ");
		sb.append("city=" + this.city);
		sb.append(", ");
		sb.append("state=" + this.state);
		sb.append(", ");
		sb.append("country=" + this.country);
		sb.append(", ");
		sb.append("zipCode=" + this.zipCode);
		sb.append(", ");
		sb.append("postalCode=" + this.postalCode);
		sb.append(", ");
		sb.append("phone=" + this.phone);
		sb.append(", ");
		sb.append("termPercent=" + this.termPercent);
		sb.append(", ");
		sb.append("daysInTerm=" + this.daysInTerm);
		sb.append(", ");
		sb.append("terms=" + this.terms);
		sb.append(", ");
		sb.append("dunsNumber=" + this.dunsNumber);
		sb.append(", ");
		sb.append("buyer=" + this.buyer);
		sb.append(", ");
		sb.append("purchasesYTD=" + this.purchasesYTD);
		sb.append(", ");
		sb.append("purchasesLTD=" + this.purchasesLTD);
		sb.append(", ");
		sb.append("sortCode=" + this.sortCode);
		sb.append(", ");
		sb.append("ssnFirstDigits=" + this.ssnFirstDigits);
		sb.append(", ");
		sb.append("ssnSecondDigits=" + this.ssnSecondDigits);
		sb.append(", ");
		sb.append("ssnLastDigits=" + this.ssnLastDigits);
		sb.append(", ");
		sb.append("factor=" + this.factor);
		sb.append(", ");
		sb.append("paymentMethod=" + this.paymentMethod);
		sb.append(", ");
		sb.append("purchaseOrder=" + this.purchaseOrder);
		sb.append(", ");
		sb.append("shipToName=" + this.shipToName);
		sb.append(", ");
		sb.append("shipVia=" + this.shipVia);
		
		return sb.toString();
	}
}