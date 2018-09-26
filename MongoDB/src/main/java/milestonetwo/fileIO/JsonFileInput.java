package milestonetwo.fileIO;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonFileInput {

  private ArrayList arrayList;
  private ObjectMapper objectMapper;
  private Object objectToReturn;

  public JsonFileInput(final ArrayList arrayList, final ObjectMapper objectMapper) {
    this.arrayList = arrayList;
    this.objectMapper = objectMapper;
  }

  public Object jsonStringToObject(final Object object, final String json) {
    final Class clazz = object.getClass();
    try {
      objectToReturn = objectMapper.readValue(json, clazz);
    } catch (final IOException e) {
      e.printStackTrace();
    }
    return objectToReturn;
  }
}
