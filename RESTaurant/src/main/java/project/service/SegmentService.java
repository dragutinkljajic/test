package project.service;

import java.util.List;

import project.domain.Segment;

public interface SegmentService {
	Segment addSegment(Segment segment);
	
	List<Segment> getSegmentsBySeatingId(Long id);
	
	Segment getSegmentByLabel(String label);
	
	void deleteSegmentById(Long id);
}
