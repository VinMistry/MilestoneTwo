package milestonetwo.models;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerProfile {

  @NotNull
  @Valid
  private Customer customer;
  @NotNull
  @Valid
  private Address address;
  @Valid
  @NotNull
  private Car car;

  public CustomerProfile() {
    customer = new Customer();
    address = new Address();
    car = new Car();

  }

  public CustomerProfile(final Customer customer, final Address address, final Car car) {
    this.customer = customer;
    this.address = address;
    this.car = car;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(final Customer customer) {
    this.customer = customer;
  }

  public Car getCar() {
    return car;
  }

  public void setCar(final Car car) {
    this.car = car;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(final Address address) {
    this.address = address;
  }

  @Override
  public String toString() {
    return "Customer Profile: \n" + getCustomer().toString() + "\n" + getAddress().toString() + "\n" + getCar().toString();
  }

}
