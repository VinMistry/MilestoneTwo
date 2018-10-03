package milestonetwo.fileIO;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import milestonetwo.models.Address;
import milestonetwo.models.Car;
import milestonetwo.models.Customer;
import milestonetwo.models.CustomerProfile;

public class CsvFileInput implements FileInput {

  private ArrayList arrayList;
  private CsvMapper csvMapper;
  private CsvSchema csvSchema;
  private MyValidation classValidator;

  public CsvFileInput(final ArrayList arrayList, final CsvMapper csvMapper, final MyValidation validation) {
    this.arrayList = arrayList;
    this.csvMapper = csvMapper;
    this.classValidator = validation;
  }

  private boolean mapChecker(final Map<String, String> rowAsMap) {
    if (rowAsMap.containsValue("")) {
      System.out.println("######ERROR#####\n On line containing:\n" + rowAsMap.toString() + "\nPLEASE CHECK CSV FILE FOR ERRORS\n-------------\n");
      return false;
    }
    return true;
  }

  @Override
  public ArrayList fileToArrayListOfProfiles(final String pathToInput) {
    try {
      final File csvFile = new File(pathToInput);
      csvSchema = CsvSchema.emptySchema().withHeader();
      final MappingIterator<Map<String, String>> iterator = csvMapper.readerFor(Map.class)
          .with(csvSchema)
          .readValues(csvFile);
      while (iterator.hasNext()) {
        final Map<String, String> rowAsMap = iterator.next();
        final CustomerProfile customerProfile = new CustomerProfile(new Customer(rowAsMap.get("firstName"), rowAsMap.get("lastName")),
            new Address(rowAsMap.get("postcode"), rowAsMap.get("houseNumber"), rowAsMap.get("street"), rowAsMap.get("city")),
            new Car(rowAsMap.get("registration"), rowAsMap.get("make"), rowAsMap.get("model"), rowAsMap.get("engineSize")));
        final Set<ConstraintViolation<Object>> violations = classValidator.validateProfile(customerProfile);
        if (!violations.isEmpty() || !mapChecker(rowAsMap)) {
          for (final ConstraintViolation<Object> violation : violations) {
            System.out.println("######ERROR#######\nViolation for: \n" + customerProfile.getCustomer().toString());
            System.out.println("Violation: " + violation.getMessage() + "\nPLEASE CHECK CSV FILE FOR ERRORS\n-------------\n");

          }
        } else {
          arrayList.add(customerProfile);
        }
      }
    } catch (final Exception e) {
      e.printStackTrace();
    }
    return arrayList;
  }

  @Override
  public Object stringToObject(final Object o, final String string) {
    return null;
  }

}
