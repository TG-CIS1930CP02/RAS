package co.edu.javeriana.RAS.security.jwt;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.transform.ToListResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import co.edu.javeriana.RAS.entitys.HealthEntity;
import co.edu.javeriana.RAS.entitys.RoleEnum;
import co.edu.javeriana.RAS.entitys.User;
import co.edu.javeriana.RAS.repositories.AuthorizationRepository;
import co.edu.javeriana.RAS.repositories.HealthEntityRepository;
import co.edu.javeriana.RAS.security.AuthenticationModeEnum;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtils {
	
	
	@Autowired
	private AuthorizationRepository authorizationRepository;
	
	
	public String getJWTToken(User user, HealthEntity healthEntity, AuthenticationModeEnum authenticationMode) throws NoSuchAlgorithmException {
		KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance("RSA");
		keyGenerator.initialize(1024);
		
		KeyPair kp = keyGenerator.generateKeyPair();
		PublicKey publicKey = (PublicKey) kp.getPublic();
		PrivateKey privateKey = (PrivateKey) kp.getPrivate();
				
		List<String> grantedAuthorities = getGrantedAuthorities(authenticationMode, user, healthEntity);
		
		String token = Jwts
				.builder()
				.claim("name", user.getPerson().getName())
				.claim("lastname", user.getPerson().getLastname())
				.claim("identificationType", user.getPerson().getIdentificationType())
				.claim("identificationNumber", user.getPerson().getIdentificationNumber())
				.claim("authorities", grantedAuthorities)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.RS256,
						privateKey).compact();
		
		return "Bearer " + token;
		
	}
	
	private List<String> getGrantedAuthorities(AuthenticationModeEnum authenticationMode, 
			User user, HealthEntity healthEntityId) {
		List<String> grantedAuthorities = new ArrayList<>();
		grantedAuthorities.add(authenticationMode.getRole());
		grantedAuthorities.addAll(authorizationRepository.getRoles(user, healthEntityId));
		return grantedAuthorities;
	}
}
