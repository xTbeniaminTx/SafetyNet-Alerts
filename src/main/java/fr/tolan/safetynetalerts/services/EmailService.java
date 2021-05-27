package fr.tolan.safetynetalerts.services;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@PersistenceContext
	private EntityManager em;

	public Set<String> getEmailsByCity(String city) {

		TypedQuery<String> query = em.createQuery("SELECT p.email FROM Person p WHERE p.city = ?1", String.class);
		Set<String> listEmailByCity = new HashSet<String>(query.setParameter(1, city).getResultList());
		if (!listEmailByCity.isEmpty()) {
			return listEmailByCity;

		} else {
			return null;
		}
	}

}
