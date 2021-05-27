package fr.tolan.safetynetalerts.controllers;

import fr.tolan.safetynetalerts.dtos.StationPersonsDto;
import fr.tolan.safetynetalerts.services.StationPersonsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Tolan
 * <p>
 * Controller for list of persons, number of children and adults by stations
 */
@RestController
public class StationPersonsController {

  @Autowired
  StationPersonsService stationPersonsService;

  private final Logger logger = LoggerFactory.getLogger(StationPersonsController.class);

  /**
   * Read - Get list of persons, number of children and adults by stations
   *
   * @param stationNumber
   * @return StationPersonsDto
   */
  @RequestMapping(value = "/firestation", params = {"stationNumber"}, method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<StationPersonsDto> getStationPersonsDto(
      @RequestParam("stationNumber") final String stationNumber) {
    logger.info("Request : GET http://localhost:8080/firestation?stationNumber={}", stationNumber);
    StationPersonsDto stationPersons = stationPersonsService.getStationPersons(stationNumber);
    if (stationPersons != null) {
      logger.info("Return : Body :{}", stationPersons);
      return ResponseEntity.ok().body(stationPersons);
    } else {
      logger.error("Station {} not found in DB", stationNumber);
      return ResponseEntity.notFound().build();
    }
  }

}
