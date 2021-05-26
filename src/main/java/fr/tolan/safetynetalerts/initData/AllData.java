package fr.tolan.safetynetalerts.initData;

import fr.tolan.safetynetalerts.models.Firestation;
import fr.tolan.safetynetalerts.models.Medicalrecord;
import fr.tolan.safetynetalerts.models.Person;
import java.util.List;
import lombok.Data;

@Data
public class AllData {

  List<Person> persons;
  List<Firestation> firestations;
  List<Medicalrecord> medicalrecords;

}
