package project.service;

import java.util.List;

import project.domain.Reservation;

public interface ReservationService {

	List<Reservation> getAll();
	
	Reservation findById(Long id);
	
	void removeAllByStatusAndUserId(Long usr_id, String status);
	
	Reservation save(Reservation reservation);
}
