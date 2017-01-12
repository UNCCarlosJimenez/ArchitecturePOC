package com.unicomer.demo.common.header;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonFormat;

@XmlRootElement
public class ResponseHeader implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7183103897619662473L;
	private Integer responseCode;
	private String responseDescription;
	private Integer serviceCode;
	private String serviceDescription;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd hh:mm:ss")
    private Date date;
    private String transactionId;
	/**
	 * @return the responseCode
	 */
	public Integer getResponseCode() {
		return responseCode;
	}
	/**
	 * @param responseCode the responseCode to set
	 */
	public void setResponseCode(Integer responseCode) {
		this.responseCode = responseCode;
	}
	/**
	 * @return the responseDescription
	 */
	public String getResponseDescription() {
		return responseDescription;
	}
	/**
	 * @param responseDescription the responseDescription to set
	 */
	public void setResponseDescription(String responseDescription) {
		this.responseDescription = responseDescription;
	}
	/**
	 * @return the serviceCode
	 */
	public Integer getServiceCode() {
		return serviceCode;
	}
	/**
	 * @param serviceCode the serviceCode to set
	 */
	public void setServiceCode(Integer serviceCode) {
		this.serviceCode = serviceCode;
	}
	/**
	 * @return the serviceDescription
	 */
	public String getServiceDescription() {
		return serviceDescription;
	}
	/**
	 * @param serviceDescription the serviceDescription to set
	 */
	public void setServiceDescription(String serviceDescription) {
		this.serviceDescription = serviceDescription;
	}
	/**
	 * @return the transactionId
	 */
	public String getTransactionId() {
		return transactionId;
	}
	/**
	 * @param transactionId the transactionId to set
	 */
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	/**
	 * @return the date
	 */
	public Date getDate() {
		return this.date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("responseCode="+this.responseCode);
		sb.append(", ");
		sb.append("responseDescription="+this.responseDescription);
		sb.append(", ");
		sb.append("serviceCode="+this.serviceCode);
		sb.append(", ");
		sb.append("serviceDescription="+this.serviceDescription);
		sb.append(", ");
		sb.append("date="+new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(this.date));
		sb.append(", ");
		sb.append("transactionId="+this.transactionId);
		
		return sb.toString();
	}
}
