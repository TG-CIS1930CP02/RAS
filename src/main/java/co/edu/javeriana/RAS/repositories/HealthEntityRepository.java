package co.edu.javeriana.RAS.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.javeriana.RAS.entitys.HealthEntity;

@Repository
public interface HealthEntityRepository extends CrudRepository<HealthEntity, Long>{
	@Query("SELECT he.secretKey FROM HealthEntity he WHERE he.id = ?1")
	public String getSecretKeyById(Long id);
}
