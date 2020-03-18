package co.edu.javeriana.RAS.security.jwt;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import co.edu.javeriana.RAS.repositories.HealthEntityRepository;
import co.edu.javeriana.RAS.security.AuthenticationModeEnum;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtils {
	@Autowired
	private HealthEntityRepository healthEntityRepository;
	
	public String getJWTToken(Long identificationNumber, Long healthEntityId, AuthenticationModeEnum authenticationMode) {
		
		String secretKey = healthEntityRepository.getSecretKeyById(healthEntityId);
		
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.
				commaSeparatedStringToAuthorityList(authenticationMode.getRole());
		
		String token = Jwts
				.builder()
				.setId("softtekJWT")
				.setSubject(String.valueOf(identificationNumber))
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512,
						secretKey.getBytes()).compact();
		
		return "Bearer " + token;
		
	}	
}
