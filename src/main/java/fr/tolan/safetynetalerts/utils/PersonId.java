package fr.tolan.safetynetalerts.utils;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PersonId implements Serializable {

  private String firstName;
  private String lastName;

  public PersonId(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

}
