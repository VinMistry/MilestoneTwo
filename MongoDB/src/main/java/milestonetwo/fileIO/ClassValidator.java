package milestonetwo.fileIO;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import milestonetwo.models.CustomerProfile;

public class ClassValidator implements MyValidation<CustomerProfile> {

  @Override
  public Set<ConstraintViolation<CustomerProfile>> validateProfile(final CustomerProfile customerProfile) {
    final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    final Validator validator = factory.getValidator();
    final Set<ConstraintViolation<CustomerProfile>> violations = validator.validate(customerProfile);
    return violations;
  }
}
