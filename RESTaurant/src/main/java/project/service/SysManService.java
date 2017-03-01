package project.service;

import java.util.List;

import org.springframework.data.domain.Page;

import project.domain.Online;
import project.domain.SystemManager;

public interface SysManService {
	
	List<SystemManager> getAll();
	
	SystemManager addSystemManager(SystemManager sm);
	
}
