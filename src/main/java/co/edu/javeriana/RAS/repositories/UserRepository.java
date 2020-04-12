package co.edu.javeriana.RAS.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.javeriana.RAS.entitys.HealthEntity;
import co.edu.javeriana.RAS.entitys.IdentificationTypeEnum;
import co.edu.javeriana.RAS.entitys.Person;
import co.edu.javeriana.RAS.entitys.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	@Query("SELECT u FROM User u "
			+ "WHERE "
			+ "u.person.identificationType = ?1 AND "
			+ "u.person.identificationNumber = ?2 AND "
			+ "u.fingerprint = ?3")
	public User getUserByIdentificationAndFingerprint(IdentificationTypeEnum identificationType, 
			Long identificationNumber, String fingerprint);
	
	@Query("SELECT u FROM User u "
			+ "WHERE "
			+ "u.person.identificationType = ?1 AND "
			+ "u.person.identificationNumber = ?2 AND "
			+ "u.password = ?3")
	public User getUserByIdentificationAndPassword(IdentificationTypeEnum identificationType, 
			Long identificationNumber,
			String password);
	
	@Query("SELECT u FROM User u "
			+ "WHERE "
			+ "u.person.identificationType = ?1 AND "
			+ "u.person.identificationNumber = ?2 AND "
			+ "u.password = ?3 AND "
			+ "u.fingerprint = ?4")
	public User getUserByIdentificationPasswordAndFingerprint(IdentificationTypeEnum identificationType,
			Long identificationNumber,
			String password, String fingerprint);
}
