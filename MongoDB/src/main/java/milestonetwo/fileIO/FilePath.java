package milestonetwo.fileIO;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FilePath {

  public static String getProjectDirectory() {
    return Paths.get("").toAbsolutePath().toString();
  }

  public static String getOutputFilePath(final String filename, final String fileExtension) {
    final Path filePath = Paths.get(getProjectDirectory() + "/outputFiles/" + fileExtension + "/" + filename + "." + fileExtension);
    return filePath.toString();
  }

  public static String getInputFilePath(final String filename, final String fileExtension) {
    final Path filePath = Paths.get(getProjectDirectory() + "/inputFiles/" + fileExtension + "/" + filename + "." + fileExtension);
    return filePath.toString();
  }

}
