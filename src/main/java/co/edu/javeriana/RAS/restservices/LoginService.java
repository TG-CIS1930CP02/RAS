package co.edu.javeriana.RAS.restservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.javeriana.RAS.entitys.IdentificationTypeEnum;
import co.edu.javeriana.RAS.entitys.User;
import co.edu.javeriana.RAS.repositories.UserRepository;
import co.edu.javeriana.RAS.security.AuthenticationModeEnum;
import co.edu.javeriana.RAS.security.jwt.JWTUtils;

@RestController
public class LoginService {
	
	@Autowired
	private JWTUtils jwtUtils;
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/login_password")
	public User loginPassword(
			@RequestParam(value = "healthEntityId", required = false) Long healthEntityId,
			@RequestParam(value = "identificationNumber", required = false) Long identificationNumber,
			@RequestParam(value = "password", required = false) String password) {
		
		User user = userRepository.getUserByIdentificationNumberAndPassword(identificationNumber, password);
		if (user != null) {
			String token = jwtUtils.getJWTToken(identificationNumber, healthEntityId,
				AuthenticationModeEnum.PASSWORD_AUTHENTICATION);
			user.setToken(token);
		}		
		return user;		
	}
	
	@PostMapping("/login_password_and_fingerprint")
	public User loginPasswordAndFingerprint(
			@RequestParam(value = "healthEntityId", required = false) Long healthEntityId,
			@RequestParam(value= "identificationNumber", required = false) Long identificationNumber,
			@RequestParam(value = "password", required = false) String password, 
			@RequestParam(value = "fingerprint", required = false) String fingerprint) {
		
		User user = userRepository.getUserByIdentificationNumberPasswordAndFingerprint(identificationNumber, password, fingerprint);
		if (user != null) {
			String token = jwtUtils.getJWTToken(identificationNumber, healthEntityId,
				AuthenticationModeEnum.PASSWORD_AND_FINGERPRINT_AUTHENTICATION);
			user.setToken(token);
		}	
		return user;		
	}
		
}
