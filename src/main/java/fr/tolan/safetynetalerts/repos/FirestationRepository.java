package fr.tolan.safetynetalerts.repos;

import fr.tolan.safetynetalerts.models.Firestation;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface FirestationRepository extends CrudRepository<Firestation, Integer> {

  Firestation findByAddress(String address);

  @Transactional
  Object deleteByAddress(String address);

  @Query("SELECT address FROM Firestation f WHERE station = ?1 ")
  List<String> findByStation(String station);

}
