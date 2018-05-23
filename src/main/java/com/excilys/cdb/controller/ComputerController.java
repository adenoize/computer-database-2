package main.java.com.excilys.cdb.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import main.java.com.excilys.cdb.exception.DatabaseException;
import main.java.com.excilys.cdb.exception.ValidatorException;
import main.java.com.excilys.cdb.model.Company;
import main.java.com.excilys.cdb.model.Computer;
import main.java.com.excilys.cdb.service.CompanyService;
import main.java.com.excilys.cdb.service.ComputerService;
import main.java.com.excilys.cdb.validator.ComputerValidator;

/**
 * @author Aurelien Denoize Excilys 2018
 */
@Controller
@RequestMapping("/computer")
public class ComputerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ComputerController.class);

    private ComputerValidator computerValidator = ComputerValidator.INSTANCE;

    @Autowired
    private ComputerService computerService;

    @Autowired
    private CompanyService companyService;

    /**
     * @return The JSP page
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addComputer(ModelMap model) {
        List<Company> companies = companyService.findAll();

        model.addAttribute("computer", new Computer());
        model.addAttribute("companies", companies);

        return "addComputer";
    }

    /**
     * @param computer The computer to save
     * @param result Binding
     * @return The JSP page
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String saveComputer(@ModelAttribute("computer") Computer computer, BindingResult result, ModelMap model) {

        try {
            computerValidator.validate(computer);
        } catch (ValidatorException e) {
            LOGGER.warn("Validator " + e.getMessage());
            model.addAttribute("error", e.getMessage());
            return "addComputer";
        }

        try {
            computerService.save(computer);
            return "redirect:/dashboard";

        } catch (DatabaseException e) {
            LOGGER.warn("Save Computer " + e.getMessage());
            model.addAttribute("error", "Error of saving");
            return "addComputer";

        }
    }

}
