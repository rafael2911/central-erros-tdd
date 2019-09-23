package br.com.codenation.service.interfaces;

import org.springframework.security.core.userdetails.UserDetailsService;

import br.com.codenation.entity.User;

public interface UserServiceInterface extends UserDetailsService {

	User toSave(User user);

}
