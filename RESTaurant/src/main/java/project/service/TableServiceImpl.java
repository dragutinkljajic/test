package project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.domain.RestTable;
import project.repository.TableRepository;

@Service
public class TableServiceImpl implements TableService{
	
	@Autowired
	private TableRepository tableRepository;
	
	@Override
	public RestTable addTable(RestTable table) {
		return tableRepository.save(table);
	}

	@Override
	public List<RestTable> getTablesBySegmentId(Long id) {
		return tableRepository.findTablesBySegmentId(id);
	}

	@Override
	public RestTable getTableByCode(String code) {
		return tableRepository.findTableByCode(code);
	}

	@Override
	public void deleteTableByCode(String code) {
		tableRepository.removeTableByCode(code);
	}

	@Override
	public RestTable getTableById(Long id) {
		return tableRepository.findTableById(id);
	}

	
}
