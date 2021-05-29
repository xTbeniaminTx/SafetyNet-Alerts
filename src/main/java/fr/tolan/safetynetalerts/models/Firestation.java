package fr.tolan.safetynetalerts.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "firestations")
public class Firestation {

  @NotEmpty(message = "Station must not be empty")
  private String station;

  @Id
  @NotEmpty(message = "Address must not be empty")
  private String address;

}
