package fr.tolan.safetynetalerts.tests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import fr.tolan.safetynetalerts.models.Person;
import fr.tolan.safetynetalerts.integration.PersonService;

@SpringBootTest
@AutoConfigureTestDatabase
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureMockMvc
public class PersonTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private PersonService personService;

  @AfterAll
  private static void tearDown() {

  }

  @Test
  @Order(1)
  @Rollback(false)
  public void createPersonTest() throws Exception {
    mockMvc.perform(post("/person").contentType(MediaType.APPLICATION_JSON).content(
        "{ \"firstName\":\"CreatedFirstName\", \"lastName\":\"CreatedLastName\", \"address\":\"1509 Culver St\", \"city\":\"Culver\", \"zip\":\"97451\", \"phone\":\"841-874-6512\", \"email\":\"jaboyd@email.com\" }")
        .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").exists());
  }

//  @Test
//  @Order(2)
//  public void createPersonConstraintViolationTest() throws Exception {
//    mockMvc.perform(post("/person").contentType(MediaType.APPLICATION_JSON).content(
//        "{ \"lastName\":\"CreatedLastName\", \"address\":\"1509 Culver St\", \"city\":\"Culver\", \"zip\":\"97451\", \"phone\":\"841-874-6512\", \"email\":\"jaboyd@email.com\" }")
//        ).andExpect(model().hasErrors())
//        .andExpect(status().isInternalServerError());
//  }

//  @Test
//  @Order(2)
//  public void createPersonConstraintViolationTest() throws Exception {
//    mockMvc.perform(MockMvcRequestBuilders.post("/person").contentType(MediaType.APPLICATION_JSON)
//        .content(
//            "{ \"firstName\":\"CreatedFirstName\", \"lastName\":\"CreatedLastName\", \"address\":\"1509 Culver St\", \"city\":\"Culver\", \"zip\":\"97451\", \"phone\":\"841-874-6512\", \"email\":\"jaboyd@email.com\" }")
//        .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isConflict());
//  }

  @Test
  @Order(3)
  void getPersonsTestIT() throws Exception {
    mockMvc.perform(get("/persons")).andExpect(status().isOk());
  }

  @Test
  @Order(4)
  @Rollback(false)
  public void updatePersonTest() throws Exception {
    mockMvc.perform(
        put("/person/CreatedFirstName CreatedLastName").contentType(MediaType.APPLICATION_JSON)
            .content(
                "{ \"address\":\"UpdatedAddress\", \"city\":\"Culver\", \"zip\":\"97451\", \"phone\":\"841-874-6512\", \"email\":\"jaboyd@email.com\" }")
            .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.address", is("UpdatedAddress")));
  }

  @Test
  @Order(5)
  @Rollback(false)
  public void updatePersonNotFoundTest() throws Exception {
    mockMvc.perform(put("/person/NotCreatedFirstName NotCreatedLastName")
        .contentType(MediaType.APPLICATION_JSON)
        .content(
            "{ \"address\":\"UpdatedAddress\", \"city\":\"Culver\", \"zip\":\"97451\", \"phone\":\"841-874-6512\", \"email\":\"jaboyd@email.com\" }")
        .accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound())
        .andExpect(MockMvcResultMatchers.jsonPath("$").doesNotExist());
  }

  @Test
  @Order(7)
  @Rollback(false)
  public void deletePersonTest() throws Exception {
    mockMvc.perform(delete("/person/CreatedFirstName CreatedLastName")).andExpect(status().isOk());
    Person deletedPerson = personService.getPerson("CreatedFirstName", "CreatedLastName");
    assertThat(deletedPerson).isNull();

  }

  @Test
  @Order(6)
  @Rollback(false)
  public void deletePersonNotFoundTest() throws Exception {
    mockMvc.perform(
        delete("/person/NotCreatedFirstName NotCreatedLastName").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
    Person deletedPerson = personService.getPerson("CreatedFirstName", "CreatedLastName");
    assertThat(deletedPerson).isNotNull();
  }

}
