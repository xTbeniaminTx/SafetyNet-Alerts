package fr.tolan.safetynetalerts.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import fr.tolan.safetynetalerts.dtos.AddressPersonsStationDto;
import fr.tolan.safetynetalerts.services.AddressPersonsStationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = AddressPersonsStationController.class)
public class AddressPersonsStationControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private AddressPersonsStationService addressPersonsStationService;

  @Test
  public void getAddressPersonsStationDtotest() throws Exception {
    AddressPersonsStationDto a = new AddressPersonsStationDto();
    when(addressPersonsStationService.getAddressPersonsStation("1509 Culver St")).thenReturn(a);
    mockMvc.perform(get("/fire?address=1509 Culver St")).andExpect(status().isOk());
  }

}
