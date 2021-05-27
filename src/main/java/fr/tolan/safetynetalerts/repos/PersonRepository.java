package fr.tolan.safetynetalerts.repos;

import fr.tolan.safetynetalerts.models.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {

  Person findByFirstNameAndLastName(String firstName, String lastName);

  @Transactional
  void deleteByFirstNameAndLastName(String firstName, String lastName);

}
