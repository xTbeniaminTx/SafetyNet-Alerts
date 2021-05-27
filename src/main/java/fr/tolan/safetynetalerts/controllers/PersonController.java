package fr.tolan.safetynetalerts.controllers;

import fr.tolan.safetynetalerts.models.Person;
import fr.tolan.safetynetalerts.services.PersonService;
import javax.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

	@Autowired
	private PersonService personService;

	private final Logger logger = LoggerFactory.getLogger(PersonController.class);

	/**
	 * Create - Add a new person
	 * 
	 * @param person An object person
	 * @return The person object saved
	 */
	@PostMapping("/person")
	@Validated
	public ResponseEntity<Object> createPerson(@RequestBody Person person) {
		logger.info("Create request : POST http://localhost:8080/person/ - Body :{}", person);
		try {
			Person savedPerson = personService.savePerson(person);
			logger.info("Return : Body :{}", savedPerson);
			return ResponseEntity.ok().body(savedPerson);
		} catch (ConstraintViolationException e) {
			logger.error(e.toString());
			return ResponseEntity.badRequest().body("All person's fields must be informed");
		}
	}

	/**
	 * Read - Get all persons
	 * 
	 * @return - An Iterable object of Person full filled
	 */
	@GetMapping("/persons")
	public Iterable<Person> getPersons() {
		Iterable<Person> persons = personService.getPersons();
		return persons;
	}

	/**
	 * * Update - Update an existing person
	 * 
	 * @param firstName
	 * @param lastName
	 * @param person    - The person object to update
	 * @return The person object updated
	 */
	@PutMapping("/person/{firstName} {lastName}")
	public ResponseEntity<Person> updatePerson(@PathVariable("firstName") final String firstName,
			@PathVariable("lastName") final String lastName, @RequestBody Person person) {

		logger.info("Update request : PUT http://localhost:8080/person/{} {} - Body : {}", firstName, lastName, person);

		Person personInDB = personService.getPerson(firstName, lastName);
		if (personInDB != null) {
			Person currentPerson = personInDB;

			String address = person.getAddress();
			if (address != null) {
				currentPerson.setAddress(address);
			}
			String city = person.getCity();
			if (city != null) {
				currentPerson.setCity(city);
			}
			String zip = person.getZip();
			if (zip != null) {
				currentPerson.setZip(zip);
			}
			String phone = person.getPhone();
			if (phone != null) {
				currentPerson.setPhone(phone);
			}
			String email = person.getEmail();
			if (email != null) {
				currentPerson.setEmail(email);
			}
			personService.savePerson(currentPerson);

			logger.info("Return : Body : {}", currentPerson);

			return ResponseEntity.ok().body(currentPerson);
		} else {
			logger.error("Person {} {} not found in DB", firstName, lastName);
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * Delete - Delete a person
	 * 
	 * @param firstName
	 * @param lastName
	 * @return person deleted message
	 */
	@DeleteMapping("person/{firstName} {lastName}")
	public ResponseEntity<Object> deletePerson(@PathVariable("firstName") final String firstName,
			@PathVariable("lastName") final String lastName) {
		logger.info("Delete request : DELETE http://localhost:8080/person/{} {}", firstName, lastName);

		Person personInDB = personService.getPerson(firstName, lastName);
		if (personInDB != null) {
			personService.deletePerson(firstName, lastName);
			logger.info("Person {} {} deleted", firstName, lastName);
			return ResponseEntity.ok().body(personInDB);
		} else {
			logger.error("Person {} {} not found in DB", firstName, lastName);
			return ResponseEntity.notFound().build();
		}
	}

}
