package milestonetwo.fileIO;

import java.util.Set;

import javax.validation.ConstraintViolation;

import milestonetwo.models.CustomerProfile;

public interface MyValidation {

  Set<ConstraintViolation<CustomerProfile>> validateProfile(CustomerProfile customerProfile);
}
