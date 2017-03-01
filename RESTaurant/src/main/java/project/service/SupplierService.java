package project.service;

import java.util.List;

import project.domain.Supplier;

public interface SupplierService {
	List<Supplier> getAll();
	
	Supplier addSupplier(Supplier s);
	
	Supplier getSupplierById(Long id);
	
	Supplier getSupplierByEmail(String email);
	
	void updateSupplierEmail(Supplier supplier);
	
	void updateSupplierPassword(Supplier supplier);
	
	void updateSupplierDetails(Supplier supplier);
}
