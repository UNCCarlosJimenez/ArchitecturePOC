package com.unicomer.demo.common.header;

import java.io.Serializable;

public class RequestHeader implements Serializable {
	/**
	 * 
	 */
	static final long serialVersionUID = -3680995512757109454L;
	private String transaction="";
	private String application="";
	private String store="";
	private String posId="";
	/**
	 * @return the token
	 */
	public String getTransaction() {
		return transaction;
	}
	/**
	 * @param token the token to set
	 */
	public void setTransaction(String transaction) {
		this.transaction = transaction;
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
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("transaction="+this.transaction);
		sb.append(", ");
		sb.append("application="+this.application);
		sb.append(", ");
		sb.append("store="+this.store);
		sb.append(", ");
		sb.append("posId="+this.posId);
		
		return sb.toString();
	}
}
