/**
 * 
 */
package com.unicomer.process.buy.entity;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * @author carlosj_rodriguez
 *
 */
@XmlRootElement(name="eventLog")
@JsonRootName(value="eventLog")
public class EventLog {
	private String lookupData;
	private String endUserLocation;
	private String eventSource;
	private String message;
	private String globalReferenceId;
	private String localReferenceId;
	private String externalReferenceId;
	@Value("${spring.application.name}") 
	private String applicationId="buy-process";
	private Long elapsedTime;
	
	
	
	/**
	 * @param message
	 * @param lookupData
	 * @param endUserLocation
	 * @param globalReferenceId
	 * @param localReferenceId
	 * @param externalReferenceId
	 */
	public EventLog(String message, String lookupData, String endUserLocation, String globalReferenceId,
			String localReferenceId, String externalReferenceId, Long elapsedTime) {
		this.eventSource = Thread.currentThread().getStackTrace()[3].toString();
		this.lookupData = lookupData;
		this.endUserLocation = endUserLocation;
		this.message = message;
		this.globalReferenceId = globalReferenceId;
		this.localReferenceId = localReferenceId;
		this.externalReferenceId = externalReferenceId;
		this.elapsedTime = elapsedTime;
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
	public String getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}
	public Long getElapsedTime() {
		return elapsedTime;
	}
	public void setElapsedTime(Long elapsedTime) {
		this.elapsedTime = elapsedTime;
	}
	
	
	
}
