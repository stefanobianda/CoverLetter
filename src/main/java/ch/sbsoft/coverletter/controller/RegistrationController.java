package ch.sbsoft.coverletter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import ch.sbsoft.coverletter.specification.MappingPage;
import ch.sbsoft.coverletter.specification.MappingPath;

@Controller
public class RegistrationController {

	@GetMapping(MappingPath.REGISTRATION)
	public String registration() {
		return MappingPage.REGISTER;
	}

}
