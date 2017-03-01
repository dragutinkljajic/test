package project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import project.domain.RestTable;

public interface TableRepository extends Repository<RestTable, Long>{
	public RestTable save(RestTable table);
	
	@Query("select t from RestTable t where t.segment.idSegment = ?1")
	public List<RestTable> findTablesBySegmentId(Long id);
	
	@Query("select t from RestTable t where t.tableCode =?1")
	public RestTable findTableByCode(String code);
	
	@Modifying
	@Query("delete from RestTable t where t.tableCode = ?1")
	public void removeTableByCode(String code);
	
	@Query("select t from RestTable t where t.idTable = ?1")
	public RestTable findTableById(Long id);
}
