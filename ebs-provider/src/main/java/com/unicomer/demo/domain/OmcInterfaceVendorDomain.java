package com.unicomer.demo.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "OMC_INTERFASE_VENDORS", schema = "ADMIHOTH")
@XmlRootElement(name="Vendor")
public class OmcInterfaceVendorDomain {

	@Column(name = "VENDOR_INTERFACE_ID", nullable = false)
	@Id
	private Integer id;

	@Column(name = "VENDOR_NAME", nullable = true)
	@Size(min = 0, max = 100)
	@NotNull
	private String name;

	@Column(name = "SEGMENT", nullable = true)
	@Size(min = 0, max = 255)
	@NotNull
	private String segment;

	@Column(name = "TRANSACTION_TYPE", nullable = true)
	@Size(min = 0, max = 255)
	@NotNull
	private String transactionType;

	@Column(name = "VENDOR_TYPE_LOOKUP_CODE", nullable = true)
	@Size(min = 0, max = 6)
	@NotNull
	private String lookupCode;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSegment() {
		return segment;
	}

	public void setSegment(String segment) {
		this.segment = segment;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getLookupCode() {
		return lookupCode;
	}

	public void setLookupCode(String lookupCode) {
		this.lookupCode = lookupCode;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("id=" + this.id);
		sb.append(", ");
		sb.append("name=" + this.name);
		sb.append(", ");
		sb.append("segment=" + this.segment);
		sb.append(", ");
		sb.append("transactionType=" + this.transactionType);
		sb.append(", ");
		sb.append("lookupCode=" + this.lookupCode);
		
		return sb.toString();
	}
		
}
