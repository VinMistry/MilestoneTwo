package milestonetwo.fileIO;

import java.util.Set;

import javax.validation.ConstraintViolation;

public interface MyValidation<T> {

  Set<ConstraintViolation<T>> validateProfile(T t);
}
