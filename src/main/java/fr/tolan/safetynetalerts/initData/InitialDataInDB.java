package fr.tolan.safetynetalerts.initData;

import fr.tolan.safetynetalerts.models.Firestation;
import fr.tolan.safetynetalerts.models.Medicalrecord;
import fr.tolan.safetynetalerts.models.Person;
import fr.tolan.safetynetalerts.services.FirestationService;
import fr.tolan.safetynetalerts.services.MedicalrecordService;
import fr.tolan.safetynetalerts.services.PersonService;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitialDataInDB implements CommandLineRunner {

  @Autowired
  private PersonService personService;
  @Autowired
  private FirestationService firestationService;
  @Autowired
  private MedicalrecordService medicalrecordService;
  @Autowired
  private JsonParser jsonParser;

  public void initialDataInDB() throws IOException {

    AllData allData = jsonParser.readJsonWithObjectMapper();
    List<Person> persons = allData.getPersons();
    List<Firestation> firestations = allData.getFirestations();
    List<Medicalrecord> medicalrecords = allData.getMedicalrecords();

    for (Person person : persons) {
      personService.savePerson(person);
    }

    for (Firestation firestation : firestations) {
      firestationService.saveFirestation(firestation);
    }

    for (Medicalrecord medicalrecord : medicalrecords) {
      medicalrecordService.saveMedicalrecord(medicalrecord);
    }
  }

  @Override
  public void run(String... args) throws Exception {
    initialDataInDB();
  }
}
