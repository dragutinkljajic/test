package project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.domain.Segment;
import project.repository.SegmentRepository;

@Service
public class SegmentServiceImpl implements SegmentService{
	
	@Autowired
	private SegmentRepository segmentRepository;
	
	@Override
	public Segment addSegment(Segment segment) {
		return segmentRepository.save(segment);
	}

	@Override
	public List<Segment> getSegmentsBySeatingId(Long id) {
		return segmentRepository.findSemgnetsBySeatingId(id);
	}

	@Override
	public Segment getSegmentByLabel(String label) {
		return segmentRepository.findSegmentByLabel(label);
	}

	@Override
	public void deleteSegmentById(Long id) {
		segmentRepository.removeSegmentById(id);
	}

}
