package project.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import project.domain.User;

public interface UserService {
	
	Page<User> findUsers();
	
	User getUser(String email);
	
	User getUserById(Long id);
}
