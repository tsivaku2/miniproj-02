package in.ashokit.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import in.ashokit.Util.EmailUtils;
import in.ashokit.bindings.LoginForm;
import in.ashokit.bindings.UnlockForm;
import in.ashokit.bindings.UserForm;
import in.ashokit.entities.CitiesMasterEntity;
import in.ashokit.entities.CountryMasterEntity;
import in.ashokit.entities.StateMasterEntity;
import in.ashokit.entities.UserAccountEntity;
import in.ashokit.repository.CityRepository;
import in.ashokit.repository.CountryRepository;
import in.ashokit.repository.StateRepository;
import in.ashokit.repository.UserAccountRepository;

public class UserMgmtServiceImpl implements UserManagementService {

	@Autowired
	private UserAccountRepository userRepo;

	@Autowired
	private CountryRepository countryRepo;

	@Autowired
	private StateRepository stateRepo;

	@Autowired
	private CityRepository cityRepo;

	@Autowired
	private EmailUtils emailUtils;

	@Override
	public String login(LoginForm loginForm) {

		UserAccountEntity entity = userRepo.findByEmailAndPassword(loginForm.getEmail(), loginForm.getPwd());

		if (entity == null) {

			return "Invalid Credentials";
		}

		if (entity != null && entity.getAccstatus().equals("LOCKED")) {

			return "Your Account Locked";

		}
		return "SUCCESS";

	}

	@Override
	public String emailCheck(String emailId) {

		UserAccountEntity entity = userRepo.findByEmail(emailId);

		if (entity == null) {
			return "UNIQUE";

		}

		return "DUPLICATE";

	}

	@Override
	public Map<Integer, String> loadCountries() {
		java.util.List<CountryMasterEntity> countries = countryRepo.findAll();

		Map<Integer, String> countryMap = new HashMap<>();

		for (CountryMasterEntity entity : countries) {

			countryMap.put(entity.getCountryId(), entity.getCountryName());

		}

		return countryMap;

	}

	@Override
	public Map<Integer, String> loadStates(Integer countryId) {

		List<StateMasterEntity> states = stateRepo.findByCountryId(countryId);

		Map<Integer, String> stateMap = new HashMap<>();

		for (StateMasterEntity state : states) {

			stateMap.put(state.getStateId(), state.getStateName());

		}

		return stateMap;
	}

	@Override
	public Map<Integer, String> loadCities(Integer stateId) {

		Map<Integer, String> citiesMap = new HashMap<>();

		List<CitiesMasterEntity> cities = cityRepo.findBycityId(stateId);

		for (CitiesMasterEntity entity : cities) {

			citiesMap.put(entity.getCityId(), entity.getCityName());

		}

		return citiesMap;

	}

	@Override
	public String unlockAccount(UnlockForm unlockAccForm) {

		if (!(unlockAccForm.getNewPwd().equals(unlockAccForm.getConfirmNewPwd()))) {

			return "Password and Confirm Password Should be same";

		}

		UserAccountEntity entity = userRepo.findByEmailAndPassword(unlockAccForm.getEmail(),
				unlockAccForm.getTempPwd());

		if (entity == null) {

			return "Incorrect Temporary Password";

		}
		entity.setPassword(unlockAccForm.getNewPwd());
		entity.setAccstatus("UNLOCKED");

		userRepo.save(entity);

		return "Account unlocked";

	}

	@Override

	public String forgotPwd(String emailId) {

		UserAccountEntity entity = userRepo.findByEmail(emailId);

		if (entity == null) {

			return "Invalid Email Id";

		}

		String filename = "recovering pazzword";
		String body = readMailBodyContent(filename, entity);
		String subject = "Recover Password - Ashok IT";

		boolean isSent = emailUtils.sendEmail(emailId, subject, body);

		if (isSent) {
			return "Password sent to registered email";
		}

		return "ERROR";

	}

	public String generateRandomPwd() {
		int leftLimit = 48; // numeral '0'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 10;
		Random random = new Random();

		String generatedString = random.ints(leftLimit, rightLimit + 1)
				.filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)).limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

		return generatedString;
	}

	private String readMailBodyContent(String fileName, UserAccountEntity entity) {

		String mailBody = null;
		try {

			StringBuffer sb = new StringBuffer();

			FileReader fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);
			String line = br.readLine();

			while (line != null) {

				sb.append(line);
				line = br.readLine();

			}

			mailBody = sb.toString();

			mailBody = mailBody.replace("{FNAME}", entity.getFname());
			mailBody = mailBody.replace("{LNAME}", entity.getLname());
			mailBody = mailBody.replace("{TEMP.PWD}", entity.getPassword());
			mailBody = mailBody.replace("{EMAIL}", entity.getEmail());
			mailBody = mailBody.replace("{PWD}", entity.getPassword());
			br.close();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return mailBody;

	}

	@Override
	public java.lang.String registerUser(UserForm userForm) {
		UserAccountEntity entity = new UserAccountEntity();

		BeanUtils.copyProperties(userForm, entity);

		entity.setAccstatus("LOCKED");

		entity.setPassword(generateRandomPwd());

		userRepo.save(entity);

		String email = userForm.getEmail();
		String subject = "User Registration - Ashok It";
		String filename = "unlock acc file";
		String body = readMailBodyContent(filename, entity);

		boolean isSent = emailUtils.sendEmail(email, subject, body);

		if (entity.getUserId() != null && isSent) {
			return "SUCCESS";
		}

		return "FAIL";

	}

	@Override
	public Map<Integer, java.lang.String> getCountry() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Integer, java.lang.String> getState(int countryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Integer, java.lang.String> getCity(int stateid) {
		// TODO Auto-generated method stub
		return null;
	}
}
