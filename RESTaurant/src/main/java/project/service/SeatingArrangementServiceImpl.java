package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.domain.SeatingArrangement;
import project.repository.SeatingArrangementRepository;

@Service
public class SeatingArrangementServiceImpl implements SeatingArrangementService{
	
	@Autowired
	private SeatingArrangementRepository seatingArrangementRepository;
	
	@Override
	public SeatingArrangement addSeatingArrangement(SeatingArrangement seatingArrangement) {
		return seatingArrangementRepository.save(seatingArrangement);
	}

}
