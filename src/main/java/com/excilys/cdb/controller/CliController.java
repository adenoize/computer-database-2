package main.java.com.excilys.cdb.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import main.java.com.excilys.cdb.config.AppConfig;
import main.java.com.excilys.cdb.exception.DatabaseException;
import main.java.com.excilys.cdb.exception.ValidatorException;
import main.java.com.excilys.cdb.model.Company;
import main.java.com.excilys.cdb.model.Computer;
import main.java.com.excilys.cdb.model.Page;
import main.java.com.excilys.cdb.service.CompanyService;
import main.java.com.excilys.cdb.service.ComputerService;
import main.java.com.excilys.cdb.validator.ComputerValidator;

/**
 * @author Aurelien Denoize
 */
public class CliController {

    static final Logger LOGGER = LoggerFactory.getLogger(CliController.class);

    private AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

    private ComputerService computerService = (ComputerService) context.getBean("computerService");
    private CompanyService companyService = (CompanyService) context.getBean("companyService");

    /**
     * Add new Computer.
     * @param args list of values
     * @return the computer
     */
    public String addComputer(Map<String, String> args) {

        Computer computer = new Computer();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (Map.Entry<String, String> entry : args.entrySet()) {
            String key = entry.getKey();
            final String value = entry.getValue();

            switch (key) {
            case "name":
                computer.setName(value);

                break;
            case "introduced":
                try {
                    LocalDate introduced = LocalDate.parse(value, formatter);
                    computer.setIntroduced(introduced);
                } catch (DateTimeParseException e) {
                    return "Error on date";
                }

                break;
            case "discontinued":

                try {
                    LocalDate discontinued = LocalDate.parse(value, formatter);
                    computer.setDiscontinued(discontinued);

                } catch (DateTimeParseException e) {
                    return "Error on date";
                }

                break;
            case "company":
                try {
                    Long companyId = Long.parseLong(value);

                    if (companyId < 1L) {
                        return "Company ID have to be positive";
                    }

                    try {
                        computer.setCompany(companyService.findById(companyId));
                    } catch (NoSuchElementException e) {
                        return "Error on company id";
                    }

                } catch (NumberFormatException e) {
                    return "Error on company id";
                }

                break;
            default:
                return "Bad argument";
            }
        }

        try {

            ComputerValidator.INSTANCE.validate(computer);
            computerService.save(computer);
            LOGGER.info("Add of computer : " + computer);
        } catch (DatabaseException e) {
            return "An error occurred !";
        } catch (ValidatorException e) {
            return e.getMessage();
        }
        return computer.toString();
    }

    /**
     * Update a Computer.
     * @param id id of the computer
     * @param args list of values
     * @return the updated computer
     */
    public String updateComputer(String id, Map<String, String> args) {

        Computer computer = new Computer();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try {
            Long computerId = new Long(id);
            computerService.findById(computerId);
            computer.setId(computerId);
        } catch (NumberFormatException e) {
            return "Error on id format";
        } catch (DatabaseException e) {
            return "Computer not found";
        }

        for (Map.Entry<String, String> entry : args.entrySet()) {
            String key = entry.getKey();
            final String value = entry.getValue();

            switch (key) {
            case "name":
                computer.setName(value);

                break;
            case "introduced":
                try {
                    LocalDate introduced = LocalDate.parse(value, formatter);
                    computer.setIntroduced(introduced);
                } catch (DateTimeParseException e) {
                    return "Error on date";
                }

                break;
            case "discontinued":
                try {
                    LocalDate discontinued = LocalDate.parse(value, formatter);
                    computer.setDiscontinued(discontinued);

                } catch (DateTimeParseException e) {
                    return "Error on date";
                }

                break;
            case "company":
                try {
                    Long companyId = Long.parseLong(value);

                    if (companyId < 1L) {
                        return "Company ID have to be positive";
                    }

                    try {
                        computer.setCompany(companyService.findById(companyId));
                    } catch (NoSuchElementException e) {
                        return "Error on company id";
                    }

                } catch (NumberFormatException e) {
                    return "Error on company id";
                }

                break;
            default:
                return "Bad argument";
            }
        }

        try {
            ComputerValidator.INSTANCE.validate(computer);
            computerService.update(computer);
            LOGGER.info("Update of computer to : " + computer);
        } catch (DatabaseException e) {
            return "An error occurred !";
        } catch (ValidatorException e) {
            return e.getMessage();
        }

        return computer.toString();

    }

    /**
     * Remove a computer.
     * @param id the id of the computer
     * @return some messages
     */
    public String removeComputer(String id) {

        Long computerId = null;

        try {
            computerId = new Long(id);
            computerService.removeById(computerId);
        } catch (NumberFormatException e) {
            return "Error on id format";
        } catch (DatabaseException e) {
            return "Computer not found";
        }

        LOGGER.info("Remove of computer with id = " + id);
        return "Computer is remove";
    }

    /**
     * Remove a computer.
     * @param id the id of the computer
     * @return some messages
     */
    public String removeCompany(String id) {

        Long computerId = null;

        try {
            computerId = new Long(id);
            companyService.removeById(computerId);
        } catch (NumberFormatException e) {
            return "Error on id format";
        } catch (DatabaseException e) {
            return "Error : company not found or database error";
        }

        LOGGER.info("Remove of company with id = " + id);
        return "Company is remove";
    }

    /**
     * List all companies per page.
     * @param page the number of the page
     * @return the page
     */
    public Page<Company> listCompany(int page) {

        return companyService.getPage(page);
    }

    /**
     * List all computers per page.
     * @param page the number of the page
     * @return the page
     */
    public Page<Computer> listComputer(int page) {

        return computerService.getPage(page);
    }

    /**
     * Show the Computer with the given id.
     * @param id the id of computer
     * @return informations of the computer
     */
    public String showComputer(String id) {

        String result = "";
        Long computerId = null;

        try {
            computerId = new Long(id);
        } catch (NumberFormatException e) {
            return "Error on id format";
        }

        try {
            Computer computer = computerService.findById(computerId);
            result = computer.toString();

        } catch (DatabaseException e) {
            result = "Computer not found !";
        }

        return result;
    }

    /**
     * Retrieve the company.
     * @param id the id of the company
     * @return the company
     */
    public Optional<Company> getCompany(Long id) {
        try {
            return Optional.ofNullable(companyService.findById(id));
        } catch (NoSuchElementException e) {
            LOGGER.warn("Company with the id= " + id + " not found.");
            return Optional.empty();
        }
    }

}
