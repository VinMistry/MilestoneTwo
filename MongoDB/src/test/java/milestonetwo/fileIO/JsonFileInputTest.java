package milestonetwo.fileIO;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import milestonetwo.models.Address;
import milestonetwo.models.Car;
import milestonetwo.models.Customer;
import milestonetwo.models.CustomerProfile;

class JsonFileInputTest {

  JsonFileInput jsonFileInput;
  String jsonString = "{\"customer\":{\"firstName\":\"Vinesh\",\"lastName\":\"Mistry\"},\"address\":{\"postcode\":\"BL3 6TS\",\"houseNumber\":\"31\",\"street\":\"Street\",\"city\":\"Bolton\"},\"car\":{\"registration\":\"CFM 139W\",\"make\":\"BMW\",\"model\":\"M3\",\"engineSize\":\"1\"}}";
  CustomerProfile expectedCustomer;
  CustomerProfile actualCust;

  @BeforeEach
  void setUp() {
    expectedCustomer = new CustomerProfile(new Customer("Vinesh", "Mistry"), new Address("BL3 6TS", "31", "Street", "Bolton"),
        new Car("CFM 139W", "BMW", "M3", "1"));
    actualCust = new CustomerProfile();
    jsonFileInput = new JsonFileInput(new ArrayList<CustomerProfile>(), new ObjectMapper());
  }

  @Test
  void jsonStringToObject() {
    final Object actualCust = jsonFileInput.stringToObject(new CustomerProfile(), jsonString);
    Assertions.assertEquals(expectedCustomer, actualCust);
  }
}