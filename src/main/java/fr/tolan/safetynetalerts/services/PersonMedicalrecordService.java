package fr.tolan.safetynetalerts.services;

import fr.tolan.safetynetalerts.dtos.PersonMedicalrecordDto;
import fr.tolan.safetynetalerts.models.Medicalrecord;
import fr.tolan.safetynetalerts.models.Person;
import fr.tolan.safetynetalerts.repos.PersonRepository;
import fr.tolan.safetynetalerts.utils.AgeFromBirthdate;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonMedicalrecordService {

  @Autowired
  PersonRepository personRepository;

  @Autowired
  PersonService personService;

  @Autowired
  private MedicalrecordService medicalrecordService;

  @Autowired
  private AgeFromBirthdate ageFromBirthdate;

  ModelMapper modelMapper = new ModelMapper();

  /**
   * Get PersonMedicalrecord from first and last name
   *
   * @return Persons and Medicalrecords Infos
   */
  public PersonMedicalrecordDto getPersonMedicalrecordDto(String firstName, String lastName) {
    Person person = personService.getPerson(firstName, lastName);
    Medicalrecord medicalrecord = medicalrecordService.getMedicalrecord(firstName, lastName);
    if (person != null & medicalrecord != null) {
      PersonMedicalrecordDto personMedDto = modelMapper.map(person, PersonMedicalrecordDto.class);
      modelMapper.map(medicalrecord, personMedDto);
      Integer age = ageFromBirthdate.ageFromBirthdate(personMedDto.getBirthdate());
      personMedDto.setAge(age);

      return personMedDto;

    } else {

      return null;

    }
  }

  /**
   * Get PersonMedicalrecord from Person
   *
   * @return Persons and Medicalrecords Infos
   */
  public PersonMedicalrecordDto personToPersonMedDto(Person person) {

    return getPersonMedicalrecordDto(person.getFirstName(), person.getLastName());

  }

  /**
   * Get PersonMedicalrecord from address
   *
   * @return Persons and Medicalrecords Infos
   */
  public List<PersonMedicalrecordDto> getPersonsMedicalrecordByAddress(String address) {
    List<Person> listPersonsByAddress = personRepository.findByAddress(address);
    List<PersonMedicalrecordDto> listPersonsMedicalByAddress = new ArrayList<>();
    for (Person person : listPersonsByAddress) {
      listPersonsMedicalByAddress.add(personToPersonMedDto(person));
    }

    return listPersonsMedicalByAddress;

  }

  /**
   * Get PersonMedicalrecord from list of addresses
   *
   * @return Persons and Medicalrecords Infos
   */
  public List<PersonMedicalrecordDto> getPersonsMedicalrecordByAddresses(List<String> addresses) {
    List<Person> listPersonsByAddresses = personRepository.findAllByAddressIn(addresses);
    List<PersonMedicalrecordDto> listPersonsMedicalByAddresses = new ArrayList<>();
    for (Person person : listPersonsByAddresses) {
      listPersonsMedicalByAddresses.add(personToPersonMedDto(person));
    }

    return listPersonsMedicalByAddresses;

  }
}
