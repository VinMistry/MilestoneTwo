package milestonetwo.models;

import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Car {

  @NotNull(message = "Registration cannot be null")
  private String registration;
  @NotNull(message = "Make cannot be null")
  private String make;
  @NotNull(message = "Model cannot be null")
  private String model;
  @NotNull(message = "Engine Size cannot be null")
  private String engineSize;

  public Car() {
    registration = "";
    make = "";
    model = "";
    engineSize = "";

  }

  public Car(@NotNull final String registration, @NotNull final String make, @NotNull final String model, @NotNull final String engineSize) {
    this.registration = registration;
    this.make = make;
    this.model = model;
    this.engineSize = engineSize;
  }

  public String getRegistration() {
    return registration;
  }

  public void setRegistration(final String registration) {
    this.registration = registration;
  }

  public String getMake() {
    return make;
  }

  public void setMake(final String make) {
    this.make = make;
  }

  public String getModel() {
    return model;
  }

  public void setModel(final String model) {
    this.model = model;
  }

  public String getEngineSize() {
    return engineSize;
  }

  public void setEngineSize(final String engineSize) {
    this.engineSize = engineSize;
  }

  @Override
  public String toString() {
    return "Car: \n" +
        " registration: " + registration +
        ",\n make: " + make +
        ",\n model: " + model +
        ",\n engineSize: " + engineSize;
  }
}
