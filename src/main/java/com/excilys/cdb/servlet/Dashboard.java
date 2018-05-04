package main.java.com.excilys.cdb.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.modelmapper.ModelMapper;

import main.java.com.excilys.cdb.dto.ComputerDTO;
import main.java.com.excilys.cdb.dto.PageDTO;
import main.java.com.excilys.cdb.exception.DatabaseException;
import main.java.com.excilys.cdb.model.Company;
import main.java.com.excilys.cdb.model.Computer;
import main.java.com.excilys.cdb.model.Page;
import main.java.com.excilys.cdb.service.CompanyService;
import main.java.com.excilys.cdb.service.ComputerService;

/**
 * Servlet implementation class Dashboard.
 */
@WebServlet("/dashboard")
public class Dashboard extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ComputerService computerService = new ComputerService();
    private CompanyService companyService = new CompanyService();

    private int limit;
    private int currPage;
    private int totalPages;
    private int numberComputers;
    private String search = "";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Dashboard() {
        super();
        limit = 10;
        currPage = 1;
        search = "";
        numberComputers = 0;
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

            try {
                Company company = companyService.findById(computer.getCompany());
                computerDTO.setCompany(company.getName());
            } catch (DatabaseException e) {
                computerDTO.setCompany("");
            }

            listDTO.add(computerDTO);

        }
        pageDTO.setPage(listDTO);

        request.setAttribute("page", pageDTO);
        request.setAttribute("limit", limit);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("currPage", currPage);
        request.setAttribute("searchval", search);
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

        doGet(request, response);
    }

};