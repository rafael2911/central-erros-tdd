package br.com.codenation.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.codenation.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);

}
