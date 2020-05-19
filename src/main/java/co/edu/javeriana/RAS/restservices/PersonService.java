package co.edu.javeriana.RAS.restservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import co.edu.javeriana.RAS.entitys.IdentificationTypeEnum;
import co.edu.javeriana.RAS.entitys.Person;
import co.edu.javeriana.RAS.repositories.PersonRepository;
import co.edu.javeriana.RAS.security.ErrorMessage;

@RestController
public class PersonService {
	@Autowired
	private PersonRepository personRepository;
	
	@GetMapping("/person/{identificationType}/{identificationNumber}")
	public ResponseEntity<Object> getUser(@PathVariable IdentificationTypeEnum identificationType,
			@PathVariable Long identificationNumber){
		Person person = personRepository.getPersonByIdentification(identificationType, identificationNumber);
		if (person != null)
			return new ResponseEntity<>(person, HttpStatus.OK);
		else
			return new ResponseEntity<>(ErrorMessage.RESOURCE_NOT_FOUND, HttpStatus.NOT_FOUND);
	}

}
