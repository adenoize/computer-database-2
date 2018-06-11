package com.excilys.cdb.core.validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapperImpl;

/**
 * @author Aurelien Denoize Excilys 2018
 */
public class DatePeriodValidator implements ConstraintValidator<DatePeriod, Object> {

    private String introduced;
    private String discontinued;

    @Override
    public void initialize(DatePeriod dates) {
        this.introduced = dates.introduced();
        this.discontinued = dates.discontinued();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {

        String introducedValue = (String) new BeanWrapperImpl(value).getPropertyValue(this.introduced);
        String discontinuedValue = (String) new BeanWrapperImpl(value).getPropertyValue(this.discontinued);

        

        if (introducedValue == null || introducedValue.isEmpty() || discontinuedValue == null || discontinuedValue.isEmpty()) {
            return true;
        }
        
        LocalDate introducedDate = LocalDate.parse(introducedValue, DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate discontinuedDate = LocalDate.parse(discontinuedValue, DateTimeFormatter.ISO_LOCAL_DATE);

        return introducedDate.isBefore(discontinuedDate);
    }

}
