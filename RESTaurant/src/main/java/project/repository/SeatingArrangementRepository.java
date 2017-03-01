package project.repository;

import org.springframework.data.repository.Repository;

import project.domain.SeatingArrangement;

public interface SeatingArrangementRepository extends Repository<SeatingArrangement, Long>{
	public SeatingArrangement save(SeatingArrangement seatingArrangement);
}
