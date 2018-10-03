package milestonetwo.fileIO;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonFileInput implements FileInput<Object> {

  private ArrayList arrayList;
  private ObjectMapper objectMapper;
  private Object objectToReturn;

  public JsonFileInput(final ArrayList arrayList, final ObjectMapper objectMapper) {
    this.arrayList = arrayList;
    this.objectMapper = objectMapper;
  }

  @Override
  public Object stringToObject(final Object object, final String json) {
    final Class clazz = object.getClass();
    try {
      objectToReturn = objectMapper.readValue(json, clazz);
    } catch (final IOException e) {
      e.printStackTrace();
    }
    return objectToReturn;
  }

  @Override
  public ArrayList fileToArrayListOfProfiles(final String filename) {
    return arrayList;
  }

}
