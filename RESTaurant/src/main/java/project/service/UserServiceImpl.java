package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import project.domain.User;
import project.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Page<User> findUsers() {

		return this.userRepository.findAll(new PageRequest(0, 20));
	}

	@Override
	public User getUser(String email) {
		Assert.notNull(email, "Email cannot be null.");
		return this.userRepository.findUser(email);
	}

	@Override
	public User getUserById(Long id) {
		Assert.notNull(id);
		return this.userRepository.findUserById(id);
	}

}
