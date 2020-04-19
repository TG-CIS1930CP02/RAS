package co.edu.javeriana.RAS.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.javeriana.RAS.entitys.Authorization;
import co.edu.javeriana.RAS.entitys.HealthEntity;
import co.edu.javeriana.RAS.entitys.RoleEnum;
import co.edu.javeriana.RAS.entitys.User;

@Repository
public interface AuthorizationRepository extends CrudRepository<Authorization, Long>{
	
	@Query("SELECT a.role "
			+ "FROM Authorization a "
			+ "WHERE "
			+ "a.user = ?1 AND "
			+ "a.healthEntity = ?2")
	public List<String> getRoles(User user, HealthEntity healthEntity);
	
	@Query("SELECT a "
			+ "FROM Authorization a "
			+ "WHERE "
			+ "a.user = ?1 AND "
			+ "a.healthEntity = ?2 AND "
			+ "a.role = ?3")
	public Authorization getAuthorization(User user, HealthEntity healthEntity, RoleEnum role);
	
}
