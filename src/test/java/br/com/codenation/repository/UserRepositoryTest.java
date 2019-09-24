package br.com.codenation.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.codenation.entity.User;

@Sql(value = "/load-database.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/clean-database.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
public class UserRepositoryTest {
	
	private static final String EMAIL = "user@user.com";
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void mustSearchUserByEmail() {
		Optional<User> optional = userRepository.findByEmail(EMAIL);
		
		assertThat(optional.isPresent()).isTrue();
		
		User user = optional.get();
		System.out.println(user);
		assertThat(user.getId()).isEqualTo(2L);
		assertThat(user.getEmail()).isEqualTo(EMAIL);
		assertThat(user.getPassword()).isEqualTo("654321");
	}
	
	@Test
	public void shouldNotFindUserWithNonexistentEmail() {
		Optional<User> optional = userRepository.findByEmail("teste@email.com");
		
		assertThat(optional.isPresent()).isFalse();
	}
	
	@Test
	public void mustRegisterUserInTheDatabase() {
		User user = new User();
		user.setEmail("novo@email.com");
		user.setPassword("senha");
		
		userRepository.save(user);
		
		assertThat(user.getId()).isEqualTo(3L);
	}
	
}
