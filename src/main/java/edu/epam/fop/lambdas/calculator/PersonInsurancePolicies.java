package edu.epam.fop.lambdas.calculator;

import edu.epam.fop.lambdas.insurance.Accommodation.EmergencyStatus;
import edu.epam.fop.lambdas.insurance.Currency;
import edu.epam.fop.lambdas.insurance.Person;
import java.math.BigInteger;
import java.util.Set;

public final class PersonInsurancePolicies {

  private PersonInsurancePolicies() {
    throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
  }

  public static InsuranceCalculator<Person> childrenDependent(int childrenCountThreshold) {
    return null;
  }

  public static InsuranceCalculator<Person> employmentDependentInsurance(BigInteger salaryThreshold,
      Set<Currency> currencies) {
    return null;
  }

  public static InsuranceCalculator<Person> accommodationEmergencyInsurance(Set<EmergencyStatus> statuses) {
    return null;
  }

  public static InsuranceCalculator<Person> injuryAndRentDependentInsurance(BigInteger rentThreshold) {
    return null;
  }
}
