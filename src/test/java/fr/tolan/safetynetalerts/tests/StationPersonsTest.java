
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

@SpringBootTest
@AutoConfigureMockMvc
public class StationPersonsTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  @AutoConfigureTestDatabase(replace = Replace.NONE)
  public void getStationPersonsDtoTest() throws Exception {
    mockMvc.perform(get("/firestation?stationNumber=1")).andExpect(status().isOk())
        .andExpect(content().contentType("application/json"));
  }

  @Test
  @AutoConfigureTestDatabase(replace = Replace.NONE)
  public void getStationPersonsDtoNotFoundTest() throws Exception {
    mockMvc.perform(get("/firestation?stationNumber=73")).andExpect(status().isNotFound());
  }

}
