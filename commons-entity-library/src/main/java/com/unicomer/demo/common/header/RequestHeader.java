package com.unicomer.demo.common.header;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RequestHeader implements Serializable {
	/**
	 * 
	 */
	static final long serialVersionUID = -3680995512757109454L;
	private String token="";
	private String application="";
	private String store="";
	private String posId="";
	private Date date;
	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}
	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}
	/**
	 * @return the application
	 */
	public String getApplication() {
		return application;
	}
	/**
	 * @param application the application to set
	 */
	public void setApplication(String application) {
		this.application = application;
	}
	/**
	 * @return the store
	 */
	public String getStore() {
		return store;
	}
	/**
	 * @param store the store to set
	 */
	public void setStore(String store) {
		this.store = store;
	}
	/**
	 * @return the posId
	 */
	public String getPosId() {
		return posId;
	}
	/**
	 * @param posId the posId to set
	 */
	public void setPosId(String posId) {
		this.posId = posId;
	}
	/**
	 * @return the requestDate
	 */
	public Date getdate() {
		return date;
	}
	/**
	 * @param requestDate the requestDate to set
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
		sb.append("token="+this.token);
		sb.append(", ");
		sb.append("application="+this.application);
		sb.append(", ");
		sb.append("store="+this.store);
		sb.append(", ");
		sb.append("posId="+this.posId);
		sb.append(", ");
		sb.append("date="+new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(this.date));
		
		return sb.toString();
	}
}
