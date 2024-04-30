package ch.sbsoft.coverletter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import ch.sbsoft.coverletter.specification.MappingPage;
import ch.sbsoft.coverletter.specification.MappingPath;

@Controller
@SpringBootApplication
public class CoverletterApplication {

	// handler method to handle home page request
	@GetMapping(MappingPath.INDEX)
	public String index() {
		return MappingPage.INDEX;
	}
	@GetMapping("/")
	public String home() {
		return MappingPage.INDEX;
	}

	public static void main(String[] args) {
		SpringApplication.run(CoverletterApplication.class, args);
	}

}
