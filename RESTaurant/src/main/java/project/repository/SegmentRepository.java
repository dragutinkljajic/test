package project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import project.domain.Segment;

public interface SegmentRepository extends Repository<Segment, Long>{
	public Segment save(Segment segment);
	
	@Query("select s from Segment s where s.seating.idSeating = ?1")
	public List<Segment> findSemgnetsBySeatingId(Long id);
	
	@Query("select s from Segment s where s.label =?1")
	public Segment findSegmentByLabel(String label);
	
	@Modifying
	@Query("delete from Segment s where s.idSegment = ?1")
	public void removeSegmentById(Long id);
}
