package main.java.com.excilys.cdb.validator;

import org.apache.commons.lang3.StringUtils;

import main.java.com.excilys.cdb.exception.ValidatorException;
import main.java.com.excilys.cdb.model.Computer;

/**
 * @author Aurelien Denoize Excilys 2018
 */
public enum ComputerValidator {
    INSTANCE;

    /**
     * Validate a computer.
     * @param computer the computer to validate computer is valid
     * @throws ValidatorException Validation exception
     */
    public void validate(Computer computer) throws ValidatorException {

        if (StringUtils.isBlank(computer.getName())) {
            throw new ValidatorException("Name is needed");

        }

        if (computer.getIntroduced() != null && computer.getDiscontinued() != null) {

            if (!computer.getIntroduced().isBefore(computer.getDiscontinued())) {
                throw new ValidatorException("The introduction date have to be before the discontinued date");
            }

        }

        if (computer.getCompany() != null) {
            if (computer.getCompany().getId() < 1L) {
                throw new ValidatorException("Company ID have to be positive");
            }
        }
    }

}
