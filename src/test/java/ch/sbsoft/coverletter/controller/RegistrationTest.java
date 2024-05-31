package ch.sbsoft.coverletter.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

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
class RegistrationTest {

	@Autowired
	private MockMvc mvc;
	@Autowired
	private UserService userService;

	// Registration
	@Test
	@WithAnonymousUser
	void getRegistrationAnonymous() throws Exception {
		mvc.perform(get(MappingPath.REGISTRATION)).andExpect(status().isOk()).andExpect(content().string(containsString("Registration and Login System")));
	}

	@Test
	@WithMockUser
	void getRegistrationUserRole() throws Exception {
		mvc.perform(get(MappingPath.REGISTRATION)).andExpect(status().isOk()).andExpect(content().string(containsString("Registration and Login System")));
	}

	@Test
	void postRegistration() throws Exception {
		mvc.perform(post(MappingPath.REGISTRATION).with(csrf())
				                                  .param("FirstName", "First")
				                                  .param("LastName", "Last")
				                                  .param("username", "first.last@test.com")
				                                  .param("password", "pass")
				                                  .param("passwordVerify", "pass")).andExpect(status().is2xxSuccessful());
		User user = userService.findUserByUsername("first.last@test.com");
		assertNotNull(user);
		assertEquals("First", user.getFirstName());
	}
}
