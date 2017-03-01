package project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import project.domain.SystemManager;
import project.repository.SysManRepository;

@Service
public class SysManServiceImpl implements SysManService{
	
	@Autowired
	private SysManRepository sysManRepository;
	
	@Override
	public SystemManager addSystemManager(SystemManager sm) {
		return this.sysManRepository.save(sm);
	}

	@Override
	public List<SystemManager> getAll() {
		return this.sysManRepository.findAll();
	}

}
