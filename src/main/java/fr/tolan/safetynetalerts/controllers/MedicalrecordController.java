package fr.tolan.safetynetalerts.controllers;

import fr.tolan.safetynetalerts.models.Medicalrecord;
import fr.tolan.safetynetalerts.services.MedicalrecordService;
import java.time.LocalDate;
import javax.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MedicalrecordController {

  @Autowired
  private MedicalrecordService medicalrecordService;

  private final Logger logger = LoggerFactory.getLogger(MedicalrecordController.class);

  /**
   * Create - Add a new medicalrecord
   *
   * @param medicalrecord An object medicalrecord
   * @return The medicalrecord object saved
   */
  @PostMapping("/medicalrecord")
  @Validated
  public ResponseEntity<Object> createMedicalrecord(@RequestBody Medicalrecord medicalrecord) {
    logger.info("Create request : POST http://localhost:8080/medicalrecord/ - Body :{}",
        medicalrecord);
    try {
      Medicalrecord savedMedicalrecord = medicalrecordService.saveMedicalrecord(medicalrecord);
      logger.info("Return : Body :{}", savedMedicalrecord);
      return ResponseEntity.ok().body(savedMedicalrecord);
    } catch (ConstraintViolationException e) {
      logger.error(e.toString());
      return ResponseEntity.badRequest().body("All medicalrecord's fields must be informed");
    }
  }

  /**
   * Read - Get all medicalrecords
   *
   * @return - An Iterable object of Medicalrecord full filled
   */
  @GetMapping("/medicalrecords")
  public Iterable<Medicalrecord> getMedicalrecords() {
    return medicalrecordService.getMedicalrecords();
  }

  /**
   * * Update - Update an existing medicalrecord
   *
   * @param medicalrecord - The medicalrecord object to update
   * @return The medicalrecord object updated
   */
  @PutMapping("/medicalrecord/{firstName} {lastName}")
  public ResponseEntity<Medicalrecord> updateMedicalrecord(
      @PathVariable("firstName") final String firstName,
      @PathVariable("lastName") final String lastName, @RequestBody Medicalrecord medicalrecord) {

    logger.info("Update request : PUT http://localhost:8080/medicalrecord/{} {} - Body :{}",
        firstName, lastName,
        medicalrecord);

    Medicalrecord medicalrecordInDB = medicalrecordService.getMedicalrecord(firstName, lastName);
    logger.info(medicalrecord.toString());
    if (medicalrecordInDB != null) {
      Medicalrecord currentMedicalrecord = medicalrecordInDB;

      LocalDate birthdate = medicalrecord.getBirthdate();
      if (birthdate != null) {
        currentMedicalrecord.setBirthdate(birthdate);
      }

      String[] medications = medicalrecord.getMedications();
      if (medications != null) {
        currentMedicalrecord.setMedications(medications);
      }
      String[] allergies = medicalrecord.getAllergies();
      if (allergies != null) {
        currentMedicalrecord.setAllergies(allergies);
      }
      medicalrecordService.saveMedicalrecord(currentMedicalrecord);

      logger.info("Return : Body :{}", currentMedicalrecord);

      return ResponseEntity.ok().body(currentMedicalrecord);
    } else {
      logger.error("Medicalrecord {} {} not found in DB", firstName, lastName);
      return ResponseEntity.notFound().build();
    }
  }

  /**
   * Delete - Delete a medicalrecord
   *
   * @return medicalrecord deleted message
   */
  @DeleteMapping("medicalrecord/{firstName} {lastName}")
  public ResponseEntity<Object> deleteMedicalrecord(
      @PathVariable("firstName") final String firstName,
      @PathVariable("lastName") final String lastName) {
    logger.info("Delete request : DELETE http://localhost:8080/medicalrecord/{} {}", firstName,
        lastName);

    Medicalrecord medicalrecordInDB = medicalrecordService.getMedicalrecord(firstName, lastName);
    if (medicalrecordInDB != null) {
      medicalrecordService.deleteMedicalrecord(firstName, lastName);
      logger.info("Medicalrecord {} {} deleted", firstName, lastName);
      return ResponseEntity.ok().body(medicalrecordInDB);
    } else {
      logger.error("Medicalrecord {} {} not found in DB", firstName, lastName);
      return ResponseEntity.notFound().build();
    }
  }

}
