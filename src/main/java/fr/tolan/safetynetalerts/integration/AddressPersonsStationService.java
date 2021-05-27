package fr.tolan.safetynetalerts.integration;

import fr.tolan.safetynetalerts.dtos.AddressPersonsStationDto;
import fr.tolan.safetynetalerts.dtos.PersonForAddressPersonsStationDto;
import fr.tolan.safetynetalerts.dtos.PersonMedicalrecordDto;
import java.lang.reflect.Type;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressPersonsStationService {

  @Autowired
  PersonMedicalrecordService personMedicalrecordDtoService;

  @Autowired
  FirestationService firestationService;

  public AddressPersonsStationDto getAddressPersonsStation(String address) {
    List<PersonMedicalrecordDto> listPersonsForAddressPersonsStation = personMedicalrecordDtoService
        .getPersonsMedicalrecordByAddress(address);
    if (!listPersonsForAddressPersonsStation.isEmpty()) {
      ModelMapper modelMapper = new ModelMapper();
      Type listType = new TypeToken<List<PersonForAddressPersonsStationDto>>() {
      }.getType();
      List<PersonForAddressPersonsStationDto> listPersonsByStationDto = modelMapper
          .map(listPersonsForAddressPersonsStation, listType);
      String station = firestationService.getFirestation(address).getStation();
      AddressPersonsStationDto addressPersonsStation = new AddressPersonsStationDto(
          listPersonsByStationDto,
          station);
      return addressPersonsStation;
    } else {
      return null;
    }
  }

}
