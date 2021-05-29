package fr.tolan.safetynetalerts.repos;

import fr.tolan.safetynetalerts.models.Person;
import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PersonRepository extends CrudRepository<Person, Integer> {

  Person findByFirstNameAndLastName(String firstName, String lastName);

  @Transactional
  void deleteByFirstNameAndLastName(String firstName, String lastName);

  List<Person> findByAddress(String address);

  List<Person> findByCity(String city);

  @Query(value = "SELECT * FROM persons WHERE persons.address IN :addresses", nativeQuery = true)
  List<Person> findAllPersonsByAddresses(@Param("addresses") List<String> addresses);

  List<Person> findAllByAddressIn(List<String> addresses);

  @Query(value = "SELECT persons.phone FROM persons WHERE persons.address IN :addresses", nativeQuery = true)
  Set<String> findAllPhonesByAddresses(@Param("addresses") List<String> addresses);

}
