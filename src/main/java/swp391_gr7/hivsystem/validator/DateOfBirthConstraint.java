package swp391_gr7.hivsystem.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD })
@Retention( RUNTIME )
@Constraint( validatedBy = { DateOfBirthValidator.class } )
public @interface DateOfBirthConstraint {

    String message() default "Invalid date of birth";

    int minAge();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
