package fr.tolan.safetynetalerts.services;

import fr.tolan.safetynetalerts.models.Person;
import fr.tolan.safetynetalerts.repos.PersonRepository;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhoneAlertService {

  @Autowired
  PersonRepository personRepository;

  @Autowired
  private FirestationService firestationService;

  public Set<String> getPhonesByStation(String stationNumber) {

    List<String> addresses = firestationService.getAddresses(stationNumber);
    if (!addresses.isEmpty()) {
      List<Person> personsByAddressIn = personRepository.findAllByAddressIn(addresses);

      return personsByAddressIn.stream().map(Person::getPhone).collect(Collectors.toSet());

    } else {

      return null;

    }

  }

}
