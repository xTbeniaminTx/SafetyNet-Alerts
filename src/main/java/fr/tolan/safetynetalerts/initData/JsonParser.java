package fr.tolan.safetynetalerts.initData;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import org.springframework.stereotype.Component;

@Component
public class JsonParser {

  public AllData readJsonWithObjectMapper() throws IOException {

    ObjectMapper objectMapper = new ObjectMapper();
    AllData allData = objectMapper
        .readValue(new File("src/main/resources/data.json"), AllData.class);
    return allData;
  }

}
