package milestonetwo.models;

import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Customer {

  @NotNull(message = "First Name cannot be null")
  private String firstName;
  @NotNull(message = "Last Name cannot be null")
  private String lastName;

  public Customer() {
    firstName = "";
    lastName = "";
  }

  public Customer(final String firstName, final String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(final String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(final String lastName) {
    this.lastName = lastName;
  }

  @Override
  public String toString() {
    return "Customer: \n" +
        " firstName: " + firstName +
        ",\n lastName: " + lastName;
  }
}
