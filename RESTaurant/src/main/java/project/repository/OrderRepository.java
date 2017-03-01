package project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import project.domain.RestOrder;

public interface OrderRepository extends Repository<RestOrder, Long> {

	RestOrder save(RestOrder order);
	
	@Query("select r from RestOrder r where r.id = ?1")
	RestOrder findById(Long id);
	
	@Query("select r from RestOrder r")
	List<RestOrder> findAll();
}
