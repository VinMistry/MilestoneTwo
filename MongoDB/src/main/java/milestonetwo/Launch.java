package milestonetwo;

import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;

import milestonetwo.fileIO.ClassValidator;
import milestonetwo.fileIO.CsvFileInput;
import milestonetwo.fileIO.FilePath;
import milestonetwo.fileIO.JsonFileOutput;
import milestonetwo.models.Address;
import milestonetwo.models.Car;
import milestonetwo.models.Customer;
import milestonetwo.models.CustomerProfile;

public class Launch {

  public FilePath filePath = new FilePath();

  public static ArrayList<Object> createCustomerProfileArray() {
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

    final ArrayList<Object> j = new ArrayList<>();
    j.add(cp1);
    j.add(cp2);
    j.add(cp3);

    return j;
  }

  public static void main(final String[] args) {
    final JsonFileOutput jsonFileOutput = new JsonFileOutput(new FilePath(), new ObjectMapper());
    final ClassValidator classValidator = new ClassValidator();
    final CsvFileInput csvFileInput = new CsvFileInput(new ArrayList<CustomerProfile>(), new CsvMapper(), classValidator);
    jsonFileOutput.arrayListToFiles(csvFileInput.fileToArrayListOfProfiles(FilePath.getInputFilePath("mock_data", "csv")));

  }


}
