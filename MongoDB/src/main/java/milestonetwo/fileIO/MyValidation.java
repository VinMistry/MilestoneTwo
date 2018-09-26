package milestonetwo.fileIO;

import java.util.Set;

import javax.validation.ConstraintViolation;

public interface MyValidation {

  Set<ConstraintViolation<Object>> validateProfile(Object customerProfile);
}
