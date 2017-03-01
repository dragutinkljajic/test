package project.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import project.domain.Online;
import project.domain.SystemManager;

public interface SysManRepository extends Repository<SystemManager, Long> {
	
	public List<SystemManager> findAll();
	
	public SystemManager save(SystemManager sm);
}
