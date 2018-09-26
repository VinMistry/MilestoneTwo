import java.nio.file.Paths;
import java.util.ArrayList;

import org.bson.Document;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;

import milestonetwo.Launch;
import milestonetwo.database.MongoDBCon;
import milestonetwo.fileIO.ClassValidator;
import milestonetwo.fileIO.CsvFileInput;
import milestonetwo.fileIO.JsonFileInput;
import milestonetwo.models.Address;
import milestonetwo.models.Car;
import milestonetwo.models.Customer;
import milestonetwo.models.CustomerProfile;

public class MongoTests {

  CsvFileInput fileInput;
  ArrayList<CustomerProfile> arrayList;
  MongoDBCon mongoDBCon;
  CustomerProfile testCustomerProfile = new CustomerProfile(new Customer("Thelma", "Baudone"), new Address("LE14", "857", "Forster", "Twyford"),
      new Car("49288-0944", "Honda", "Insight", "1"));

  @BeforeEach
  void setUp() {
    mongoDBCon = new MongoDBCon(Launch.getRegistrySettings(), "test");
    arrayList = new ArrayList<>();
    fileInput = new CsvFileInput(arrayList, new CsvMapper(), new ClassValidator());
  }

  @AfterEach
  void tearDown() {
    mongoDBCon.deleteAll("test");
    arrayList = null;
    mongoDBCon = null;
  }

  @Test
  void writeCsvToDBTest() {
    final JsonFileInput jsonFileInput = new JsonFileInput(new ArrayList<CustomerProfile>(), new ObjectMapper());
    mongoDBCon.insertArrayOfData(fileInput.fileToArrayListOfProfiles(Paths.get("").toAbsolutePath().toString() + "/src/test/resources/mock_data.csv"), "test");
    mongoDBCon.filterRead("test", "customer.firstName", arrayList.get(29).getCustomer().getFirstName(), 0);
    CustomerProfile customerProfile2 = new CustomerProfile();
    for (final Object object : mongoDBCon.getResults()) {
      final Document doc = (Document) object;
      System.out.println(doc.toJson());
      customerProfile2 = (CustomerProfile) jsonFileInput.jsonStringToObject(new CustomerProfile(), doc.toJson());
    }
    Assertions.assertTrue(testCustomerProfile.equals(customerProfile2));
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
