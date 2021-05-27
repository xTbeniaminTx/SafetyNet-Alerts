package fr.tolan.safetynetalerts.controllers;

import fr.tolan.safetynetalerts.services.PhoneAlertService;
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
 * Controller for PhoneAlert
 * 
 * @author tolan
 * 
 *
 */
@RestController
public class PhoneAlertController {

	private final Logger logger = LoggerFactory.getLogger(StationPersonsController.class);

	@Autowired
	private PhoneAlertService phoneAlertService;

	/**
	 * 
	 * Read - Get list of phones by stations
	 * 
	 * @param stationNumber
	 * @return Set of phones
	 */
	@RequestMapping(value = "/phoneAlert", params = { "firestation" }, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Set<String>> getPhoneAlert(@RequestParam("firestation") final String stationNumber) {
		logger.info("Request : GET http://localhost:8080/phoneAlert?firestation={}", stationNumber);
		Set<String> phonesByStation = phoneAlertService.getPhonesByStation(stationNumber);
		if (phonesByStation != null) {
			logger.info("Return : Body :{}", phonesByStation);
			return ResponseEntity.ok().body(phonesByStation);
		} else {
			logger.error("Station {} not found in DB", stationNumber);
			return ResponseEntity.notFound().build();
		}
	}

}
