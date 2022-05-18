package in.ashokit.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.bindings.UnlockForm;
import in.ashokit.service.UserManagementService;
@RestController
public class ForgotPwdRestController {
	
	@Autowired
	private UserManagementService service;
	
	@PostMapping("/forgotPwd/(email)")
	public String forgotPwd(@PathVariable String email) {
		return service.forgotPwd(email);
	}	  

}
