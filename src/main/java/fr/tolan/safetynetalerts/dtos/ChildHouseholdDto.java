package fr.tolan.safetynetalerts.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class ChildHouseholdDto {

	private String firstName;

	private String lastName;

}
