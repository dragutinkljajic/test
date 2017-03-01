package project.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import project.domain.Supplier;

public interface SupplierRepository extends Repository<Supplier, Long>{
	
	public List<Supplier> findAll();
	
	public Supplier save(Supplier s);
	
	@Query("select s from Supplier s where s.userID = ?1")
	public Supplier findSupplierById(Long id);
	
	@Query("select s from Supplier s where s.email = ?1")
	public Supplier findSupplierByEmail(String email);

	@Modifying
	@Query("update Supplier s set s.email = ?2 where s.userID = ?1 ")
	public void updateEmail(Long id, String email);
	
	@Modifying
	@Query("update Supplier s set s.password = ?2 where s.userID = ?1 ")
	public void updatePassword(Long id, String password);
	
	@Modifying
	@Query("update Supplier s set s.label = ?2, s.description = ?3 where s.userID = ?1")
	public void updateDetails(Long id, String label, String description);
}
