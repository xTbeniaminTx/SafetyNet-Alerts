package fr.tolan.safetynetalerts.controllers;

import fr.tolan.safetynetalerts.dtos.FloodStationDto;
import fr.tolan.safetynetalerts.services.FloodStationService;
import java.util.ArrayList;
import java.util.List;
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
 * @author tolan
 * 
 *         Controller for list of persons by addresses by stations stations
 *
 */
@RestController
public class FloodStationController {

	@Autowired
	FloodStationService floodStationService;

	private final Logger logger = LoggerFactory.getLogger(FloodStationService.class);

	/**
	 * Read - Get list of persons and station number by address
	 * 
	 * @param stations
	 * @return AddressPersonsStationDto
	 */
	@RequestMapping(value = "/flood/stations", params = { "stations" }, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<FloodStationDto>> getFloodStationsDto(
			@RequestParam("stations") final ArrayList<String> stations) {
		logger.info("Request : GET http://localhost:8080/flood/stations?station={}", stations);
		List<FloodStationDto> floodStation = floodStationService.getFloodStation(stations);
		if (!floodStation.isEmpty()) {
			logger.info("Return : Body :{}", floodStation);
			return ResponseEntity.ok().body(floodStation);
		} else {
			logger.error("Stations {} not found in DB", stations);
			return ResponseEntity.notFound().build();
		}
	}

}
