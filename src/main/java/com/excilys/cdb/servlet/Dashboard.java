package main.java.com.excilys.cdb.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import main.java.com.excilys.cdb.dto.ComputerDTO;
import main.java.com.excilys.cdb.dto.PageDTO;
import main.java.com.excilys.cdb.exception.DatabaseException;
import main.java.com.excilys.cdb.model.Computer;
import main.java.com.excilys.cdb.model.Page;
import main.java.com.excilys.cdb.service.ComputerService;

/**
 * Servlet implementation class Dashboard.
 */
@WebServlet(name = "Dashboard", urlPatterns = "/dashboard")
public class Dashboard extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final Logger LOGGER = LoggerFactory.getLogger(Dashboard.class);

    @Autowired
    private ComputerService computerService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     * @param request the request
     * @param response the response
     * @throws ServletException ServletException
     * @throws IOException IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int limit = 10;
        int currPage = 1;
        int totalPages = 0;
        int numberComputers = 0;
        String search = "";

        response.setContentType("text/html");
        String pageParam = request.getParameter("page");

        try {
            currPage = Integer.parseInt(pageParam);
        } catch (NumberFormatException e) {
            currPage = 1;
        }

        ModelMapper modelMapper = new ModelMapper();
        Page<Computer> page = null;
        PageDTO<ComputerDTO> pageDTO = new PageDTO<>();
        List<ComputerDTO> listDTO = new ArrayList<ComputerDTO>();

        search = request.getParameter("search");

        if (search == null || search.equals("")) {
            page = computerService.getPage(currPage, limit);
            numberComputers = computerService.count();
            totalPages = computerService.totalPages(limit);
        } else {
            page = computerService.getPageBySearch(currPage, limit, search);
            numberComputers = computerService.count(search);
            totalPages = computerService.totalPages(limit, search);
        }

        for (Computer computer : page.getPage()) {

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

        request.setAttribute("page", pageDTO);
        request.setAttribute("limit", limit);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("currPage", currPage);
        request.setAttribute("search", search);
        request.setAttribute("numberComputers", numberComputers);

        this.getServletContext().getRequestDispatcher("/jsp/dashboard.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     * @param request the request
     * @param response the response
     * @throws ServletException ServletException
     * @throws IOException IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String deletedComputers = request.getParameter("selection");
        String[] computerIds = deletedComputers.split(",");
        Long id = null;
        for (String computerId : computerIds) {

            try {
                id = Long.parseLong(computerId);
                computerService.removeById(id);
                request.setAttribute("info", "Computer was deleted");
                LOGGER.info("Computer with id=" + id + " was deleted.");
            } catch (DatabaseException e) {
                LOGGER.warn("Fail to delete computer with id " + id + "Exception : " + e.getMessage());
            } catch (NumberFormatException e) {
                LOGGER.debug("Delete computer : bad id, not a number" + e.getMessage());
            }
        }

        doGet(request, response);
    }
}