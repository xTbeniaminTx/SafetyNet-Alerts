package fr.tolan.safetynetalerts.services;

import fr.tolan.safetynetalerts.models.Medicalrecord;
import fr.tolan.safetynetalerts.repos.MedicalrecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicalrecordService {

  @Autowired
  private MedicalrecordRepository medicalrecordRepository;

  public Medicalrecord getMedicalrecord(String firstName, String lastName) {
    return medicalrecordRepository.findByFirstNameAndLastName(firstName, lastName);
  }

  public Iterable<Medicalrecord> getMedicalrecords() {
    return medicalrecordRepository.findAll();
  }

  public Medicalrecord saveMedicalrecord(Medicalrecord medicalrecord) {
    return medicalrecordRepository.save(medicalrecord);
  }

  public void deleteMedicalrecord(String firstName, String lastName) {
    medicalrecordRepository.deleteByFirstNameAndLastName(firstName, lastName);
  }

}
