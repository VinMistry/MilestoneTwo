package milestonetwo.fileIO;

import java.util.ArrayList;

public interface FileOutput {

  void objectToFile(String filename, Object objectToWrite);

  void arrayListToFiles(ArrayList arrayList);


}
