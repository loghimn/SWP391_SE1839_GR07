package swp391_gr7.hivsystem.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD })
@Retention( RUNTIME )
@Constraint( validatedBy = { ProvinceValidator.class } )
public @interface ValidProvinceConstraint {
    String message() default "Address must be the name of a province/city in Vietnam";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
