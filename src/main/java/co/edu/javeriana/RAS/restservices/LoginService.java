package co.edu.javeriana.RAS.restservices;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
import co.edu.javeriana.RAS.security.ErrorMessage;
import co.edu.javeriana.RAS.security.JWTUtils;
import co.edu.javeriana.RAS.security.KeyStore;

@RestController
public class LoginService {
	
	@Autowired
	private KeyStore keyStore;
	
	@Autowired
	private JWTUtils jwtUtils;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private HealthEntityRepository healthEntityRepository;
	
	@GetMapping("/public-key")
	public String getPublicKey (){
		return Base64.getEncoder().encodeToString(keyStore.getPublicKey().getEncoded());
	}
	
	@PostMapping("/login-password")
	public ResponseEntity<Object> loginPassword(@RequestBody LoginPasswordForm form) throws NoSuchAlgorithmException {
		User user = userRepository.getUserByIdentificationNumberAndPassword(form.getIdentificationType(), 
				form.getIdentificationNumber(), form.getPassword());
		HealthEntity healthEntity = healthEntityRepository.getById(form.getHealthEntityId());
		String token = null;
		if (user != null && healthEntity != null) {
			token = jwtUtils.getJWTToken(user, healthEntity,
				AuthenticationModeEnum.PASSWORD_AND_FINGERPRINT_AUTHENTICATION);
			user.setToken(token);
		}
		else {
			return new ResponseEntity<>(ErrorMessage.INVALID_CREDENTIALS, HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<>(user, HttpStatus.OK);		
	}
	
	
	@PostMapping("/login-password-and-fingerprint")
	public ResponseEntity<Object> loginPasswordAndFingerprint(@RequestBody LoginPasswordFingerprintForm form) throws NoSuchAlgorithmException {
		
		User user = userRepository.getUserByIdentificationNumberPasswordAndFingerprint(form.getIdentificationType(), 
				form.getIdentificationNumber(), form.getPassword(), form.getFingerprint());
		HealthEntity healthEntity = healthEntityRepository.getById(form.getHealthEntityId());
		String token = null;
		if (user != null) {
			token = jwtUtils.getJWTToken(user, healthEntity,
				AuthenticationModeEnum.PASSWORD_AND_FINGERPRINT_AUTHENTICATION);
			user.setToken(token);
		}	
		else {
			return new ResponseEntity<>(ErrorMessage.INVALID_CREDENTIALS, HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<>(user, HttpStatus.OK);	
	}
	
	
		
}
