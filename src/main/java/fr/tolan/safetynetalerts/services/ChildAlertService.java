package fr.tolan.safetynetalerts.services;

import fr.tolan.safetynetalerts.dtos.ChildAlertDto;
import fr.tolan.safetynetalerts.dtos.ChildDto;
import fr.tolan.safetynetalerts.dtos.ChildHouseholdDto;
import fr.tolan.safetynetalerts.models.Person;
import fr.tolan.safetynetalerts.repos.MedicalrecordRepository;
import fr.tolan.safetynetalerts.repos.PersonRepository;
import fr.tolan.safetynetalerts.utils.AgeFromBirthdate;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChildAlertService {

  @Autowired
  PersonRepository personRepository;

  @Autowired
  MedicalrecordRepository medicalrecordRepository;

  @Autowired
  private AgeFromBirthdate ageFromBirthdate;

  public ChildAlertDto getChildAlert(String address) {

    ModelMapper modelMapper = new ModelMapper();

    List<ChildDto> listChildDto = new ArrayList<ChildDto>();
    List<ChildHouseholdDto> childHouseholdDto = new ArrayList<ChildHouseholdDto>();

    List<Person> listPersonsByAddress = personRepository.findByAddress(address);

    for (Person person : listPersonsByAddress) {

      String firstName = person.getFirstName();
      String lastName = person.getLastName();

      LocalDate birthdate = medicalrecordRepository.findByFirstNameAndLastName(firstName, lastName)
          .getBirthdate();
      System.out.println(birthdate);
      Integer age = ageFromBirthdate.ageFromBirthdate(birthdate);

      if (age < 18) {
        ChildDto child = modelMapper.map(person, ChildDto.class);
        child.setAge(age);
        listChildDto.add(child);
      } else {
        childHouseholdDto.add(modelMapper.map(person, ChildHouseholdDto.class));
      }
    }
    if (!listChildDto.isEmpty()) {
      return new ChildAlertDto(listChildDto, childHouseholdDto);
    } else {
      return null;
    }

  }

}
