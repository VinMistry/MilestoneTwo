import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import milestonetwo.Launch;
import milestonetwo.database.MongoDBCon;
import milestonetwo.fileIO.FileOutput;

public class MongoTests {

  FileOutput fileOutput;
  ArrayList arrayList;
  MongoDBCon mongoDBCon;

  @BeforeEach
  void setUp() {
    mongoDBCon = new MongoDBCon(Launch.getRegistrySettings(), "test");
  }

  @AfterEach
  void tearDown() {
    mongoDBCon.deleteAll("test");
  }

  @Test
  void writeCsvToDBTest() {

  }

  @Test
  void readCollectionTest() {

  }

  @Test
  void readLastTenProfilesTest() {

  }

  @Test
  void findBMWTest() {

  }

  @Test
  void findPostcodeTest() {

  }

  @Test
  void storeAddressesSeparate() {

  }

}
