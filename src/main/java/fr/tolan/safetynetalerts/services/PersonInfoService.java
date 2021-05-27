package fr.tolan.safetynetalerts.services;

import fr.tolan.safetynetalerts.dtos.PersonInfoDto;
import fr.tolan.safetynetalerts.dtos.PersonMedicalrecordDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonInfoService {

  @Autowired
  PersonMedicalrecordService personMedicalrecordDtoService;

  public PersonInfoDto getPersonInfo(String firstName, String lastName) {
    PersonMedicalrecordDto personMedicalrecordDto = personMedicalrecordDtoService
        .getPersonMedicalrecordDto(firstName, lastName);
    if (personMedicalrecordDto != null) {
      ModelMapper modelMapper = new ModelMapper();
      PersonInfoDto personInfoDto = modelMapper.map(personMedicalrecordDto, PersonInfoDto.class);
      return personInfoDto;
    } else {
      return null;
    }
  }
}
