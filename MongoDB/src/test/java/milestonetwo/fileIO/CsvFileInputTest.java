package milestonetwo.fileIO;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;

import milestonetwo.models.Address;
import milestonetwo.models.Car;
import milestonetwo.models.Customer;
import milestonetwo.models.CustomerProfile;

class CsvFileInputTest {

  private ArrayList<Object> arrayList;
  private CsvFileInput csvInput;
  private ArrayList<Object> returnArray;

  @BeforeEach
  private void setUp() {

    returnArray = new ArrayList<>();

    final Customer c1 = new Customer("Worth", "Throssell");
    final Car vroom1 = new Car("61715-074", "Ford", "Taurus", "1");
    final CustomerProfile customerProfile = new CustomerProfile(c1, new Address("SK11 2WE", "0250", "High Crossing", "Normanton"), vroom1);
    arrayList = new ArrayList<>();
    arrayList.add(customerProfile);
    csvInput = new CsvFileInput(returnArray, new CsvMapper(), new ClassValidator());
  }

  @AfterEach
  private void tearDown() {
    csvInput = null;
    arrayList = null;
    returnArray = null;
  }

  @Test
  void fileToArrayListOfProfiles() {
    try {
      returnArray = csvInput.fileToArrayListOfProfiles(FilePath.getInputFilePath("test_data", "csv"));
    } catch (final Exception e) {
      e.printStackTrace();
    }
    Assertions.assertTrue(arrayList.get(0).equals(returnArray.get(0)));
  }

}