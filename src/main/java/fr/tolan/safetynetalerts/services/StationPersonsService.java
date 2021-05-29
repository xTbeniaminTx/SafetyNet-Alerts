package fr.tolan.safetynetalerts.services;

import fr.tolan.safetynetalerts.dtos.PersonByStationDto;
import fr.tolan.safetynetalerts.dtos.PersonMedicalrecordDto;
import fr.tolan.safetynetalerts.dtos.StationPersonsDto;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StationPersonsService {

  @Autowired
  private FirestationService firestationService;

  @Autowired
  private PersonMedicalrecordService personMedicalrecordDtoService;

  public StationPersonsDto getStationPersons(String stationNumber) {

    List<String> addresses = firestationService.getAddresses(stationNumber);
    if (!addresses.isEmpty()) {

      List<PersonMedicalrecordDto> listPersonsByStation = personMedicalrecordDtoService
          .getPersonsMedicalrecordByAddresses(addresses);
      List<PersonByStationDto> listPersonsByStationDto = new ArrayList<>();
      int children = 0;
      int adults = 0;
      for (PersonMedicalrecordDto personMedByStation : listPersonsByStation) {
        ModelMapper modelMapper = new ModelMapper();
        PersonByStationDto personByStationDto = modelMapper
            .map(personMedByStation, PersonByStationDto.class);
        listPersonsByStationDto.add(personByStationDto);
        if (personMedByStation.getAge() < 18) {
          children++;
        } else {
          adults++;
        }
      }

      return new StationPersonsDto(listPersonsByStationDto, children, adults);

    } else {

      return null;

    }

  }
}
