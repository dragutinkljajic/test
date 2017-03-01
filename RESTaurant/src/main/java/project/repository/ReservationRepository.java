package project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import project.domain.Reservation;

public interface ReservationRepository extends Repository<Reservation, Long> {

	public Reservation save(Reservation reservation);
	
	@Query("select r from Reservation r")
	public List<Reservation> findAll();
	
	@Query("select r from Reservation r where r.id = ?1")
	public Reservation findById(Long id);
	
	@Modifying
	@Query("delete from Reservation r where r.customer.userID = ?1 and r.status = ?2")
	public void removeAllByStatusAndUserId(Long usr_id, String status);
	
}
