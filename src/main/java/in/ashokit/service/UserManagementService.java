package in.ashokit.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import in.ashokit.bindings.LoginForm;
import in.ashokit.bindings.UnlockForm;
import in.ashokit.bindings.UserForm;

@Service
public interface UserManagementService {
	
	public String login(LoginForm loginForm);
	
	public String emailCheck(String emailId);
	
	public String registerUser(UserForm userForm);
	
	public String unlockAccount(UnlockForm unlockAccForm);
	
	public String forgotPwd(String emailId);
	
    Map<Integer, String> getCountry();
    
    Map<Integer, String> getState(int countryId);
    
    Map<Integer, String> getCity(int stateid);

	Map<Integer, String> loadCountries();

	Map<Integer, String> loadStates(Integer countryId);

	public Map<Integer, String> loadCities(Integer stateId);

	
    
}
