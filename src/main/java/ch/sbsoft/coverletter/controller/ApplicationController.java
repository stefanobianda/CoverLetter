package ch.sbsoft.coverletter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import ch.sbsoft.coverletter.specification.MappingPage;
import ch.sbsoft.coverletter.specification.MappingPath;

@Controller
public class ApplicationController {

	// handler method to handle home page request
	@GetMapping(MappingPath.INDEX)
	public String index() {
		return MappingPage.INDEX;
	}
	@GetMapping("/")
	public String home() {
		return MappingPage.INDEX;
	}
}
