package co.edu.javeriana.RAS.entitys;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Qualification {
	@JsonIgnore
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long id;
	
	String name;
	Date startDate;
	String reportingEntity;
	
	@JsonIgnore
	@ManyToOne(optional = false)
	User user;
}
