package milestonetwo.fileIO;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class ClassValidator implements MyValidation {

  @Override
  public Set<ConstraintViolation<Object>> validateProfile(final Object customerProfile) {
    final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    final Validator validator = factory.getValidator();
    final Set<ConstraintViolation<Object>> violations = validator.validate(customerProfile);
    return violations;
  }
}
