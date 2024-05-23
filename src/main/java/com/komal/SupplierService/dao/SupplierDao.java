package com.komal.SupplierService.dao;

import com.komal.SupplierService.entity.SupplierEntity;
import com.komal.SupplierService.model.SupplierModel;

public interface SupplierDao {
	public boolean addSupplier(SupplierEntity supplierentity) ;

	public SupplierEntity getsupplierbyId(long supplierId);
	
	public boolean deletesupplierbyId(long supplierId);
	public boolean updateSupplier(SupplierEntity supplierentity);
}
