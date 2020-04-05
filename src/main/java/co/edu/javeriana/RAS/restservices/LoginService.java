package co.edu.javeriana.RAS.restservices;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.javeriana.RAS.entitys.GenderEnum;
import co.edu.javeriana.RAS.entitys.HealthEntity;
import co.edu.javeriana.RAS.entitys.IdentificationTypeEnum;
import co.edu.javeriana.RAS.entitys.Person;
import co.edu.javeriana.RAS.entitys.User;
import co.edu.javeriana.RAS.forms.LoginPasswordFingerprintForm;
import co.edu.javeriana.RAS.forms.LoginPasswordForm;
import co.edu.javeriana.RAS.repositories.HealthEntityRepository;
import co.edu.javeriana.RAS.repositories.UserRepository;
import co.edu.javeriana.RAS.security.AuthenticationModeEnum;
import co.edu.javeriana.RAS.security.jwt.JWTUtils;

@RestController
public class LoginService {
	
	@Autowired
	private JWTUtils jwtUtils;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private HealthEntityRepository healthEntityRepository;
	
	@PostMapping("/login-password")
	public User loginPassword(
			@RequestBody(required = false) LoginPasswordForm form) throws NoSuchAlgorithmException {
		User user = userRepository.getUserByIdentificationNumberAndPassword(form.getIdentificationType(), 
				form.getIdentificationNumber(), form.getPassword());
		HealthEntity healthEntity = healthEntityRepository.findById(form.getHealthEntityId()).get();
		String token = null;
		if (user != null && healthEntity != null) {
			token = jwtUtils.getJWTToken(user, healthEntity,
				AuthenticationModeEnum.PASSWORD_AND_FINGERPRINT_AUTHENTICATION);
			user.setToken(token);
		}
		return user;		
	}
	
	
//	@PostMapping("/login-password_and_fingerprint")
//	public User loginPasswordAndFingerprint(
//			@RequestBody(required = false) LoginPasswordFingerprintForm form) throws NoSuchAlgorithmException {
//		
//		User user = userRepository.getUserByIdentificationNumberPasswordAndFingerprint(form.getIdentificationType(), 
//				form.getIdentificationNumber(), form.getPassword(), form.getFingerprint());
//		String token = null;
//		if (user != null) {
//			token = jwtUtils.getJWTToken(user, form.getHealthEntityId(),
//				AuthenticationModeEnum.PASSWORD_AND_FINGERPRINT_AUTHENTICATION);
//			user.setToken(token);
//		}	
//		return user;		
//	}
		
}
