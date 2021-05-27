package fr.tolan.safetynetalerts.dtos;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class PersonMedicalrecordDto {

	private String firstName;

	private String lastName;

	private String address;

	private String city;

	private String zip;

	private LocalDate birthdate;

	private String phone;

	private String email;

	private String[] medications;

	private String[] allergies;

	private Integer age;

}
