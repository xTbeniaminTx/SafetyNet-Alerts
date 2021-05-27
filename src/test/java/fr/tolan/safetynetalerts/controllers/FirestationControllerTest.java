package fr.tolan.safetynetalerts.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import fr.tolan.safetynetalerts.models.Firestation;
import fr.tolan.safetynetalerts.services.FirestationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = FirestationController.class)
public class FirestationControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private FirestationService firestationService;

  @Test
  public void createFirestationTest() throws Exception {
    mockMvc.perform(post("/firestation").contentType(MediaType.APPLICATION_JSON)
        .content(" { \"address\":\"my address\", \"station\":\"7\" }")).andExpect(status().isOk());
  }

  @Test
  public void updateFirestationTest() throws Exception {
    Firestation f = new Firestation("my address", "7");
    when(firestationService.getFirestation("my address")).thenReturn(f);
    mockMvc.perform(
        put("/firestation/my address").contentType(MediaType.APPLICATION_JSON)
            .content("{ \"station\":\"7\" }"))
        .andExpect(status().isOk());
  }

  @Test
  public void deleteFirestationTest() throws Exception {
    Firestation f = new Firestation("my address", "7");
    when(firestationService.getFirestation("my address")).thenReturn(f);
    mockMvc.perform(delete("/firestation/my address")).andExpect(status().isOk());
  }

}
