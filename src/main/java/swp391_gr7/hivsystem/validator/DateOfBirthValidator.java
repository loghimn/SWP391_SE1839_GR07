package swp391_gr7.hivsystem.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class DateOfBirthValidator implements ConstraintValidator<DateOfBirthConstraint, LocalDate> {

    private int minAge;

    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {
        if (Objects.isNull(localDate)) {
            return false; // Date of birth cannot be null
        }

        long years = ChronoUnit.YEARS.between(localDate, LocalDate.now());

        if (years < minAge) {
            return false; // User must be at least minAge years old
        }
        return true; // Valid date of birth
    }

    @Override
    public void initialize(DateOfBirthConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        minAge = constraintAnnotation.minAge();
    }
}
