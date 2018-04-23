package main.java.com.excilys.cdb.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Map;

import main.java.com.excilys.cdb.model.Company;
import main.java.com.excilys.cdb.model.Computer;
import main.java.com.excilys.cdb.model.Page;
import main.java.com.excilys.cdb.service.CompanyService;
import main.java.com.excilys.cdb.service.ComputerService;
import main.java.com.excilys.cdb.service.DatabaseException;

/**
 * @author Aurelien Denoize
 *
 */
public class CliController {

    ComputerService computerService = new ComputerService();
    CompanyService companyService = new CompanyService();

    /**
     * Add new Computer.
     * 
     * @param args
     *            list of values
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
                break;
            }
        }

        try {
            computerService.save(computer);
        } catch (DatabaseException e) {
            return "An error occurred !";
        }

        // TODO
        return computer.toString();

    }

    /**
     * Update a Computer.
     * 
     * @param id
     *            id of the computer
     * @param args
     *            list of values
     * @return the updated computer
     */
    public String updateComputer(String id, Map<String, String> args) {

        Computer computer = new Computer();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try {
            Long computerId = new Long(id);
            computer.setId(computerId);
        } catch (NumberFormatException e) {
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
                break;
            }
        }

        try {
            computerService.update(computer);
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
            return "Computer not found";
        } catch (DatabaseException e) {
            return "An error occurred !";
        }

        return "Computer is remove";
    }

    /**
     * List all companies.
     * @param page the number of the page
     * @return the page
     */
    public Page<Company> listCompany(int page) {

        return companyService.getPage(page);
    }

    public Page<Computer> listComputer(int page) {

        return computerService.getPage(page);
    }

    /**
     * Show the Computer with the given id
     */
    public String showComputer(String id) {

        // TODO
        Long computerId = new Long(id);
        String result = "";

        try {
            Computer computer = computerService.findById(computerId);
            // TODO
            result = computer.toString();

        } catch (DatabaseException e) {
            result = "Computer not found !";
        }

        return result;
    }

    public Company getCompany(Long id) {
        return companyService.findById(id);
    }

}
