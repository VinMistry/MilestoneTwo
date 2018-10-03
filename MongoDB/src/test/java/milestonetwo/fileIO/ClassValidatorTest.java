package milestonetwo.fileIO;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import milestonetwo.models.Address;
import milestonetwo.models.Car;
import milestonetwo.models.Customer;
import milestonetwo.models.CustomerProfile;

@ExtendWith(MockitoExtension.class)
class ClassValidatorTest {

  ClassValidator classValidator;

  CustomerProfile testCustomerProfile = new CustomerProfile(new Customer("Thelma", "Baudone"), new Address("LE14", "857", "Forster", "Twyford"),
      new Car("49288-0944", "Honda", "Insight", null));


  Set<ConstraintViolation<CustomerProfile>> constraintViolations;

  @Mock
  ValidatorFactory factory;
  @Mock
  Validator validator;
  @Mock
  private CustomerProfile customerProfile;

  @BeforeEach
  void setUp() {
    classValidator = new ClassValidator();
  }

  @AfterEach
  void tearDown() {
    classValidator = null;
  }

  @Test
  void validateProfile() {
    Assertions.assertEquals(
        "[ConstraintViolationImpl{interpolatedMessage='Engine Size cannot be null', propertyPath=car.engineSize, rootBeanClass=class milestonetwo.models.CustomerProfile, messageTemplate='Engine Size cannot be null'}]",
        classValidator.validateProfile(testCustomerProfile).toString());
  }
}