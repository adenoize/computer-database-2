package com.excilys.cdb.core.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = DatePeriodValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface DatePeriod {

    /**
     * Message display.
     * @return message to display
     */
    String message() default "The introduction date have to be before the discontinued date";

    /**
     * @return the date formatted
     */
    String introduced();

    /**
     * @return the date formatted
     */
    String discontinued();

    /**
     * @return .
     */
    Class<?>[] groups() default {};

    /**
     * @return .
     */
    Class<? extends Payload>[] payload() default {};

}