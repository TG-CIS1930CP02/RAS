package co.edu.javeriana.RAS.security;

public enum ErrorMessage {
	INVALID_CREDENTIALS("Invalid Credentials");
	
	private String message;
	private ErrorMessage(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
