package project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import project.domain.Reservation;
import project.repository.ReservationRepository;

@Service
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	private ReservationRepository reservationRepository;
	
	@Override
	public List<Reservation> getAll() {
		return reservationRepository.findAll();
	}

	@Override
	public Reservation findById(Long id) {
		Assert.notNull(id);
		return reservationRepository.findById(id);
	}

	@Override
	public void removeAllByStatusAndUserId(Long usr_id, String status) {
		Assert.notNull(usr_id);
		Assert.notNull(status);
		reservationRepository.removeAllByStatusAndUserId(usr_id, status);
	}

	@Override
	public Reservation save(Reservation reservation) {
		Assert.notNull(reservation, "Reservation cannot be null");
		return reservationRepository.save(reservation);
	}

}
