package edu.epam.fop.lambdas.calculator;

import edu.epam.fop.lambdas.insurance.Car;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.Period;

public final class CarInsurancePolicies {

  private CarInsurancePolicies() {
    throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
  }

  public static InsuranceCalculator<Car> ageDependInsurance(LocalDate baseDate) {
    return null;
  }

  public static InsuranceCalculator<Car> priceAndOwningOfFreshCarInsurance(LocalDate baseDate,
      BigInteger priceThreshold, Period owningThreshold) {
    return null;
  }
}
