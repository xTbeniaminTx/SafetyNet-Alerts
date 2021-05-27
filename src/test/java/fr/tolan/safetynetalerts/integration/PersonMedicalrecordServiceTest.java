package fr.tolan.safetynetalerts.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PersonMedicalrecordServiceTest {

	@Autowired
	PersonMedicalrecordService tryPersonMed;

	@Test
	@AutoConfigureTestDatabase(replace = Replace.NONE)
	void test() {
		tryPersonMed.getPersonMedicalrecordDto("Brian", "Stelzer");
	}

}
