package co.edu.javeriana.RAS.security;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.StringTokenizer;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class JWTTokenProcessor {
	public static String IDENTIFICATION_TYPE = "identificationType";
	public static String IDENTIFICATION_NUMBER = "identificationNumber";
	public static String HEALTH_ENTITY_ID = "healthEntityId";
	public static String AUTHENTICATION_MODE = "authenticationMode";
	
	public String getInformationFromToken(String token, String parameter){
		List<String> parts = new ArrayList<String>();
		StringTokenizer stok = new StringTokenizer(token, ".");
		while (stok.hasMoreTokens()){
			parts.add(stok.nextToken());
		}
		String payload = new String(Base64.getDecoder().decode(parts.get(1)));
		JSONObject payloadObject = new JSONObject(payload);
		return payloadObject.get(parameter).toString();		
	}
	
}
