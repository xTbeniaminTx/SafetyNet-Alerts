
package fr.tolan.safetynetalerts.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import fr.tolan.safetynetalerts.utils.DateFromString;
import fr.tolan.safetynetalerts.utils.PersonId;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@IdClass(PersonId.class)
@Table(name = "medicalrecords")
public class Medicalrecord {

  @Id
  private long id;

  @Id
  @NotEmpty(message = "First name must not be empty")
  private String firstName;


  @Id
  @NotEmpty(message = "Last name must not be empty")
  private String lastName;

  @JsonDeserialize(using = DateFromString.class)
  private LocalDate birthdate;

  private String[] medications;

  private String[] allergies;


}
