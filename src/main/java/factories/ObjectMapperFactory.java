package factories;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class ObjectMapperFactory {

  private static ObjectMapper singleton;

  public ObjectMapper getInstance() {
    if (singleton == null) {
      singleton = new ObjectMapper();
      singleton.registerModule(new JavaTimeModule());
    }
    return singleton;
  }

}
