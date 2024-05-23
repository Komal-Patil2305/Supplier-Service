package com.komal.SupplierService.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.komal.SupplierService.model.SupplierModel;
import com.komal.SupplierService.service.SupplierService;
import com.komal.SupplierService.serviceimpl.SupplierServiceImpl;

@RestController
@RequestMapping("supplier")
public class SupplierController {
	
	
	@Autowired
	private SupplierService  service;
	
	@PostMapping("add-supplier")
	public ResponseEntity<String> addSupplier(@RequestBody @Valid SupplierModel suppliermosel){
		boolean isAdded = service.addSupplier(suppliermosel);
		
			return ResponseEntity.ok(" Supplier Added ");
	
	}
	
	
	@GetMapping("get-supplier/{supplierId}")
	public ResponseEntity<SupplierModel> getsupplierbyId(@PathVariable long supplierId){
		SupplierModel supplierModel = service.getsupplierbyId(supplierId);
		return ResponseEntity.ok(supplierModel);
		
	}
	
	@DeleteMapping("delete-supplier/{supplierId}")
	public ResponseEntity<String> deletesupplierbyId(@PathVariable long supplierId){
		 boolean isdeleted = service.deletesupplierbyId(supplierId);
		return ResponseEntity.ok("deleted successfully");
		
	}
	@PutMapping("update-supplier")
	public ResponseEntity<String> updateSupplier(@RequestBody SupplierModel suppliermodel){
		boolean updateSupplier = service.updateSupplier(suppliermodel);
		return  ResponseEntity.ok("Updated successfully");
		
	}
}
