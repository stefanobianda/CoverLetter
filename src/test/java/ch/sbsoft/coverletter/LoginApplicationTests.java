package ch.sbsoft.coverletter;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import ch.sbsoft.coverletter.specification.MappingPath;
import ch.sbsoft.coverletter.users.User;
import ch.sbsoft.coverletter.users.UserService;

@SpringBootTest
@AutoConfigureMockMvc
class LoginApplicationTests {

	@Autowired
	private MockMvc mvc;
	@Autowired
	WebApplicationContext context;
	@Autowired
	UserService userService;

	@BeforeEach
	void setup() {
		// H2DB: Password is always different
		User user = userService.findUserByUsername("admin@test.com");
		user.setPassword("password");
		userService.update(user);
	}
	
	// Login
	@Test
	@WithAnonymousUser
	void getLoginAnonymousMock() throws Exception {
		mvc.perform(get(MappingPath.LOGIN_URL)).andExpect(status().isOk()).andExpect(content().string(containsString("Please sign in")));
	}

	@Test
	@WithMockUser
	void getLoginUserRoleMock() throws Exception {
		mvc.perform(get(MappingPath.LOGIN_URL)).andExpect(status().isOk()).andExpect(content().string(containsString("Please sign in")));
	}
	
	@Test
	void performLoginValidUserMock() throws Exception {
		mvc.perform(formLogin().user("admin@test.com").password("password")).andExpect(authenticated());
	}

	@Test
	void performLoginWrongUserMock() throws Exception {
		mvc.perform(formLogin().user("wrong").password("pass")).andExpect(redirectedUrl("/login?error")).andExpect(status().isFound());
	}

	@Test
	void performLoginWrongPasswordMock() throws Exception {
		mvc.perform(formLogin().user("user").password("wrong")).andExpect(redirectedUrl("/login?error")).andExpect(status().isFound());
	}

	// Logout
	@Test
	@WithAnonymousUser
	void getLogoutAnonymousMock() throws Exception {
		mvc.perform(get(MappingPath.LOGOUT_URL)).andExpect(status().isOk()).andExpect(content().string(containsString("Are you sure you want to log out")));
	}

	@Test
	@WithMockUser
	void getLogoutUserRoleMock() throws Exception {
		mvc.perform(get(MappingPath.LOGOUT_URL)).andExpect(status().isOk()).andExpect(content().string(containsString("Are you sure you want to log out")));
	}
	

}
