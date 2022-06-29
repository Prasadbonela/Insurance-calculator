# Insurance calculator

The purpose of this task is to practice with Optional, and its
main methods such as `map`, `filter`, `flatMap`, etc.

Duration: _3 hours_

## Description

In this task you're provided with some classes, which is used for insurance
calculations. In `insurance` package you have:
1. `Subject` - the main sealed interface, which is
implemented by classes which represents insurance subject
2. `Car`, `Accommodation`, `Person` - main domain classes,
which are implementing `Subject`, we will calculate insurance for them
3. `Currency` - just an enum of provided currencies
4. `RepeatablePayment` - represent for example rent, salary or loan
5. `Injury`, `Family` - not subject classes, which is used
for described a certain part of the subject classes

These classes a mostly plain data objects without any logic.
The only things to highlight are:
1. Some getters return `Optional` instead of target type
2. Some of these classes implements `Comparable` interface,
so it'd be easier to retrieve the required instance from
`SortedSet`

Also, you have `calculator` package, here you have 2 main classes:
1. `InsuranceCoefficient` - a wrapper for `int` value in range [0; 100]. Represents a percent of clients trustworthy.
2. `InsuranceCalculator<S extends Subject>` - functional interface,
which determines `InsuranceCoefficient` based on the passed `S` instance

Also, here you can find several `*InsurancePolicies` classes,
which provides methods which must be implemented.
Let's take a closer look at them and define the requirements.

## Requirements

All implementations must not throw an exception and must
be null-safe. All objects might be `null` or return `null`.
The getters which returns `Optional` never return `null`.

All arguments are never `null`.

### AccommodationInsurancePolicies

#### rentDependentInsurance

Accepts `BigInteger divider` which is used to adjust insurance coefficient.

Must return an insurance for a renting accommodation, with monthly payment
in USD. As a result for insurance coefficient you must divide the rent cost
by the provided divider. If the resulting insurance coefficient is more than 100,
then return MAX (100). If some condition is not met, then return empty Optional.

1. accommodation must have `rent`
2. rent period must be in months
3. rent currency must be USD
4. rent amount must be greater than 0
5. coefficient is calculated as rent amount relation to the provided divider in percents
6. if the relation is more than 100%, then return 100%
7. default value is `Optional.empty`

#### priceAndRoomsAndAreaDependentInsurance

Accepts `BigInteger priceThreshold` which must be met or exceeded by the accommodation price,
`int roomsThreshold` which must be met or exceeded by the room counts
and `areaThreshold` the same but for accommodation area.

Accommodations price, rooms count and area must be greater or equal to their corresponding thresholds.
If all the conditions are met, then return `InsuranceCoefficient.MAX` (100) and
`InsuranceCoefficient.MIN` (0) otherwise.

1. accommodation price >= priceThreshold
2. accommodation rooms >= roomsThreshold
3. accommodation area >= areaThreshold
4. return 100 coefficient if all conditions are met
5. return 0 coefficient if one of them is not

### CarInsurancePolicies

#### ageDependInsurance

Accepts `LocalDate baseDate` which is used as pivot point for calculating,
the all calculations must use it. Date-math precision is 1 day.

This calculator must return:
1. 100 coefficient if manufactured date is not earlier than 1 year from `baseDate`
2. 70 coefficient if 5 years
3. 30 coefficient if 10 years
4. 0 coefficient if car is older than 10 years.
5. If manufactured date is not known, then `Optional.empty` must be returned

#### priceAndOwningOfFreshCarInsurance

Accepts `LocalDate baseDate` as base for date-math calculations,
`BigInteger priceThreshold` which must be met or exceeded by cars price
and `Period owningThreshold` - the minimum period of owning the car by a person.

The idea is to calculate insurance coefficient only for cars
which is still owned by a person (`soldDate` is not set),
price is greater or equal to `priceThreshold`,
the period from `purchaseDate` to `baseDate` is greater or equal to `owningThreshold`.

The resulting coefficient must be based on cars price and `priceThreshold`.
If the price of the car is exceeds `priceThreshold` 3 times or more, then
100 coefficient must be returned. If it exceeds 2 times, but less than 3, then
50 coefficient. And 0 coefficient for the price from threshold up to 2 times threshold.
If some condition is not met, then `Optional.empty` must be returned.

1. `soldDate` must not be set
2. `price` must be >= than `priceThreshold`
3. `purchaseDate` + `owningThreshold` must be >= `baseDate`
4. must return 100 coefficient if `price` >= 3 * `priceThreshold`
5. must return 50 coefficient if 3 * `priceThreshold` > `price` >= 2 * `priceThreshold`
6. must return 0 coefficient if `price` < 2 * `priceThreshold`
7. must return `Optional.empty` if any condition is not met

### PersonInsurancePolicies

#### childrenDependent

Accepts `int childrenCountThreshold`. Must calculate coefficient based on
persons children count relatively to the provided threshold.

1. person must have a `family`
2. person must have `children`
3. the resulting coefficient equals to `max(100, childrenCount / threshold)`
4. 0 coefficient must return if any condition is not met

#### employmentDependentInsurance

Accepts `BigInteger salaryThreshold` and `Set<Currency> currencies`,
which together defines the threshold of salary based on its amount and allowed
currency.

1. Person must have an employment history of at least 4 records
2. They must have multi-currency account (contains more than 1 currency)
3. Must not have any injuries records
4. Must have at least 1 accommodation (owned or rented - does not matter)
5. They still must be employed (so last employment record does not have `endDate`)
6. `salary` on this job is one of the provided `currencies`
7. `salary.amount` is more or equal to `salaryThreshold`
8. If all conditions are met, then return 50 coefficient
9. If any condition is not met, then return `Optional.empty`

#### accommodationEmergencyInsurance

Accepts `Set<EmergencyStatus> statuses` which defines allowed emergency statuses
of accommodation owned or rented by a person.

1. Person must have accommodation
2. Calculator must pick the smallest by area accommodation
3. Check if its emergency status is as one of the provided in `statuses`
4. Calculate a coefficient as `100 * (1 - status.ordinal() / totalStatuses)`
5. If any condition is not met, then return `Optional.empty`

#### injuryAndRentDependentInsurance

Accepts `BigInteger rentThreshold`.

1. Person must have any injury
2. This person must be a culprit of the last injury they have
3. The biggest (by area) accommodation must be rented
4. The rent must be in GBP
5. The coefficient is calculated as `max(100, 100 * rent / threshold)`
6. If any condition is not met, then return `Optional.empty`
