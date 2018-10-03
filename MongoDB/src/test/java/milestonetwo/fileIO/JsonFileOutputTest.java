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

import milestonetwo.Launch;
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

  @Test
  void arrayToJsonTest() throws IOException {
    jsonFileCreator.arrayListToFiles(Launch.createCustomerProfileArray2());
    verify(objectMapper, atLeast(1)).writeValue(any(File.class), any(CustomerProfile.class));
  }

}