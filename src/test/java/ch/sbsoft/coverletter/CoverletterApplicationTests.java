package ch.sbsoft.coverletter;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import ch.sbsoft.coverletter.specification.MappingPath;

@SpringBootTest
@AutoConfigureMockMvc
@ImportAutoConfiguration(ThymeleafAutoConfiguration.class)
class CoverletterApplicationTests {

	@Autowired
	private MockMvc mvc;
	
	@Test
	@WithAnonymousUser
	void getIndexAnonymousMock() throws Exception {
		mvc.perform(get(MappingPath.HOME)).andExpect(status().isOk()).andExpect(content().string(containsString("Welcome to Cover Letter Generator")));
		mvc.perform(get(MappingPath.INDEX)).andExpect(status().isOk()).andExpect(content().string(containsString("Welcome to Cover Letter Generator")));
	}

	@Test
	@WithMockUser
	void getIndexUserRoleMock() throws Exception {
		mvc.perform(get(MappingPath.HOME)).andExpect(status().isOk()).andExpect(content().string(containsString("Welcome back to Cover Letter Generator")));
		mvc.perform(get(MappingPath.INDEX)).andExpect(status().isOk()).andExpect(content().string(containsString("Welcome back to Cover Letter Generator")));
	}

}
