package br.com.desafio.totalshake.controller.annotations.impl;

import br.com.desafio.totalshake.controller.annotations.DateTimeValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;

public class DateTimeValidatorImpl implements ConstraintValidator <DateTimeValidator, LocalDateTime> {
    @Override
    public void initialize(DateTimeValidator constraintAnnotation) {  }

    @Override
    public boolean isValid(LocalDateTime dataHora, ConstraintValidatorContext constraintValidatorContext) {
        return LocalDateTime.now().compareTo(dataHora) > 0;
    }
}
