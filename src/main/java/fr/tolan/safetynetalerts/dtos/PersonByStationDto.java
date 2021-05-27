package fr.tolan.safetynetalerts.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class PersonByStationDto {

  private String firstName;

  private String lastName;

  private String address;

  private String phone;
}
