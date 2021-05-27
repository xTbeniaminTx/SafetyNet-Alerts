package fr.tolan.safetynetalerts.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import fr.tolan.safetynetalerts.models.Medicalrecord;
import fr.tolan.safetynetalerts.services.MedicalrecordService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = MedicalrecordController.class)
public class MedicalrecordControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private MedicalrecordService medicalrecordService;

  @Test
  public void createMedicalrecordTest() throws Exception {
    mockMvc.perform(post("/medicalrecord").contentType(MediaType.APPLICATION_JSON).content(
        "{ \"firstName\":\"John\", \"lastName\":\"Boyd\", \"birthdate\":\"03/06/1984\", \"medications\":[\"aznol:350mg\", \"hydrapermazol:100mg\"], \"allergies\":[\"nillacilan\"] }"))
        .andExpect(status().isOk());
  }

  @Test
  public void updateMedicalrecordTest() throws Exception {
    Medicalrecord p = new Medicalrecord();

    when(medicalrecordService.getMedicalrecord("CreatedFirstName", "CreatedLastName"))
        .thenReturn(p);
    mockMvc.perform(
        put("/medicalrecord/CreatedFirstName CreatedLastName")
            .contentType(MediaType.APPLICATION_JSON).content(
            "{ \"firstName\":\"John\", \"lastName\":\"Boyd\", \"birthdate\":\"03/06/1984\", \"medications\":[\"aznol:350mg\", \"hydrapermazol:100mg\"], \"allergies\":[\"nillacilan\"] }"))
        .andExpect(status().isOk());
  }

  @Test
  public void deleteMedicalrecordTest() throws Exception {
    Medicalrecord p = new Medicalrecord();
    when(medicalrecordService.getMedicalrecord("CreatedFirstName", "CreatedLastName"))
        .thenReturn(p);
    mockMvc.perform(delete("/medicalrecord/CreatedFirstName CreatedLastName"))
        .andExpect(status().isOk());
  }

}
