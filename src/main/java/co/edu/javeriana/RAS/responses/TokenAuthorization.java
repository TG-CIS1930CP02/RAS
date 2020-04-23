package co.edu.javeriana.RAS.responses;

public class TokenAuthorization {
	String token;
	

	public TokenAuthorization(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}	
	
}
