package in.ashokit.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.bindings.LoginForm;
import in.ashokit.service.UserManagementService;

@RestController

public class LoginRestController {
	@Autowired
	private UserManagementService service;
	
	@PostMapping("/login")
	public String login(@RequestBody LoginForm form) {
		return service.login(form);
	}

}
