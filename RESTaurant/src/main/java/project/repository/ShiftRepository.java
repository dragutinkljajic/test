package project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import project.domain.Shift;

public interface ShiftRepository extends Repository<Shift, Long>{
	public Shift save(Shift shift);
	
	@Query("select s from Shift s where s.schedule.idSchedule = ?1")
	public List<Shift> findShiftsByWorkScheduleId(Long id);
	
	@Query("select s from Shift s where s.idShift = ?1")
	public Shift findShiftById(Long id);
	
	@Modifying
	@Query("delete from Shift s where s.idShift = ?1")
	public void removeShiftById(Long id);
}
