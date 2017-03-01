package project.service;

import java.util.List;

import project.domain.RestTable;

public interface TableService {
	RestTable addTable(RestTable table);
	
	List<RestTable> getTablesBySegmentId(Long id);
	
	RestTable getTableByCode(String code);
	
	void deleteTableByCode(String code);
	
	RestTable getTableById(Long id);
}
