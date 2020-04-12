package co.edu.javeriana.RAS.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.javeriana.RAS.entitys.IdentificationTypeEnum;
import co.edu.javeriana.RAS.entitys.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long>{
	@Query("SELECT p FROM Person p "
			+ "WHERE "
			+ "p.identificationType = ?1 AND "
			+ "p.identificationNumber = ?2 ")
	public Person getPersonByIdentification(IdentificationTypeEnum identificationType, Long identificationNumber);
}
