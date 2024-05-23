package com.komal.SupplierService.serviceimpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.komal.SupplierService.dao.SupplierDao;
import com.komal.SupplierService.entity.SupplierEntity;
import com.komal.SupplierService.exception.ResourseNotExistException;
import com.komal.SupplierService.model.SupplierModel;
import com.komal.SupplierService.service.SupplierService;

@Service
public class SupplierServiceImpl implements SupplierService {
	
	@Autowired
	private SupplierDao dao;
	
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public boolean addSupplier(SupplierModel suppliermodel) {
		SupplierEntity supplierentity = mapper.map(suppliermodel, SupplierEntity.class);
		boolean isAdded = dao.addSupplier(supplierentity);
		return isAdded;
	}

	@Override
	public SupplierModel getsupplierbyId(long supplierId) {
	SupplierEntity supplierEntity = dao.getsupplierbyId(supplierId);
	if(supplierEntity!=null)
	{
		SupplierModel suppliermodel = mapper.map(supplierEntity, SupplierModel.class);
	
		return suppliermodel;
	}
	else {
		 throw new ResourseNotExistException("Supplier is not availble with : "+supplierId);
		}
	}

	@Override
	public boolean deletesupplierbyId(long supplierId) {
		boolean IsDeleted = dao.deletesupplierbyId(supplierId);
		// SupplierModel suppliermodel = mapper.map(IsDeleted, SupplierModel.class);
		return IsDeleted;
		
		
}

	@Override
	public boolean updateSupplier(SupplierModel suppliermodel) {
		SupplierEntity supplierentity = mapper.map(suppliermodel, SupplierEntity.class);
		boolean isUpdated = dao.updateSupplier(supplierentity);
		
		if(isUpdated) {
			
			return true;
		}
		else {
			throw new ResourseNotExistException("Supplier iS not Available with "+suppliermodel.getSupplierId());
		}
		
		
	}
}
