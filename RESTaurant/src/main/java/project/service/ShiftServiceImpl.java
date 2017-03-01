package project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.domain.Shift;
import project.repository.ShiftRepository;

@Service
public class ShiftServiceImpl implements ShiftService{

	@Autowired
	private ShiftRepository shiftRepository;

	@Override
	public Shift addShift(Shift shift) {
		return shiftRepository.save(shift);
	}

	@Override
	public List<Shift> getShiftsByWorkScheduleId(Long id) {
		return shiftRepository.findShiftsByWorkScheduleId(id);
	}

	@Override
	public void deleteShiftById(Long id) {
		shiftRepository.removeShiftById(id);
	}

	@Override
	public Shift getShiftById(Long id) {
		return shiftRepository.findShiftById(id);
	}
}
