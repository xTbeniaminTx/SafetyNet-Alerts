package fr.tolan.safetynetalerts.tests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import fr.tolan.safetynetalerts.models.Medicalrecord;
import fr.tolan.safetynetalerts.services.MedicalrecordService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureTestDatabase
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureMockMvc
public class MedicalrecordTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private MedicalrecordService medicalrecordService;

  @AfterAll
  private static void tearDown() {

  }

  @Test
  @Order(1)
  @Rollback(false)
  public void createMedicalrecordTest() throws Exception {
    mockMvc.perform(post("/medicalrecord").contentType(MediaType.APPLICATION_JSON).content(
        "{ \"firstName\":\"CreatedFirstName\", \"lastName\":\"CreatedLastName\", \"birthdate\":\"03/06/1984\", \"medications\":[\"aznol:350mg\", \"hydrapermazol:100mg\"], \"allergies\":[\"nillacilan\"] }")
        .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").exists());
  }

  @Test
  @Order(3)
  void getMedicalrecordsTestIT() throws Exception {
    mockMvc.perform(get("/medicalrecords")).andExpect(status().isOk());
  }

  @Test
  @Order(4)
  @Rollback(false)
  public void updateMedicalrecordTest() throws Exception {
    mockMvc.perform(put("/medicalrecord/CreatedFirstName CreatedLastName")
        .contentType(MediaType.APPLICATION_JSON)
        .content(
            "{ \"birthdate\":\"01/01/1901\", \"medications\":[\"aznol:350mg\", \"hydrapermazol:100mg\"], \"allergies\":[\"nillacilan\"] }")
        .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.birthdate", is("1901-01-01")));
  }

  @Test
  @Order(5)
  @Rollback(false)
  public void updateMedicalrecordNotFoundTest() throws Exception {
    mockMvc.perform(put("/medicalrecord/NotCreatedFirstName NotCreatedLastName")
        .contentType(MediaType.APPLICATION_JSON)
        .content(
            "{ \"birthdate\":\"03/06/1984\", \"medications\":[\"aznol:350mg\", \"hydrapermazol:100mg\"], \"allergies\":[\"nillacilan\"] }")
        .accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
  }

  @Test
  @Order(7)
  @Rollback(false)
  public void deleteMedicalrecordTest() throws Exception {
    mockMvc.perform(delete("/medicalrecord/CreatedFirstName CreatedLastName"))
        .andExpect(status().isOk());
    Medicalrecord deletedMedicalrecord = medicalrecordService.getMedicalrecord("CreatedFirstName",
        "CreatedLastName");
    assertThat(deletedMedicalrecord).isNull();

  }

  @Test
  @Order(6)
  @Rollback(false)
  public void deleteMedicalrecordNotFoundTest() throws Exception {
    mockMvc.perform(
        delete("/medicalrecord/NotCreatedFirstName NotCreatedLastName")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
    Medicalrecord deletedMedicalrecord = medicalrecordService.getMedicalrecord("CreatedFirstName",
        "CreatedLastName");
    assertThat(deletedMedicalrecord).isNotNull();
  }

}