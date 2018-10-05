package milestonetwo.fileIO;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fasterxml.jackson.databind.ObjectMapper;

import milestonetwo.models.Address;
import milestonetwo.models.Car;
import milestonetwo.models.Customer;
import milestonetwo.models.CustomerProfile;

@ExtendWith(MockitoExtension.class)
class JsonFileOutputTest {

  private JsonFileOutput jsonFileCreator;

  @Mock
  private CustomerProfile customerProfile;

  @Mock
  private ObjectMapper objectMapper;

  @Mock
  ArrayList<CustomerProfile> arrayList;


  @BeforeEach
  void setUp() {
    jsonFileCreator = new JsonFileOutput(objectMapper);
  }

  @Test
  void objectToFileTest() throws IOException {
    jsonFileCreator.objectToFile("test", customerProfile);
    verify(objectMapper).writeValue(any(File.class), ArgumentMatchers.eq(customerProfile));
  }

  private ArrayList<CustomerProfile> createCustomerProfileArray() {
    //Create Pojo.Customer objects
    final Customer c1 = new Customer("Vinesh", "Mistry");
    final Customer c2 = new Customer("Dan", "Graef");
    final Customer c3 = new Customer("Jon", "Kneller");
    //Create Pojo.Car objects
    final Car vroom1 = new Car("CFM 139W", "BMW", "M3", "1");
    final Car vroom2 = new Car("CSZ 2614", "Hyundai", "i10", "1.2");
    final Car vroom3 = new Car("EHA 642", "Kia", "Sportage", "1.4");
    //Create customer profile objects
    final CustomerProfile cp1 = new CustomerProfile(c1, new Address("BL3 6TS", "31", "Street", "Bolton"), vroom1);
    final CustomerProfile cp2 = new CustomerProfile(c2, new Address("M2 2BT", "1", "Road", "Manchester"), vroom2);
    final CustomerProfile cp3 = new CustomerProfile(c3, new Address("M3 3IS", "90", "Queen", "Manchester"), vroom3);

    final ArrayList<CustomerProfile> j = new ArrayList<>();
    j.add(cp1);
    j.add(cp2);
    j.add(cp3);

    return j;
  }

  @Test
  void arrayToJsonTest() throws IOException {
    jsonFileCreator.arrayListToFiles(createCustomerProfileArray());
    verify(objectMapper, atLeast(1)).writeValue(any(File.class), any(CustomerProfile.class));
  }

}