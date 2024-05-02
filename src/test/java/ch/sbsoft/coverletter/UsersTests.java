package ch.sbsoft.coverletter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import ch.sbsoft.coverletter.users.User;
import ch.sbsoft.coverletter.users.UserRepository;
import ch.sbsoft.coverletter.users.UserService;

@ExtendWith(MockitoExtension.class)
class UsersTests {

	@Mock
	private UserRepository userRepositoryMock;

	@InjectMocks
	private UserService userService;

	
	@Test
	void testValidObjects() {
		assertNotNull(userService);
		assert(userService instanceof UserDetailsService);

	}

		@Test
	void testFindUserByUsername() {
		when(userRepositoryMock.findByUsername("stefanobianda@ik.me")).thenReturn(Optional.of(new User((long) 12,"Stefano", "Bianda", "stefanobianda@ik.me", "password", true, true, true, true, null)));
		User user = userService.findUserByUsername("stefanobianda@ik.me");
		assert(user.getUsername().equalsIgnoreCase("stefanobianda@ik.me"));

	}

	@Test
	void testFindUserByEmailNotFound() {
		when(userRepositoryMock.findByUsername("stefanobiand@ik.me")).thenThrow(new UsernameNotFoundException("Could not find user"));
		try {
			userService.findUserByUsername("stefanobiand@ik.me");
			fail("Expected exception was not thrown");
		} catch (UsernameNotFoundException e) {
			// Assert that the exception message is correct
			assertEquals("Could not find user", e.getMessage());
		}
	}

	
}
