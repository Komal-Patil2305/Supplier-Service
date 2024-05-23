package com.komal.SupplierService.service;

import com.komal.SupplierService.model.SupplierModel;

public interface SupplierService {
	
	public boolean addSupplier(SupplierModel suppliermodel);
	public SupplierModel getsupplierbyId(long supplierId);
	
	public boolean deletesupplierbyId(long supplierId);
	public boolean updateSupplier(SupplierModel suppliermodel);

}
