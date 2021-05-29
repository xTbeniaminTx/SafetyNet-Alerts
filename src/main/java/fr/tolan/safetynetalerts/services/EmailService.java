package fr.tolan.safetynetalerts.services;

import fr.tolan.safetynetalerts.models.Person;
import fr.tolan.safetynetalerts.repos.PersonRepository;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

  @Autowired
  PersonRepository personRepository;

  public Set<String> getEmailsByCity(String city) {

    List<Person> personByCity = personRepository.findByCity(city);
    System.out.println(personByCity);
    Set<String> listEmailByCity = personByCity.stream().map(Person::getEmail)
        .collect(Collectors.toSet());
    if (!listEmailByCity.isEmpty()) {
      return listEmailByCity;
    } else {
      return null;
    }
  }

}
