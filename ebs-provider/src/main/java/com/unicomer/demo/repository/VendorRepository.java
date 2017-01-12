package com.unicomer.demo.repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.unicomer.demo.domain.OmcInterfaceVendorDomain;
import com.unicomer.demo.message.OmcInterfaceVendor;

public class VendorRepository {
	private Session session;
    
	@SuppressWarnings("unchecked")
	public List<OmcInterfaceVendor> findAll(){
		List<OmcInterfaceVendor> resultList = new ArrayList<OmcInterfaceVendor>();
		List<OmcInterfaceVendorDomain> vendorList = new ArrayList<OmcInterfaceVendorDomain>();
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Query query = session.createQuery("from " + OmcInterfaceVendorDomain.class.getName());
			vendorList = query.list();
			session.getTransaction().commit();
			
			Iterator<OmcInterfaceVendorDomain> it = vendorList.iterator();
			while(it.hasNext()){
				OmcInterfaceVendorDomain vendor = it.next();
				OmcInterfaceVendor vendorReturn = new OmcInterfaceVendor();
				vendorReturn.setId(vendor.getId());
				vendorReturn.setName(vendor.getName());
				vendorReturn.setLookupCode(vendor.getLookupCode());
				vendorReturn.setSegment(vendor.getSegment());
				vendorReturn.setTransactionType(vendor.getTransactionType());
				
				resultList.add(vendorReturn);
			}
			
		}catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
		return resultList;
	}
	
	public OmcInterfaceVendor findOne(Integer id){
		OmcInterfaceVendor vendorReturn = new OmcInterfaceVendor();
		OmcInterfaceVendorDomain vendor = new OmcInterfaceVendorDomain();
		try{
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			vendor = session.load(OmcInterfaceVendorDomain.class, id);
			session.getTransaction().commit();
						
			vendorReturn.setId(vendor.getId());
			vendorReturn.setName(vendor.getName());
			vendorReturn.setLookupCode(vendor.getLookupCode());
			vendorReturn.setSegment(vendor.getSegment());
			vendorReturn.setTransactionType(vendor.getTransactionType());
		}catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
		return vendorReturn;
	}
	
	public OmcInterfaceVendor save(OmcInterfaceVendorDomain vendor){
		OmcInterfaceVendor vendorReturn = new OmcInterfaceVendor();
		try{
			Session session = HibernateUtil.getSessionFactory().openSession();
			
			session.beginTransaction();
			vendor = (OmcInterfaceVendorDomain) session.save(vendor);
			session.getTransaction().commit();
			
			vendorReturn.setId(vendor.getId());
			vendorReturn.setName(vendor.getName());
			vendorReturn.setLookupCode(vendor.getLookupCode());
			vendorReturn.setSegment(vendor.getSegment());
			vendorReturn.setTransactionType(vendor.getTransactionType());
		}catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
		return vendorReturn;
	}
	
	public void delete(OmcInterfaceVendorDomain vendor){
		try{
			Session session = HibernateUtil.getSessionFactory().openSession();
			
			session.beginTransaction();
			session.delete(vendor);
			session.getTransaction().commit();
		}catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
	
}
