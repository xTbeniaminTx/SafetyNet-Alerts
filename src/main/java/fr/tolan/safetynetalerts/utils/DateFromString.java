package fr.tolan.safetynetalerts.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateFromString extends StdDeserializer<LocalDate> {


  public DateFromString(Class<?> clazz) {
    super(clazz);
  }

  protected DateFromString() {
    super(LocalDate.class);
  }

  @Override
  public LocalDate deserialize(JsonParser jsonparser, DeserializationContext deserializationContext)
      throws IOException, JsonProcessingException {
    String date = jsonparser.getText();
    DateTimeFormatter pattern = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    return LocalDate.parse(date, pattern);

  }

}
