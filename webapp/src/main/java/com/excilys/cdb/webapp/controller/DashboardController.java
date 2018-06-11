package com.excilys.cdb.webapp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.cdb.core.dto.ComputerDTO;
import com.excilys.cdb.core.dto.PageDTO;
import com.excilys.cdb.core.model.Computer;
import com.excilys.cdb.core.model.Page;
import com.excilys.cdb.service.exception.DatabaseException;
import com.excilys.cdb.service.service.ComputerService;

@Controller
@RequestMapping("/")
public class DashboardController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DashboardController.class);

  
    private ComputerService computerService;
    

    /**
     * @param computerService
     */
    public DashboardController(ComputerService computerService) {
        super();
        this.computerService = computerService;
    }

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String getDashboard(@RequestParam(value = "page", required = false) String page, @RequestParam(value = "search", required = false) String search, ModelMap model) {
        
        int limit = 10;
        int currPage = 1;
        int totalPages = 0;
        int numberComputers = 0;

        String pageParam = page;

        try {
            currPage = Integer.parseInt(pageParam);
        } catch (NumberFormatException e) {
            currPage = 1;
        }

        ModelMapper modelMapper = new ModelMapper();
        Page<Computer> computerPage = null;
        PageDTO<ComputerDTO> pageDTO = new PageDTO<>();
        List<ComputerDTO> listDTO = new ArrayList<ComputerDTO>();

        if (search == null || search.equals("")) {
            computerPage = computerService.getPage(currPage, limit);
            numberComputers = computerService.count();
            totalPages = computerService.totalPages(limit);
        } else {
            computerPage = computerService.getPageBySearch(currPage, limit, search);
            numberComputers = computerService.count(search);
            totalPages = computerService.totalPages(limit, search);
        }

        for (Computer computer : computerPage.getPage()) {

            ComputerDTO computerDTO = new ComputerDTO();
            modelMapper.map(computer, computerDTO);
            if (computer.getCompany() == null) {
                computerDTO.setCompany("");
            } else {
                computerDTO.setCompany(computer.getCompany().getName());
            }

            listDTO.add(computerDTO);

        }
        pageDTO.setPage(listDTO);

        model.addAttribute("page", pageDTO);
        model.addAttribute("limit", limit);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currPage", currPage);
        model.addAttribute("search", search);
        model.addAttribute("numberComputers", numberComputers);

        return "dashboard";
    }

    @RequestMapping(value = "/dashboard", method = RequestMethod.POST)
    public String delete(@RequestParam(value = "selection", required = false) String selection, ModelMap model) {

        String[] computerIds = selection.split(",");
        Long id = null;
        for (String computerId : computerIds) {

            try {
                id = Long.parseLong(computerId);
                computerService.removeById(id);
                model.addAttribute("info", "Computer was deleted");
                LOGGER.info("Computer with id=" + id + " was deleted.");
            } catch (DatabaseException e) {
                LOGGER.warn("Fail to delete computer with id " + id + "Exception : " + e.getMessage());
            } catch (NumberFormatException e) {
                LOGGER.debug("Delete computer : bad id, not a number" + e.getMessage());
                model.addAttribute("info", "Bad selection");
            }
        }

        return "redirect:dashboard";
    }
    
    
}