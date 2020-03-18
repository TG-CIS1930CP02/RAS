package co.edu.javeriana.RAS.entitys;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class HealthEntity {
	@Id
	Long id;
	
	String name;
	String secretKey;
}
