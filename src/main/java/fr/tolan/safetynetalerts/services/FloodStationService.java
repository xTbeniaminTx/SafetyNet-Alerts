package fr.tolan.safetynetalerts.services;

import fr.tolan.safetynetalerts.dtos.FloodStationDto;
import fr.tolan.safetynetalerts.dtos.FloodStationPersonsDto;
import fr.tolan.safetynetalerts.dtos.PersonForAddressPersonsStationDto;
import fr.tolan.safetynetalerts.dtos.PersonMedicalrecordDto;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FloodStationService {

	@Autowired
	FirestationService firestationService;

	@Autowired
	PersonMedicalrecordService personMedicalrecordDtoService;

	@Autowired
	AddressPersonsStationService addressPersonsStationService;

	public List<FloodStationDto> getFloodStation(List<String> stations) {
		List<FloodStationDto> floodStationList = new ArrayList<FloodStationDto>();
		for (String station : stations) {
			List<String> stationAddresses = firestationService.getAddresses(station);
			List<FloodStationPersonsDto> stationPersonsByAddress = new ArrayList<FloodStationPersonsDto>();
			for (String address : stationAddresses) {
				List<PersonMedicalrecordDto> personsByAddress = personMedicalrecordDtoService
						.getPersonsMedicalrecordByAddress(address);
				ModelMapper modelMapper = new ModelMapper();
				Type listType = new TypeToken<List<PersonForAddressPersonsStationDto>>() {
				}.getType();
				List<PersonForAddressPersonsStationDto> personsByAddressDto = modelMapper.map(personsByAddress,
						listType);
				FloodStationPersonsDto floodStationPersons = new FloodStationPersonsDto(address, personsByAddressDto);
				stationPersonsByAddress.add(floodStationPersons);
			}
			FloodStationDto floodStation = new FloodStationDto(station, stationPersonsByAddress);
			floodStationList.add(floodStation);
		}
		return floodStationList;
	}

}
