package fr.tolan.safetynetalerts.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class PersonInfoDto {

	private String firstName;

	private String lastName;

	private Integer age;

	private String email;

	private String[] medications;

	private String[] allergies;

}
