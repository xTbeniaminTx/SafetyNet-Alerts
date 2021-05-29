package fr.tolan.safetynetalerts.controllers;

import fr.tolan.safetynetalerts.exceptions.PersonNotFoundException;
import fr.tolan.safetynetalerts.models.Person;
import fr.tolan.safetynetalerts.services.PersonService;
import java.net.URI;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
  public ResponseEntity<Object> createPerson(@Valid @RequestBody Person person) {
    logger.info("Create request : POST http://localhost:8080/person/ - Body :{}", person);
    Person savedPerson = personService.savePerson(person);
    logger.info("Return : Body :{}", savedPerson);
    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{firstName} {lastName}")
        .buildAndExpand(savedPerson.getFirstName(), savedPerson.getLastName()).toUri();
    return ResponseEntity.created(location).body(savedPerson);
  }


  /**
   * Read - Get all persons
   *
   * @return - An Iterable object of Person full filled
   */
  @GetMapping("/persons")
  public Iterable<Person> getPersons() {
    return personService.getPersons();
  }

  /**
   * Read - Get one person
   *
   * @return - Person
   */
  @GetMapping("/person/{firstName} {lastName}")
  public Person getPerson(@PathVariable("firstName") final String firstName,
      @PathVariable("lastName") final String lastName) {

    Person person = personService.getPerson(firstName, lastName);

    if (person == null) {
      logger.warn("The person is not found in DB !!!");
      throw new PersonNotFoundException(
          "The person with firstName: " + firstName + " and lastName: " + lastName
              + "is not found in DB");
    }

    logger.info("Get request : Get http://localhost:8080/person/{} {} - Body : {}", firstName,
        lastName, person);

    return person;
  }

  /**
   * * Update - Update an existing person
   *
   * @param person - The person object to update
   * @return The person object updated
   */
  @PutMapping("/person/{firstName} {lastName}")
  public ResponseEntity<Person> updatePerson(@PathVariable("firstName") final String firstName,
      @PathVariable("lastName") final String lastName, @RequestBody Person person)
      throws Exception {

    logger.info("Update request : PUT http://localhost:8080/person/{} {} - Body : {}", firstName,
        lastName, person);

    Person personInDB = personService.getPerson(firstName, lastName);
    if (personInDB != null) {

      String address = person.getAddress();
      if (address != null) {
        personInDB.setAddress(address);
      }
      String city = person.getCity();
      if (city != null) {
        personInDB.setCity(city);
      }
      String zip = person.getZip();
      if (zip != null) {
        personInDB.setZip(zip);
      }
      String phone = person.getPhone();
      if (phone != null) {
        personInDB.setPhone(phone);
      }
      String email = person.getEmail();
      if (email != null) {
        personInDB.setEmail(email);
      }
      personService.savePerson(personInDB);

      logger.info("Return : Body : {}", personInDB);

      return ResponseEntity.ok().body(personInDB);
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
