package main.java.com.excilys.cdb.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import main.java.com.excilys.cdb.dto.ComputerDTO;
import main.java.com.excilys.cdb.exception.DatabaseException;
import main.java.com.excilys.cdb.exception.MapperException;
import main.java.com.excilys.cdb.exception.ValidatorException;
import main.java.com.excilys.cdb.mapper.ComputerDTOMapper;
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

    @Autowired
    private ComputerDTOMapper computerDTOMapper;

    /**
     * @return The JSP page
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addComputer(ModelMap model) {
        List<Company> companies = companyService.findAll();

        model.addAttribute("computer", new ComputerDTO());
        model.addAttribute("companies", companies);

        return "addComputer";
    }

    /**
     * Save Computer function.
     * @param computerDTO The computer to save
     * @param result Binding
     * @return The JSP page
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String saveAddComputer(@ModelAttribute("computer") @Valid ComputerDTO computerDTO, BindingResult result,
            ModelMap model) {

        Computer computer = null;

        if (result.hasErrors()) {
            return "addComputer";
        }

        try {
            computer = computerDTOMapper.toComputer(computerDTO);
        } catch (MapperException e) {
            LOGGER.warn("Validator " + e.getMessage());
        }

//        try {
//            computerValidator.validate(computer);
//        } catch (ValidatorException e) {
//            LOGGER.warn("Validator " + e.getMessage());
//            model.addAttribute("error", e.getMessage());
//            return "addComputer";
//        }

        try {
            computerService.save(computer);
            LOGGER.info("Save Computer " + computer);
            return "redirect:/dashboard";

        } catch (DatabaseException e) {
            LOGGER.warn("Save Computer " + e.getMessage());
            model.addAttribute("error", "Error of saving");
            return "addComputer";
        }
    }

    /**
     * @return The JSP page
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String editComputer(@ModelAttribute(value = "id") String id, ModelMap model) {
        List<Company> companies = companyService.findAll();
        Computer computer = null;
        Long companyId = null;
        ComputerDTO computerDTO = null;

        try {
            companyId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            return "redirect:/error/404";
        }
        try {
            computer = computerService.findById(companyId);
        } catch (DatabaseException e) {
            LOGGER.warn(e.getMessage());
            return "redirect:/error/404";
        }

        computerDTO = computerDTOMapper.toDTO(computer);

        model.addAttribute("computer", computerDTO);
        model.addAttribute("companies", companies);

        return "editComputer";
    }

    /**
     * Save Computer function.
     * @param computerDTO The computer to save
     * @param result Binding
     * @return The JSP page
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String saveEditComputer(@ModelAttribute("computer") ComputerDTO computerDTO, BindingResult result,
            ModelMap model) {

        Computer computer = null;

        try {
            computer = computerDTOMapper.toComputer(computerDTO);
        } catch (MapperException e) {
            LOGGER.warn("Mapper " + e.getMessage());
            return "redirect:/404";
        }

        try {
            computerValidator.validate(computer);
        } catch (ValidatorException e) {
            LOGGER.warn("Validator " + e.getMessage());
            model.addAttribute("error", e.getMessage());
            return "editComputer";
        }

        try {
            computerService.update(computer);
            LOGGER.info("Edit Computer " + computer);
            return "redirect:/dashboard";

        } catch (DatabaseException e) {
            LOGGER.warn("Edit Computer " + e.getMessage());
            model.addAttribute("error", "Error of updating");
            return "editComputer";
        }
    }
}
