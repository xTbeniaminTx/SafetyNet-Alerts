package fr.tolan.safetynetalerts.controllers;

import fr.tolan.safetynetalerts.services.EmailService;
import java.util.Set;
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
 * 
 * Controller for Email list by city
 * 
 * @author tolan
 *
 */
@RestController
public class EmailController {

	private final Logger logger = LoggerFactory.getLogger(StationPersonsController.class);

	@Autowired
	private EmailService emailService;

	/**
	 * 
	 * Read - Get list of emails by city
	 * 
	 * @param city
	 * @return Set of emails
	 */
	@RequestMapping(value = "/communityEmail", params = { "city" }, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Set<String>> getPhoneAlert(@RequestParam("city") final String city) {
		logger.info("Request : GET http://localhost:8080/communityEmail?city={}", city);
		Set<String> phonesByStation = emailService.getEmailsByCity(city);
		logger.info("Return : Body :{}", phonesByStation);
		return ResponseEntity.ok().body(phonesByStation);
	}

}
