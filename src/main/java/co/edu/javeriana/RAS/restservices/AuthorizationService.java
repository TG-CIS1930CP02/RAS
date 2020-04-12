package co.edu.javeriana.RAS.restservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.edu.javeriana.RAS.entitys.Authorization;
import co.edu.javeriana.RAS.entitys.HealthEntity;
import co.edu.javeriana.RAS.entitys.IdentificationTypeEnum;
import co.edu.javeriana.RAS.entitys.RoleEnum;
import co.edu.javeriana.RAS.entitys.User;
import co.edu.javeriana.RAS.repositories.AuthorizationRepository;
import co.edu.javeriana.RAS.repositories.HealthEntityRepository;
import co.edu.javeriana.RAS.repositories.UserRepository;

@RestController
public class AuthorizationService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private HealthEntityRepository healthEntityRepository;
	
	@Autowired
	private AuthorizationRepository authorizationRepository;
	
	@PostMapping(path = "/user/{identificationType}/{identificationNumber}/authorization/role_doctor/{healthEntityId}", consumes = "application/json")
	public void authorizateDoctor(@PathVariable IdentificationTypeEnum identificationType, @PathVariable Long identificationNumber, @PathVariable Long healthEntityId, 
			@RequestBody String fingerprint){
		User user = userRepository.getUserByIdentificationAndFingerprint(identificationType, identificationNumber, fingerprint);
		HealthEntity healthEntity = healthEntityRepository.getById(healthEntityId);
		if(user != null && healthEntity != null) {
			Authorization authorization = new Authorization();
			authorization.setHealthEntity(healthEntity);
			authorization.setRole(RoleEnum.ROLE_DOCTOR);
			authorization.setUser(user);
			authorizationRepository.save(authorization);
		}
	}
	
	@PostMapping(path = "/user/{identificationType}/{identificationNumber}/authorization/role_patient/{healthEntityId}", consumes = "application/json")
	public void authorizatePatient(@PathVariable IdentificationTypeEnum identificationType, @PathVariable Long identificationNumber, @PathVariable Long healthEntityId, 
			@RequestBody String fingerprint){
		User user = userRepository.getUserByIdentificationAndFingerprint(identificationType, identificationNumber, fingerprint);
		HealthEntity healthEntity = healthEntityRepository.getById(healthEntityId);
		if(user != null && healthEntity != null) {
			Authorization authorization = new Authorization();
			authorization.setHealthEntity(healthEntity);
			authorization.setRole(RoleEnum.ROLE_PATIENT);
			authorization.setUser(user);
			authorizationRepository.save(authorization);
		}
	}
	
	@PostMapping(path = "/user/{identificationType}/{identificationNumber}/authorization/role_nurse/{healthEntityId}", consumes = "application/json")
	public void authorizateNurse(@PathVariable IdentificationTypeEnum identificationType, @PathVariable Long identificationNumber, @PathVariable Long healthEntityId, 
			@RequestBody String fingerprint){
		User user = userRepository.getUserByIdentificationAndFingerprint(identificationType, identificationNumber, fingerprint);
		HealthEntity healthEntity = healthEntityRepository.getById(healthEntityId);
		if(user != null && healthEntity != null) {
			Authorization authorization = new Authorization();
			authorization.setHealthEntity(healthEntity);
			authorization.setRole(RoleEnum.ROLE_NURSE);
			authorization.setUser(user);
			authorizationRepository.save(authorization);
		}
	}
	
	@PostMapping(path = "/user/{identificationType}/{identificationNumber}/authorization/role_administrative_assistant/{healthEntityId}", consumes = "application/json")
	public void authorizateAdministrativeAssistant(@PathVariable IdentificationTypeEnum identificationType, @PathVariable Long identificationNumber, @PathVariable Long healthEntityId, 
			@RequestBody String fingerprint){
		User user = userRepository.getUserByIdentificationAndFingerprint(identificationType, identificationNumber, fingerprint);
		HealthEntity healthEntity = healthEntityRepository.getById(healthEntityId);
		if(user != null && healthEntity != null) {
			Authorization authorization = new Authorization();
			authorization.setHealthEntity(healthEntity);
			authorization.setRole(RoleEnum.ROLE_ADMINISTRATIVE_ASSISTANT);
			authorization.setUser(user);
			authorizationRepository.save(authorization);
		}
	}


}
