package me.dio.service;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import me.dio.model.User;
import me.dio.repository.UserRepository;
import me.dio.service.impl.UserService;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	
	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public User findById(Long id) {
		return userRepository.findById(id).orElseThrow(NoSuchElementException::new);
	}

	@Override
	public User create(User userToCreate) {
		if(userRepository.existsByAccountNumber(userToCreate.getAccount().getNumber())) {
			throw new IllegalArgumentException("This User iD already exisits.");
		}
		return userRepository.save(userToCreate);
	}

}
