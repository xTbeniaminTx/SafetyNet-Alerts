package fr.tolan.safetynetalerts.dtos;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class ChildDto {

	private String firstName;

	private String lastName;

	private int age;

}
