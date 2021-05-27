package fr.tolan.safetynetalerts.dtos;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class StationPersonsDto {

  private List<PersonByStationDto> listPersonsByStation;

  private int childs;

  private int adults;
}
