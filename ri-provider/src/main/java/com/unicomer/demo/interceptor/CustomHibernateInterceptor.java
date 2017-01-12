package com.unicomer.demo.interceptor;

import org.hibernate.EmptyInterceptor;

public class CustomHibernateInterceptor extends EmptyInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3308350759346587742L;
	
	@Override
	public String onPrepareStatement(String sql) {
		String newSql = sql;
		newSql=newSql.replace("as vnñav", "as vnnav");
		newSql=newSql.toUpperCase();
		
		System.out.println("CustomHibernateInterceptor: newSql="+newSql);
		
		return newSql;
	}
}
