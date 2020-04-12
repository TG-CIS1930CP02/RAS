package co.edu.javeriana.RAS.security;

public enum ErrorMessage {
	WRONG_CREDENTIALS("The Credentials Are Wrong"),
	RESOURCE_NOT_FOUND("The Requested Resource Was Not Found");
	
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
