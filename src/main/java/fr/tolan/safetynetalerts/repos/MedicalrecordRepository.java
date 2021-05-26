package fr.tolan.safetynetalerts.repos;

import fr.tolan.safetynetalerts.models.Medicalrecord;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MedicalrecordRepository extends CrudRepository<Medicalrecord, Integer> {

	Medicalrecord findByFirstNameAndLastName(String firstName, String lastName);

	@Transactional
	void deleteByFirstNameAndLastName(String firstName, String lastName);

}
