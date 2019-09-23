package br.com.codenation.service.impl;

import java.util.Optional;

import br.com.codenation.entity.User;
import br.com.codenation.repository.UserRepository;
import br.com.codenation.service.exception.DuplicateEmailException;
import br.com.codenation.service.interfaces.UserServiceInterface;

public class UserService implements UserServiceInterface {

	private UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User toSave(User user) {
		Optional<User> optional = this.userRepository.findByEmail(user.getEmail());
		
		if(optional.isPresent()) {
			throw new DuplicateEmailException("E-mail already registered");
		}
		
		return this.userRepository.save(user);
	}

}
