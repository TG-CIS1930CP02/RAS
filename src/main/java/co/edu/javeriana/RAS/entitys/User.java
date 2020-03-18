package co.edu.javeriana.RAS.entitys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "health_user")
public class User {
	@Id
	@JsonIgnore
	Long id;
	
	String name;
	
	String lastName;
	
	@Enumerated(EnumType.STRING)
	IdentificationTypeEnum identificationType;
	
	@Column(unique = true)
	Long identificationNumber;
	
	@JsonIgnore
	String password;
	
	String fingerprint;
	
	@Transient
	String token;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getFingerprint() {
		return fingerprint;
	}

	public void setFingerprint(String fingerprint) {
		this.fingerprint = fingerprint;
	}
	
}
