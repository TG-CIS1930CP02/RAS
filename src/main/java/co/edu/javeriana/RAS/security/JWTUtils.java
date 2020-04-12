package co.edu.javeriana.RAS.security;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.edu.javeriana.RAS.entitys.HealthEntity;
import co.edu.javeriana.RAS.entitys.User;
import co.edu.javeriana.RAS.repositories.AuthorizationRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtils {
	
	@Autowired
	private KeyStore keyStore;
	
	@Autowired
	private AuthorizationRepository authorizationRepository;
	
	
	public String getJWTToken(User user, HealthEntity healthEntity, AuthenticationModeEnum authenticationMode){
				
		List<String> grantedAuthorities = getGrantedAuthorities(authenticationMode, user, healthEntity);
		
		String token = Jwts
				.builder()
				.claim("name", user.getPerson().getName())
				.claim("lastname", user.getPerson().getLastname())
				.claim("identificationType", user.getPerson().getIdentificationType())
				.claim("identificationNumber", user.getPerson().getIdentificationNumber())
				.claim("healthEntityId", healthEntity.getId())
				.claim("authorities", grantedAuthorities)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.RS256,
						keyStore.getPrivateKey()).compact();
		
		return "Bearer " + token;
		
	}
	
	private List<String> getGrantedAuthorities(AuthenticationModeEnum authenticationMode, 
			User user, HealthEntity healthEntity) {
		List<String> grantedAuthorities = new ArrayList<>();
		grantedAuthorities.add(authenticationMode.getRole());
		grantedAuthorities.add("HEALTH_ENTITY_" + healthEntity.getId());
		grantedAuthorities.addAll(authorizationRepository.getRoles(user, healthEntity));
		return grantedAuthorities;
	}
}
