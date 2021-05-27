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
public class FloodStationDto {

	private String station;

	private List<fr.tolan.safetynetalerts.dtos.FloodStationPersonsDto> stationPersonsByAddress;

}
