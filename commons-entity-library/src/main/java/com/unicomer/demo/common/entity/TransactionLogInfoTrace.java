/**
 * 
 */
package com.unicomer.demo.common.entity;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * @author carlosj_rodriguez
 *
 */
@XmlRootElement(name="transactionLogInfoTrace")
@JsonRootName(value="transactionLogInfoTrace")
public class TransactionLogInfoTrace {
//	private Date createdDate;
	private String detail;
	private String lookupData;
	private String endUserLocation;
	private String eventName;
	private String eventSource;
	private String message;
	private String globalReferenceId;
	private String localReferenceId;
	private String externalReferenceId;
	private String serviceVersion;
	private String applicationId;
	private String serverLocation;

	public TransactionLogInfoTrace() {

	}
	
	public TransactionLogInfoTrace(String detail, String lookupData, String endUserLocation,
			String message, String globalReferenceId, String localReferenceId,
			String externalReferenceId, String serviceVersion, String applicationId) {
		super();
//		this.createdDate = Calendar.getInstance().getTime();
		this.detail = detail;
		this.lookupData = lookupData;
		this.endUserLocation = endUserLocation;
		this.message = message;
		this.globalReferenceId = globalReferenceId;
		this.localReferenceId = localReferenceId;
		this.externalReferenceId = externalReferenceId;
		this.serviceVersion = serviceVersion;
		this.applicationId = applicationId;
	}
	
//	public Date getCreatedDate() {
//		return createdDate;
//	}
//
//	public void setCreatedDate(Date createdDate) {
//		this.createdDate = createdDate;
//	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getLookupData() {
		return lookupData;
	}

	public void setLookupData(String lookupData) {
		this.lookupData = lookupData;
	}

	public String getEndUserLocation() {
		return endUserLocation;
	}

	public void setEndUserLocation(String endUserLocation) {
		this.endUserLocation = endUserLocation;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventSource() {
		return eventSource;
	}

	public void setEventSource(String eventSource) {
		this.eventSource = eventSource;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getGlobalReferenceId() {
		return globalReferenceId;
	}

	public void setGlobalReferenceId(String globalReferenceId) {
		this.globalReferenceId = globalReferenceId;
	}

	public String getLocalReferenceId() {
		return localReferenceId;
	}

	public void setLocalReferenceId(String localReferenceId) {
		this.localReferenceId = localReferenceId;
	}

	public String getExternalReferenceId() {
		return externalReferenceId;
	}

	public void setExternalReferenceId(String externalReferenceId) {
		this.externalReferenceId = externalReferenceId;
	}

	public String getServiceVersion() {
		return serviceVersion;
	}

	public void setServiceVersion(String serviceVersion) {
		this.serviceVersion = serviceVersion;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}
	
	public String getServerLocation() {
		return serverLocation;
	}

	public void setServerLocation(String serverLocation) {
		this.serverLocation = serverLocation;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[detail=" + this.detail);
		sb.append(", ");
		sb.append("lookupData=" + this.lookupData);
		sb.append(", ");
		sb.append("endUserLocation=" + this.endUserLocation);
		sb.append(", ");
		sb.append("eventName=" + this.eventName);
		sb.append(", ");
		sb.append("eventSource=" + this.eventSource);
		sb.append(", ");
		sb.append("message=" + this.message);
		sb.append(", ");
		sb.append("globalReferenceId=" + this.globalReferenceId);
		sb.append(", ");
		sb.append("localReferenceId=" + this.localReferenceId);
		sb.append(", ");
		sb.append("externalReferenceId=" + this.externalReferenceId);
		sb.append(", ");
		sb.append("serviceVersion=" + this.serviceVersion);
		sb.append(", ");
		sb.append("applicationId=" + this.applicationId);
		sb.append(", ");
		sb.append("serverLocation=" + this.serverLocation);
		sb.append("]");
		return sb.toString();
	}
	
}
