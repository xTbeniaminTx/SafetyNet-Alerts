package fr.tolan.safetynetalerts.services;

import fr.tolan.safetynetalerts.models.Person;
import fr.tolan.safetynetalerts.repos.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

  @Autowired
  private PersonRepository personRepository;

  public Person getPerson(String firstName, String lastName) {
    return personRepository.findByFirstNameAndLastName(firstName, lastName);
  }

  public Iterable<Person> getPersons() {
    return personRepository.findAll();
  }

  public Person savePerson(Person person) {
    return personRepository.save(person);
  }

  public void deletePerson(String firstName, String lastName) {
    personRepository.deleteByFirstNameAndLastName(firstName, lastName);
  }

}
