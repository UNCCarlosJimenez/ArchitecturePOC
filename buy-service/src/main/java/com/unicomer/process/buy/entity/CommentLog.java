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
@XmlRootElement(name="commentLog")
@JsonRootName(value="commentLog")
public class CommentLog {
	private String eventSource;
	private String message;
	private String globalReferenceId;
	@Value("${spring.application.name}") 
	private String applicationId="buy-process";
	private Long elapsedTime;
	
	/**
	 * @param message
	 * @param globalReferenceId
	 * @param responseTime
	 */
	public CommentLog(String message, String globalReferenceId, Long responseTime) {
		this.eventSource = Thread.currentThread().getStackTrace()[3].toString();
		this.message = message;
		this.globalReferenceId = globalReferenceId;
		this.elapsedTime = responseTime;
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
