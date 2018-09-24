package milestonetwo.fileIO;

import java.io.File;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonFileOutput implements FileOutput {

  private FilePath outputFilePath;
  private ObjectMapper objectMapper;

  public JsonFileOutput(final FilePath outputFilePath, final ObjectMapper objectMapper) {
    this.outputFilePath = outputFilePath;
    this.objectMapper = objectMapper;
  }

  @Override
  public void objectToFile(final String filename, final Object objectToWrite) {
    objectMapper.setVisibility(PropertyAccessor.GETTER, Visibility.PUBLIC_ONLY);
    objectMapper.setVisibility(PropertyAccessor.SETTER, Visibility.NONE);
    final File fileToCreate = new File(outputFilePath.getOutputFilePath(filename, "json"));
    try {
      objectMapper.writeValue(fileToCreate, objectToWrite);
    } catch (final Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void arrayListToFiles(final ArrayList arrayList) {
    arrayList.forEach(object -> objectToFile("jsonFile" + Integer.toString(arrayList.indexOf(object)), object));
  }

}
