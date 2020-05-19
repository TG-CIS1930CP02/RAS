package co.edu.javeriana.RAS.entitys;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Qualification {
	@JsonIgnore
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long id;
	
	String name;	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	Date startDate;
	String reportingEntity;
	
	@JsonIgnore
	@ManyToOne(optional = false)
	User user;

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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getReportingEntity() {
		return reportingEntity;
	}

	public void setReportingEntity(String reportingEntity) {
		this.reportingEntity = reportingEntity;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
