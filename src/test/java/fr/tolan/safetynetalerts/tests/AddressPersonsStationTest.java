package fr.tolan.safetynetalerts.tests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class AddressPersonsStationTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  @AutoConfigureTestDatabase(replace = Replace.NONE)
  public void getAddressPersonsStationDtoTest() throws Exception {
    mockMvc.perform(get("/fire?address=1509 Culver St")).andExpect(status().isOk())
        .andExpect(content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.personForAddress").exists());
  }

}
