package project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import project.domain.Supplier;
import project.repository.SupplierRepository;

@Service
public class SupplierServiceImpl implements SupplierService{
	
	@Autowired
	private SupplierRepository supplierRepository;
	
	@Override
	public List<Supplier> getAll() {
		return this.supplierRepository.findAll();
	}

	@Override
	public Supplier addSupplier(Supplier s) {
		return this.supplierRepository.save(s);
	}
	
	@Override
	public Supplier getSupplierById(Long id) {
		Assert.notNull(id, "ID cannot be null");
		return supplierRepository.findSupplierById(id);
	}
	
	@Override
	public void updateSupplierEmail(Supplier supplier) {
		Assert.notNull(supplier, "Cannot be null");
		supplierRepository.updateEmail(supplier.getUserID(), supplier.getEmail());
	}

	@Override
	public void updateSupplierPassword(Supplier supplier) {
		Assert.notNull(supplier, "Cannot be null");
		supplierRepository.updatePassword(supplier.getUserID(), supplier.getPassword());
		
	}
	
	@Override
	public void updateSupplierDetails(Supplier supplier) {
		Assert.notNull(supplier, "Cannot be null");
		supplierRepository.updateDetails(supplier.getUserID(), supplier.getLabel(), supplier.getDescription());
	}

	@Override
	public Supplier getSupplierByEmail(String email) {
		Assert.notNull(email, "email cannot be null");
		return supplierRepository.findSupplierByEmail(email);
	}
}
