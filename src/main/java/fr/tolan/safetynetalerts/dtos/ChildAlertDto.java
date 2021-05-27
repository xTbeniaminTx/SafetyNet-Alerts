package fr.tolan.safetynetalerts.dtos;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@Component
@NoArgsConstructor
public class ChildAlertDto {

	private List<ChildDto> children;

	private List<fr.tolan.safetynetalerts.dtos.ChildHouseholdDto> childrenHousehold;

}
