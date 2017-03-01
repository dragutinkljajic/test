package project.repository;

import org.springframework.data.repository.Repository;

import project.domain.WorkSchedule;

public interface WorkScheduleRepository extends Repository<WorkSchedule, Long>{
	public WorkSchedule save(WorkSchedule workSchedule);
}
