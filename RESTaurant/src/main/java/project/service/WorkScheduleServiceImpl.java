package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.domain.WorkSchedule;
import project.repository.WorkScheduleRepository;

@Service
public class WorkScheduleServiceImpl implements WorkScheduleService{
	@Autowired
	private WorkScheduleRepository workScheduleRepository;

	@Override
	public WorkSchedule addWorkSchedule(WorkSchedule workSchedule) {
		return workScheduleRepository.save(workSchedule);
	}
}
