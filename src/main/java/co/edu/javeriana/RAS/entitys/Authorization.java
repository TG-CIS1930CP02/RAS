package co.edu.javeriana.RAS.entitys;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "health_authorization")
public class Authorization {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long id;
	
	@ManyToOne(optional = false)
	User user;
	
	@ManyToOne(optional = false)
	HealthEntity healthEntity;
	
	@Enumerated(EnumType.STRING)
	RoleEnum role;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public HealthEntity getHealthEntity() {
		return healthEntity;
	}

	public void setHealthEntity(HealthEntity healthEntity) {
		this.healthEntity = healthEntity;
	}

	public RoleEnum getRole() {
		return role;
	}

	public void setRole(RoleEnum role) {
		this.role = role;
	}
}
