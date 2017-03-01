package project.service;

import org.springframework.data.domain.Page;

import project.domain.Online;
import project.domain.User;

public interface OnlineService {

	Page<Online> getAll();
	
	Online getOnline(Long id);
	
	Online addOnline(Online online);
	
	void deleteOnline(Online online);
	
	Online getUser(Long usr_id);
	
	void deleteUser(Long usr_id);
	

}
