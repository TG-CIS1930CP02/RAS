package co.edu.javeriana.RAS.restservices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import co.edu.javeriana.RAS.entitys.Authorization;
import co.edu.javeriana.RAS.entitys.HealthEntity;
import co.edu.javeriana.RAS.entitys.IdentificationTypeEnum;
import co.edu.javeriana.RAS.entitys.RoleEnum;
import co.edu.javeriana.RAS.entitys.User;
import co.edu.javeriana.RAS.repositories.AuthorizationRepository;
import co.edu.javeriana.RAS.repositories.HealthEntityRepository;
import co.edu.javeriana.RAS.repositories.UserRepository;
import co.edu.javeriana.RAS.responses.TokenAuthorization;
import co.edu.javeriana.RAS.security.AuthenticationModeEnum;
import co.edu.javeriana.RAS.security.JWTTokenProcessor;
import co.edu.javeriana.RAS.security.JWTUtils;

@RestController
public class AuthorizationService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private HealthEntityRepository healthEntityRepository;
	
	@Autowired
	private AuthorizationRepository authorizationRepository;
	
	@Autowired
	private JWTTokenProcessor jwtTokenProcessor;
	
	@Autowired
	private JWTUtils jwtUtils;
	
	@GetMapping(path = "/authorization/patient/{identificationTypePatient}/{identificationNumberPatient}")
	public ResponseEntity<TokenAuthorization> getAuthorizationTokenForPatientInformation(@PathVariable IdentificationTypeEnum identificationTypePatient, @PathVariable Long identificationNumberPatient, @RequestHeader("Authorization") String token){
		
		Long healthEntityId = Long.valueOf(jwtTokenProcessor.getInformationFromToken(token, JWTTokenProcessor.HEALTH_ENTITY_ID));
		IdentificationTypeEnum identificationType = IdentificationTypeEnum.valueOf(jwtTokenProcessor.getInformationFromToken(token, JWTTokenProcessor.IDENTIFICATION_TYPE));
		Long identificationNumber = Long.valueOf(jwtTokenProcessor.getInformationFromToken(token, JWTTokenProcessor.IDENTIFICATION_NUMBER));
		
		HealthEntity healthEntity = healthEntityRepository.getById(healthEntityId);
		User user = userRepository.getUserByIdentification(identificationType, identificationNumber);
		User patient = userRepository.getUserByIdentification(identificationTypePatient, identificationNumberPatient);
		
		if (healthEntity != null && patient != null) {
			Authorization authorization = authorizationRepository.getAuthorization(patient, healthEntity, RoleEnum.ROLE_PATIENT);
			if (authorization != null) {
				AuthenticationModeEnum authenticationMode = AuthenticationModeEnum.valueOf(jwtTokenProcessor.getInformationFromToken(token, JWTTokenProcessor.AUTHENTICATION_MODE));
				return new ResponseEntity<>(new TokenAuthorization(jwtUtils.getJWTTokenForPatientInformation(user, healthEntity, authenticationMode, patient)), HttpStatus.OK);
			}
			else {
				return new ResponseEntity<TokenAuthorization>(HttpStatus.FORBIDDEN);
			}
		}
		else {
			return new ResponseEntity<TokenAuthorization>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping(path = "/user/{identificationType}/{identificationNumber}/authorization/role_doctor/{healthEntityId}", consumes = "application/json")
	public ResponseEntity<Object> authorizateDoctor(@PathVariable IdentificationTypeEnum identificationType, @PathVariable Long identificationNumber, @PathVariable Long healthEntityId, 
			@RequestBody String fingerprint){
		User user = userRepository.getUserByIdentificationAndFingerprint(identificationType, identificationNumber, fingerprint);
		HealthEntity healthEntity = healthEntityRepository.getById(healthEntityId);
		if(user != null && healthEntity != null) {
			List<String> roles = authorizationRepository.getRoles(user, healthEntity);
			if (roles.contains(RoleEnum.ROLE_DOCTOR.toString()))
				return new ResponseEntity<Object>(HttpStatus.CONFLICT);			
			Authorization authorization = new Authorization();
			authorization.setHealthEntity(healthEntity);
			authorization.setRole(RoleEnum.ROLE_DOCTOR);
			authorization.setUser(user);
			authorizationRepository.save(authorization);
			return new ResponseEntity<Object>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping(path = "/user/{identificationType}/{identificationNumber}/authorization/role_patient/{healthEntityId}", consumes = "application/json")
	public ResponseEntity<Object> authorizatePatient(@PathVariable IdentificationTypeEnum identificationType, @PathVariable Long identificationNumber, @PathVariable Long healthEntityId, 
			@RequestBody String fingerprint){
		User user = userRepository.getUserByIdentificationAndFingerprint(identificationType, identificationNumber, fingerprint);
		HealthEntity healthEntity = healthEntityRepository.getById(healthEntityId);
		if(user != null && healthEntity != null) {
			List<String> roles = authorizationRepository.getRoles(user, healthEntity);
			if (roles.contains(RoleEnum.ROLE_PATIENT.toString()))
				return new ResponseEntity<Object>(HttpStatus.CONFLICT);	
			Authorization authorization = new Authorization();
			authorization.setHealthEntity(healthEntity);
			authorization.setRole(RoleEnum.ROLE_PATIENT);
			authorization.setUser(user);
			authorizationRepository.save(authorization);
			return new ResponseEntity<Object>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping(path = "/user/{identificationType}/{identificationNumber}/authorization/role_nurse/{healthEntityId}", consumes = "application/json")
	public ResponseEntity<Object> authorizateNurse(@PathVariable IdentificationTypeEnum identificationType, @PathVariable Long identificationNumber, @PathVariable Long healthEntityId, 
			@RequestBody String fingerprint){
		User user = userRepository.getUserByIdentificationAndFingerprint(identificationType, identificationNumber, fingerprint);
		HealthEntity healthEntity = healthEntityRepository.getById(healthEntityId);
		if(user != null && healthEntity != null) {
			List<String> roles = authorizationRepository.getRoles(user, healthEntity);
			if (roles.contains(RoleEnum.ROLE_NURSE.toString()))
				return new ResponseEntity<Object>(HttpStatus.CONFLICT);	
			Authorization authorization = new Authorization();
			authorization.setHealthEntity(healthEntity);
			authorization.setRole(RoleEnum.ROLE_NURSE);
			authorization.setUser(user);
			authorizationRepository.save(authorization);
			return new ResponseEntity<Object>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping(path = "/user/{identificationType}/{identificationNumber}/authorization/role_administrative_assistant/{healthEntityId}", consumes = "application/json")
	public ResponseEntity<Object> authorizateAdministrativeAssistant(@PathVariable IdentificationTypeEnum identificationType, @PathVariable Long identificationNumber, @PathVariable Long healthEntityId, 
			@RequestBody String fingerprint){
		User user = userRepository.getUserByIdentificationAndFingerprint(identificationType, identificationNumber, fingerprint);
		HealthEntity healthEntity = healthEntityRepository.getById(healthEntityId);
		if(user != null && healthEntity != null) {
			List<String> roles = authorizationRepository.getRoles(user, healthEntity);
			if (roles.contains(RoleEnum.ROLE_ADMINISTRATIVE_ASSISTANT.toString()))
				return new ResponseEntity<Object>(HttpStatus.CONFLICT);	
			Authorization authorization = new Authorization();
			authorization.setHealthEntity(healthEntity);
			authorization.setRole(RoleEnum.ROLE_ADMINISTRATIVE_ASSISTANT);
			authorization.setUser(user);
			authorizationRepository.save(authorization);
			return new ResponseEntity<Object>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@GetMapping(path = "authorization/health-entity/{healthEntityId}")
	public ResponseEntity<List<Authorization>> getAuthorizations(@PathVariable Long healthEntityId){
		return ResponseEntity.ok(authorizationRepository.getAuthorizations(healthEntityId));		
	}
	
	@DeleteMapping(path = "authorization/health-entity/{health_entity_id}/{identificationType}/{identificationNumber}/{role}")
	public ResponseEntity<Object> deleteAuthorization(@PathVariable Long healthEntityId, 
			@PathVariable IdentificationTypeEnum identificationType, @PathVariable Long identificationNumber, @PathVariable RoleEnum role){
		User user = userRepository.getUserByIdentification(identificationType, identificationNumber);
		HealthEntity healthEntity = healthEntityRepository.getById(healthEntityId);
		Authorization authorization = authorizationRepository.getAuthorization(user, healthEntity, role);
		if (authorization != null) {
			authorizationRepository.delete(authorization);
			return new ResponseEntity<Object>(null, HttpStatus.OK);
		}
		return new ResponseEntity<Object>(null, HttpStatus.NOT_FOUND);
	}

}
