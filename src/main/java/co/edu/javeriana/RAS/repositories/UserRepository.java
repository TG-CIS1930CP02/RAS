package co.edu.javeriana.RAS.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.javeriana.RAS.entitys.HealthEntity;
import co.edu.javeriana.RAS.entitys.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	@Query("SELECT user FROM User user "
			+ "WHERE "
			+ "user.identificationNumber = ?1 AND "
			+ "user.password = ?2")
	public User getUserByIdentificationNumberAndPassword(Long identificationNumber,
			String password);
	
	@Query("SELECT user FROM User user "
			+ "WHERE "
			+ "user.identificationNumber = ?1 AND "
			+ "user.password = ?2 AND "
			+ "user.fingerprint = ?3 ")
	public User getUserByIdentificationNumberPasswordAndFingerprint(Long identificationNumber,
			String password, String fingerprint);
}
