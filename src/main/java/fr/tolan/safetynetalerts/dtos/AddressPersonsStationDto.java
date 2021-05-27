package fr.tolan.safetynetalerts.dtos;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class AddressPersonsStationDto {

  private List<PersonForAddressPersonsStationDto> personForAddress;

  private String station;
}
