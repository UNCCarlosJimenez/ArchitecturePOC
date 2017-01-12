/**
 * 
 */
package com.unicomer.logger.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.unicomer.demo.logger.domain.TransactionLog;

/**
 * @author carlosj_rodriguez
 *
 */
@Entity
@Table(name = "TRANSACTION_LOG", schema = "ADMIHOTH")
public class TransactionLogDomain extends TransactionLog {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.unicomer.demo.logger.domain.TransactionLog#getLogId()
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "LOG_ID")
	@Override
	public BigDecimal getLogId() {
		return super.getLogId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.unicomer.demo.logger.domain.TransactionLog#getCreatedDate()
	 */
	@Column(name = "CREATED_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd hh:mm:ss")
	@Override
	public Date getCreatedDate() {
		return super.getCreatedDate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.unicomer.demo.logger.domain.TransactionLog#getDetail()
	 */
	@Lob
	@Column(name = "DETAIL")
	@Override
	public String getDetail() {
		return super.getDetail();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.unicomer.demo.logger.domain.TransactionLog#getLookupData()
	 */
	@Size(max = 255)
	@Column(name = "LOOKUP_DATA")
	@Override
	public String getLookupData() {
		return super.getLookupData();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.unicomer.demo.logger.domain.TransactionLog#getEndUserLocation()
	 */
	@Size(max = 255)
	@Column(name = "END_USER_LOCATION")
	@Override
	public String getEndUserLocation() {
		return super.getEndUserLocation();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.unicomer.demo.logger.domain.TransactionLog#getEventName()
	 */
	@Size(max = 255)
	@Column(name = "EVENT_NAME")
	@Override
	public String getEventName() {
		return super.getEventName();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.unicomer.demo.logger.domain.TransactionLog#getEventLevel()
	 */
	@Size(max = 255)
	@Column(name = "EVENT_LEVEL")
	@Override
	public String getEventLevel() {
		return super.getEventLevel();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.unicomer.demo.logger.domain.TransactionLog#getEventSource()
	 */
	@Size(max = 4000)
	@Column(name = "EVENT_SOURCE")
	@Override
	public String getEventSource() {
		return super.getEventSource();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.unicomer.demo.logger.domain.TransactionLog#getMessage()
	 */
	@Size(max = 4000)
	@Column(name = "MESSAGE")
	@Override
	public String getMessage() {
		return super.getMessage();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.unicomer.demo.logger.domain.TransactionLog#getGlobalReferenceId()
	 */
	@Size(max = 50)
	@Column(name = "GLOBAL_REFERENCE_ID")
	@Override
	public String getGlobalReferenceId() {
		return super.getGlobalReferenceId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.unicomer.demo.logger.domain.TransactionLog#getLocalReferenceId()
	 */
	@Size(max = 50)
	@Column(name = "LOCAL_REFERENCE_ID")
	@Override
	public String getLocalReferenceId() {
		return super.getLocalReferenceId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.unicomer.demo.logger.domain.TransactionLog#getExternalReferenceId()
	 */
	@Size(max = 50)
	@Column(name = "EXTERNAL_REFERENCE_ID")
	@Override
	public String getExternalReferenceId() {
		return super.getExternalReferenceId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.unicomer.demo.logger.domain.TransactionLog#getServiceVersion()
	 */
	@Size(max = 8)
	@Column(name = "SERVICE_VERSION")
	@Override
	public String getServiceVersion() {
		return super.getServiceVersion();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.unicomer.demo.logger.domain.TransactionLog#getApplicationId()
	 */
	@Size(max = 255)
	@Column(name = "APPLICATION_ID")
	@Override
	public String getApplicationId() {
		return super.getApplicationId();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.unicomer.demo.logger.domain.TransactionLog#getResponseTime()
	 */
	@Column(name = "RESPONSE_TIME")
	@Override
	public Long getResponseTime() {
		return super.getResponseTime();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.unicomer.demo.logger.domain.TransactionLog#getResponseStatusCode()
	 */
	@Column(name = "RESPONSE_STATUS_CODE")
	@Override
	public Short getResponseStatusCode() {
		return super.getResponseStatusCode();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.unicomer.demo.logger.domain.TransactionLog#getServerLocation()
	 */
	@Size(max = 255)
	@Column(name = "SERVER_LOCATION")
	@Override
	public String getServerLocation() {
		return super.getServerLocation();
	}

}
