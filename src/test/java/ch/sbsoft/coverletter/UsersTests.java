package ch.sbsoft.coverletter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import ch.sbsoft.coverletter.users.User;
import ch.sbsoft.coverletter.users.UserService;

@SpringBootTest
class UsersTests {

	@Autowired
	private UserService userService;

	
	@Test
	void testValidObjects() {
		assertNotNull(userService);
		assert(userService instanceof UserDetailsService);

	}

	@Test
	void testFindUserByUsername() {
		User user = userService.findUserByUsername("admin@test.com");
		assert(user.getUsername().equalsIgnoreCase("admin@test.com"));
		assertEquals("Test", user.getFirstName());
		assertEquals("Admin", user.getLastName());
	}

	@Test
	void testFindUserByEmailNotFound() {
		try {
			userService.findUserByUsername("nouser@test.com");
			fail("Expected exception was not thrown");
		} catch (UsernameNotFoundException e) {
			// Assert that the exception message is correct
			assertEquals("Could not find user", e.getMessage());
		}
	}

	@Test
	void testLoadUserByUsername() {
		UserDetails user = userService.findUserByUsername("admin@test.com");
		assert(user instanceof UserDetails);
		assert(user.getUsername().equalsIgnoreCase("admin@test.com"));
	}
	
	@Test
	void testLoadUserByUsernameNotFound() {
		try {
			userService.findUserByUsername("nouser@test.com");
			fail("Expected exception was not thrown");
		} catch (UsernameNotFoundException e) {
			// Assert that the exception message is correct
			assertEquals("Could not find user", e.getMessage());
		}
	}
}
