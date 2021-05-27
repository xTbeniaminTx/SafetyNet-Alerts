package fr.tolan.safetynetalerts.utils;

import java.time.LocalDate;
import java.time.Period;
import org.springframework.stereotype.Component;

@Component
public class AgeFromBirthdate {
	public Integer ageFromBirthdate(LocalDate birthdate) {
		Integer age = Period.between(birthdate, LocalDate.now()).getYears();
		return age;
	}
}