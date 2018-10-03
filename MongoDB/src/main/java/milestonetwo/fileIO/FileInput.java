package milestonetwo.fileIO;

import java.util.ArrayList;

public interface FileInput<T> {

  ArrayList fileToArrayListOfProfiles(String filename);

  T stringToObject(T t, String string);

}
