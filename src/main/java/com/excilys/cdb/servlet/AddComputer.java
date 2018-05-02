package main.java.com.excilys.cdb.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.com.excilys.cdb.model.Company;
import main.java.com.excilys.cdb.model.Computer;
import main.java.com.excilys.cdb.service.CompanyService;
import main.java.com.excilys.cdb.service.ComputerService;
import main.java.com.excilys.cdb.service.DatabaseException;

/**
 * Servlet implementation class AddComputer.
 */
@WebServlet("/addComputer")
public class AddComputer extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ComputerService computerService = new ComputerService();
    private CompanyService companyService = new CompanyService();

    private String computerName;
    private LocalDate introduced;
    private LocalDate discontinued;
    private Long companyId;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddComputer() {
        super();
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

        List<Company> companies = null;

        try {
            companies = companyService.findAll();
        } catch (DatabaseException e) {
            e.printStackTrace();
        }

        request.setAttribute("companies", companies);

        this.getServletContext().getRequestDispatcher("/jsp/addComputer.jsp").forward(request, response);
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

        computerName = request.getParameter("computerName");

        try {
            introduced = LocalDate.parse(request.getParameter("introduced"), DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeParseException e) {
            introduced = null;
        }
        try {
            discontinued = LocalDate.parse(request.getParameter("discontinued"), DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeParseException e) {
            discontinued = null;
        }
        try {
            companyId = Long.valueOf(request.getParameter("companyId"));
        } catch (NumberFormatException e) {
            companyId = null;
        }

        if (companyId == 0) {
            companyId = null;
        }

        Computer computer = new Computer(computerName, introduced, discontinued, companyId);

        try {
            computerService.save(computer);
            response.sendRedirect("dashboard");

        } catch (DatabaseException e) {
            e.printStackTrace();
            doGet(request, response);

        }

    }

}
