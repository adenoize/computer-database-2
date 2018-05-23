package main.java.com.excilys.cdb.controller;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import main.java.com.excilys.cdb.dto.ComputerDTO;
import main.java.com.excilys.cdb.dto.PageDTO;
import main.java.com.excilys.cdb.model.Computer;
import main.java.com.excilys.cdb.model.Page;
import main.java.com.excilys.cdb.service.ComputerService;

@Controller
@RequestMapping("/")
public class DashboardController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DashboardController.class);

    @Autowired
    private ComputerService computerService;

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String getDashboard(@RequestParam String page, @RequestParam String search, ModelMap model) {

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

    // @RequestMapping(value = "/helloagain", method = RequestMethod.GET)
    // public String sayHelloAgain(ModelMap model) {
    // model.addAttribute("greeting", "Hello World Again, from Spring 4 MVC");
    // return "welcome";
    // }

}