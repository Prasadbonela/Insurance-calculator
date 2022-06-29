package edu.epam.fop.lambdas.calculator;

import edu.epam.fop.lambdas.insurance.Accommodation;
import java.math.BigInteger;

public final class AccommodationInsurancePolicies {

  private AccommodationInsurancePolicies() {
    throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
  }

  static InsuranceCalculator<Accommodation> rentDependentInsurance(BigInteger divider) {
    return null;
  }

  static InsuranceCalculator<Accommodation> priceAndRoomsAndAreaDependentInsurance(BigInteger priceThreshold,
      int roomsThreshold, BigInteger areaThreshold) {
    return null;
  }
}
