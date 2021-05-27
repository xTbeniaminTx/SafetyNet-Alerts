package fr.tolan.safetynetalerts.controllers;

import fr.tolan.safetynetalerts.dtos.PersonInfoDto;
import fr.tolan.safetynetalerts.integration.PersonInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tolan
 * <p>
 * Controller for PersonInfo
 */
@RestController
public class PersonInfoController {

  @Autowired
  private PersonInfoService personMedicalrecordService;

  private final Logger logger = LoggerFactory.getLogger(PersonInfoController.class);

  /**
   * Read - Get PersonInfo - Person and Medicalrecord
   *
   * @param firstName
   * @param lastName
   * @return person + medicalrecord DTO
   */
  @RequestMapping(value = "/personInfo", params = {"firstName",
      "lastName"}, method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<PersonInfoDto> getPersonInfoDto(
      @RequestParam("firstName") final String firstName,
      @RequestParam("lastName") final String lastName) {
    logger
        .info("Request : GET http://localhost:8080/personInfo?firstName={}&lastName={}", firstName,
            lastName);
    PersonInfoDto personMedicalrecord = personMedicalrecordService
        .getPersonInfo(firstName, lastName);
    System.out.println(personMedicalrecord);
    if (personMedicalrecord != null) {
      logger.info("Return : Body :{}", personMedicalrecord);
      return ResponseEntity.ok().body(personMedicalrecord);
    } else {
      logger.error("Person {} {} not found in DB", firstName, lastName);
      return ((BodyBuilder) ResponseEntity.notFound()).body(null);
    }
  }

}
