package ch.sbsoft.coverletter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ch.qos.logback.core.model.Model;
import ch.sbsoft.coverletter.specification.MappingPage;
import ch.sbsoft.coverletter.specification.MappingPath;
import ch.sbsoft.coverletter.users.User;
import ch.sbsoft.coverletter.users.UserService;
import jakarta.validation.Valid;

@Controller
public class RegistrationController {

	@Autowired
	private UserService userService;
	
	@GetMapping(MappingPath.REGISTRATION)
	public String registration(ModelMap model) {
		model.addAttribute(new User());
		return MappingPage.REGISTER;
	}

	@PostMapping(MappingPath.REGISTRATION_SAVE)
	public String doingRegistration(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
		User created = userService.addUser(user);
		System.out.println("UserId: " + created.getId());
		return MappingPage.INDEX;
	}

}
