package br.com.desafio.totalshake.controller.annotations;

import br.com.desafio.totalshake.controller.annotations.impl.DateTimeValidatorImpl;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DateTimeValidatorImpl.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface DateTimeValidator {
    String message() default "Datetime can't be in the future!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
