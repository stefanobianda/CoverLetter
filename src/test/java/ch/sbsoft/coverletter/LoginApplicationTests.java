package ch.sbsoft.coverletter;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import ch.sbsoft.coverletter.specification.MappingPath;

@SpringBootTest
@AutoConfigureMockMvc
class LoginApplicationTests {

	@Autowired
	private MockMvc mvc;
	
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
		mvc.perform(formLogin().user("user").password("password")).andExpect(redirectedUrl("/")).andExpect(status().isFound());
	}

	@Test
	void performLoginWrongUserMock() throws Exception {
		mvc.perform(formLogin().user("wrong").password("password")).andExpect(redirectedUrl("/login?error")).andExpect(status().isFound());
	}

	@Test
	void performLoginWrongPasswordMock() throws Exception {
		mvc.perform(formLogin().user("user").password("wrong")).andExpect(redirectedUrl("/login?error")).andExpect(status().isFound());
	}


}
