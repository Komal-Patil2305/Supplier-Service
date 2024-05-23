package com.komal.SupplierService.daoimpl;

import javax.persistence.RollbackException;
import javax.validation.ConstraintViolationException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.komal.SupplierService.dao.SupplierDao;
import com.komal.SupplierService.entity.SupplierEntity;
import com.komal.SupplierService.exception.ResourseAlreadyExistException;
import com.komal.SupplierService.exception.ResourseNotExistException;
import com.komal.SupplierService.exception.SomethingWentWrongException;

@Repository
public class SupplierDaoImpl implements SupplierDao {
	
	@Autowired
	private SessionFactory sf;

	@Override
	public boolean addSupplier(SupplierEntity supplierentity) {
		
		boolean isAdded=false;
		try {
			Session session= sf.openSession();
			
			
			SupplierEntity dbentity = getsupplierbyId(supplierentity.getSupplierId());
			
			
			SupplierEntity supplierEntitybyName = session.createQuery("From SupplierEntity where supplierName =: name", SupplierEntity.class ).
			setParameter("name", supplierentity.getSupplierName()).uniqueResult();
			if(supplierEntitybyName!=null) {
				throw new ResourseAlreadyExistException("Supplier name is already avilable with "+supplierentity.getSupplierName());
			}
			
			SupplierEntity supplierEntitybyMobile = session.createQuery("From SupplierEntity where mobileNo =:mobile", SupplierEntity.class).
			setParameter("mobile", supplierentity.getMobileNo()).uniqueResult();
			if(supplierEntitybyMobile!=null) {
				throw new ResourseAlreadyExistException("Supplier mobile is already avilable with "+supplierentity.getMobileNo());
			}
			
			if(dbentity==null)
			{
			Transaction t = session.beginTransaction();
			session.save(supplierentity);
			t.commit();
			isAdded=true;
			}
			else 
			{
				throw new ResourseAlreadyExistException("supplier is already availbale with :"+supplierentity.getSupplierId());
			}
			
			
		} 
		
	catch (RollbackException e) {
		
				throw new SomethingWentWrongException("something went wrong during adding supplier, check unit field");
		}
		
		return isAdded;
	}

	@Override
	public SupplierEntity getsupplierbyId(long supplierId) {
		SupplierEntity supplierentity = null;
		
		try {
			Session session=sf.openSession();
			 supplierentity	 = session.get(SupplierEntity.class, supplierId);
			
			
		} catch (HibernateException he) {
			he.printStackTrace();
			
		throw new  SomethingWentWrongException("connection is not established");
		}
		
		return supplierentity;
	}

	@Override
	public boolean deletesupplierbyId(long supplierId) {
		boolean isDeleted=false;
	//	SupplierEntity supplierentity=null;
		try(Session session=sf.openSession()) {
			
			
		//	Session session=sf.openSession();
			SupplierEntity	 supplierentity = session.get(SupplierEntity.class, supplierId);
			 if(supplierentity==null) {
				  
			 }
			 Transaction t=session.beginTransaction();
			session.delete(supplierentity);
			t.commit();
			isDeleted=true;
			
		} catch (Exception e) {
			
			throw new SomethingWentWrongException("supplier is not avilable with " +supplierId);
		}
		
		return isDeleted;
	}

	@Override
	public boolean updateSupplier(SupplierEntity supplierentity) {
		boolean isUpadated=false;
		try {
			SupplierEntity dbentity = getsupplierbyId(supplierentity.getSupplierId());
			
			
			if(dbentity!=null) {
				Session session=sf.openSession();
				Transaction t= session.beginTransaction();
				session.update(supplierentity);
				t.commit();
				isUpadated=true;
			}
			
			
		} catch (Exception e) {
			
			throw new SomethingWentWrongException("some thing went wrong during updating product");
		}
		return isUpadated;
	}

}
