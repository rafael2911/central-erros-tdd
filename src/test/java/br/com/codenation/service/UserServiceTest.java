package br.com.codenation.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.codenation.entity.User;
import br.com.codenation.repository.UserRepository;
import br.com.codenation.service.exception.DuplicateEmailException;
import br.com.codenation.service.impl.UserService;
import br.com.codenation.service.interfaces.UserServiceInterface;

@RunWith(SpringRunner.class)
public class UserServiceTest {
	
	private static final String PASSWORD = "123456";

	private static final String EMAIL = "admin@admin.com";

	@MockBean
	private UserRepository userRepository;
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	private UserServiceInterface userService;

	private User user;
	
	@Before
	public void setUp() {
		userService = new UserService(userRepository);
		
		user = new User();
		user.setEmail(EMAIL);
		user.setPassword(PASSWORD);
		
		when(userRepository.findByEmail(EMAIL)).thenReturn(Optional.empty());
	}
	
	@Test
	public void mustSaveUserToRepository() {
		userService.toSave(user);
		
		verify(userRepository).save(user);
	}
	
	@Test
	public void shouldNotSaveTwoUsersWithTheSameEmail() {
		when(userRepository.findByEmail(EMAIL)).thenReturn(Optional.of(user));
		
		expectedException.expect(DuplicateEmailException.class);
		expectedException.expectMessage("E-mail already registered");
		
		userService.toSave(user);
	}
	
}
