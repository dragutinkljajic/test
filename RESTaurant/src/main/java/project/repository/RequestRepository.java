package project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import project.domain.Customer;
import project.domain.Request;

public interface RequestRepository extends Repository<Request, Long> {
	
	@Query("select r from Request r")
	public List<Request> findAll();
	
	@Query("select r from Request r where r.id = ?1")
	public Request findRequestById(Long id);
	
	@Query("select r from Request r where r.sender.userID = ?1")
	public List<Request> findRequestsBySender(Long id);
	
	@Query("select r from Request r where r.receiver.userID = ?1")
	public List<Request> findRequestsByReceiver(Long id);
	
	public Request save(Request request);
	
	@Query("select r from Request r where r.receiver.userID = ?2 and r.sender.userID = ?1")
	public List<Request> findRequestsByCombination(Long sender, Long receiver);

}
