package fr.tolan.safetynetalerts.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import fr.tolan.safetynetalerts.dtos.PersonInfoDto;
import fr.tolan.safetynetalerts.services.PersonInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = PersonInfoController.class)
public class PersonInfoControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private PersonInfoService personMedicalrecordService;

	@Test
	public void getPersonMedicalrecordDtoTest() throws Exception {
		PersonInfoDto p = new PersonInfoDto();
		when(personMedicalrecordService.getPersonInfo("FirstName", "LastName")).thenReturn(p);
		mockMvc.perform(get("/personInfo?firstName=FirstName&lastName=LastName")).andExpect(status().isOk());
	}

	@Test
	public void getPersonMedicalrecordDtoNotFoundTest() throws Exception {
		when(personMedicalrecordService.getPersonInfo("FirstName", "LastName")).thenReturn(null);
		mockMvc.perform(get("/personInfo?firstName=FirstName&lastName=LastName")).andExpect(status().isNotFound());
	}

}
