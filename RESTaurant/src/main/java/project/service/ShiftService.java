package project.service;

import java.util.List;

import project.domain.Shift;

public interface ShiftService {
	Shift addShift(Shift shift);
	
	List<Shift> getShiftsByWorkScheduleId(Long id);
	
	void deleteShiftById(Long id);
	
	Shift getShiftById(Long id);
}
