package fr.tolan.safetynetalerts.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhoneAlertService {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private FirestationService firestationService;

	public Set<String> getPhonesByStation(String stationNumber) {

		List<String> addresses = firestationService.getAddresses(stationNumber);
		if (!addresses.isEmpty()) {
			TypedQuery<String> query = em.createQuery("SELECT p.phone FROM Person p WHERE p.address IN :addresses",
					String.class);
			Set<String> listPhonesByStation = new HashSet<String>(
					query.setParameter("addresses", addresses).getResultList());
			return listPhonesByStation;

		} else {
			return null;
		}

	}

}
