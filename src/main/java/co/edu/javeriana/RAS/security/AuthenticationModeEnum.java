package co.edu.javeriana.RAS.security;

public enum AuthenticationModeEnum {
	PASSWORD_AUTHENTICATION("PASSWORD_AUTHENTICATED_USER"),
	PASSWORD_AND_FINGERPRINT_AUTHENTICATION("PASSWORD_AND_FINGERPRINT_AUTHENTICATED_USER");
	
	private String role;
	private AuthenticationModeEnum(String role) {
		this.role = role;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
}
