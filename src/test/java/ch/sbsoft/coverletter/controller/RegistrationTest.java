package ch.sbsoft.coverletter.controller;

import static org.hamcrest.CoreMatchers.containsString;
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

@SpringBootTest
@AutoConfigureMockMvc
class RegistrationTest {

	@Autowired
	private MockMvc mvc;
	@Autowired
	WebApplicationContext context;

	// Registration
	@Test
	@WithAnonymousUser
	void getRegistrationAnonymous() throws Exception {
		mvc.perform(get(MappingPath.REGISTRATION)).andExpect(status().isOk()).andExpect(content().string(containsString("Please register")));
	}

	@Test
	@WithMockUser
	void getRegistrationUserRole() throws Exception {
		mvc.perform(get(MappingPath.REGISTRATION)).andExpect(status().isOk()).andExpect(content().string(containsString("Please register")));
	}

	@Test
	void postRegistration() throws Exception {
		mvc.perform(post(MappingPath.REGISTRATION).with(csrf())
				                                  .param("FirstName", "First")
				                                  .param("LastName", "Last")
				                                  .param("username", "first.last@test.com")
				                                  .param("password", "pass")
				                                  .param("passwordVerify", "pass")).andExpect(status().isCreated());
	}
}
