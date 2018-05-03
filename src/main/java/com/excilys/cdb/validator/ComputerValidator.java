package main.java.com.excilys.cdb.validator;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import main.java.com.excilys.cdb.model.Computer;

/**
 * @author Aurelien Denoize Excilys 2018
 */
public class ComputerValidator {

    /**
     * Validate a computer.
     * @param computer the computer to validate
     * @return Optional<String> String with error message or empty String if
     *         computer is valid
     */
    public List<String> validate(Computer computer) {

        List<String> messages = new ArrayList<>();

        if (StringUtils.isBlank(computer.getName())) {
            messages.add("Name is needed");
        }

        if (computer.getIntroduced() != null && computer.getDiscontinued() != null) {

            if (!computer.getIntroduced().isBefore(computer.getDiscontinued())) {
                messages.add("Date of introduction have to be before the date of discontinue");
            }

        }

        if (computer.getCompany() != null) {
            if (computer.getCompany() < 1) {
                messages.add("Company ID have to be positive");
            }
        }

        return messages;
    }

}
