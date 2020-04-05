package co.edu.javeriana.RAS.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import co.edu.javeriana.RAS.entitys.Authorization;
import co.edu.javeriana.RAS.entitys.HealthEntity;
import co.edu.javeriana.RAS.entitys.User;

public interface AuthorizationRepository extends CrudRepository<Authorization, Long>{
	
	@Query("SELECT a.role "
			+ "FROM Authorization a "
			+ "WHERE "
			+ "a.user = ?1 AND "
			+ "a.healthEntity = ?2")
	public List<String> getRoles(User user, HealthEntity healthEntity);
}
