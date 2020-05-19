package co.edu.javeriana.RAS.forms;

import co.edu.javeriana.RAS.entitys.IdentificationTypeEnum;

public class LoginPasswordFingerprintForm {
	private Long healthEntityId;
	private IdentificationTypeEnum identificationType;
	private Long identificationNumber;
	private String password;
	private String fingerprint;
	
	
	public Long getHealthEntityId() {
		return healthEntityId;
	}
	public void setHealthEntityId(Long healthEntityId) {
		this.healthEntityId = healthEntityId;
	}
	public IdentificationTypeEnum getIdentificationType() {
		return identificationType;
	}
	public void setIdentificationType(IdentificationTypeEnum identificationType) {
		this.identificationType = identificationType;
	}
	public Long getIdentificationNumber() {
		return identificationNumber;
	}
	public void setIdentificationNumber(Long identificationNumber) {
		this.identificationNumber = identificationNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFingerprint() {
		return fingerprint;
	}
	public void setFingerprint(String fingerprint) {
		this.fingerprint = fingerprint;
	}
}
