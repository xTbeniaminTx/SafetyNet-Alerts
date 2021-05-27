package fr.tolan.safetynetalerts.controllers;

import fr.tolan.safetynetalerts.models.Firestation;
import fr.tolan.safetynetalerts.services.FirestationService;
import javax.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirestationController {

  @Autowired
  private FirestationService firestationService;

  private final Logger logger = LoggerFactory.getLogger(FirestationController.class);

  /**
   * Create - Add a new mapping station/
   *
   * @param firestation An object firestation (station + )
   * @return The firestation object saved
   */
  @PostMapping("/firestation")
  @Validated
  public ResponseEntity<Object> createFirestation(@RequestBody Firestation firestation) {
    logger.info("Create request : POST http://localhost:8080/firestation/ - Body :{}", firestation);
    try {
      Firestation savedFirestation = firestationService.saveFirestation(firestation);
      logger.info("Return : Body :{}", savedFirestation);
      return ResponseEntity.ok().body(savedFirestation);
    } catch (ConstraintViolationException e) {
      logger.error(e.toString());
      return ResponseEntity.badRequest().body("Station and  must be informed");
    }
  }

  /**
   * Update - Update station for an
   *
   * @param
   * @param station to update
   * @return The firestation object updated
   */
  @PutMapping("/firestation/{address}")
  public ResponseEntity<Firestation> updateFirestation(
      @PathVariable("address") final String address,
      @RequestBody String station) {

    logger.info("Update request : PUT http://localhost:8080/firestation/{} - Body : {}", station);

    Firestation firestationInDB = firestationService.getFirestation(address);
    if (firestationInDB != null) {
      Firestation currentFirestation = firestationInDB;
      currentFirestation.setStation(station);
      firestationService.saveFirestation(currentFirestation);

      logger.info("Return : Body : {}", currentFirestation);

      return ResponseEntity.ok().body(currentFirestation);
    } else {
      logger.error("Firestation for : {} not found in DB", station);
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("firestation/{address}")
  public ResponseEntity<Object> deleteFirestation(@PathVariable("address") final String address) {
    logger.info("Delete request : DELETE http://localhost:8080/firestation/{}", address);

    Firestation firestationInDB = firestationService.getFirestation(address);
    if (firestationInDB != null) {
      firestationService.deleteFirestation(address);
      logger.info("Firestation {} {} deleted", address);
      return ResponseEntity.ok().body(firestationInDB);
    } else {
      logger.error("Firestation for  {} not found in DB", address);
      return ResponseEntity.notFound().build();
    }
  }

}
