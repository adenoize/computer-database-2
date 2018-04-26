package main.java.com.excilys.cdb.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import main.java.com.excilys.cdb.model.Company;
import main.java.com.excilys.cdb.model.Computer;
import main.java.com.excilys.cdb.model.Page;
import main.java.com.excilys.cdb.service.CompanyService;
import main.java.com.excilys.cdb.service.ComputerService;
import main.java.com.excilys.cdb.service.DatabaseException;

/**
 * @author Aurelien Denoize
 */
public class CliController {

    static final Logger LOGGER = LoggerFactory.getLogger(CliController.class);
    ComputerService computerService = new ComputerService();
    CompanyService companyService = new CompanyService();

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
                    computer.setCompany(companyId);

                } catch (NumberFormatException e) {
                    return "Error on company id";
                }

                break;
            default:
                return "Bad argument";
            }
        }

        try {
            computerService.save(computer);
            LOGGER.info("Add of computer : " + computer);
        } catch (DatabaseException e) {
            return "An error occurred !";
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

                    computer.setCompany(companyId);

                } catch (NumberFormatException e) {
                    return "Error on company id";
                }

                break;
            default:
                return "Bad argument";
            }
        }

        try {

            computerService.update(computer);
            LOGGER.info("Update of computer to : " + computer);
        } catch (DatabaseException e) {
            return "An error occurred !";
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
    public Company getCompany(Long id) {
        try {
            return companyService.findById(id);
        } catch (DatabaseException e) {
            e.printStackTrace();
            return null;
        }
    }

}
