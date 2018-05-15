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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import main.java.com.excilys.cdb.exception.DatabaseException;
import main.java.com.excilys.cdb.exception.ValidatorException;
import main.java.com.excilys.cdb.model.Company;
import main.java.com.excilys.cdb.model.Computer;
import main.java.com.excilys.cdb.service.CompanyService;
import main.java.com.excilys.cdb.service.ComputerService;
import main.java.com.excilys.cdb.validator.ComputerValidator;

/**
 * Servlet implementation class AddComputer.
 */
@WebServlet("/addComputer")
public class AddComputer extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final Logger LOGGER = LoggerFactory.getLogger(AddComputer.class);

    private ComputerService computerService = new ComputerService();
    private CompanyService companyService = new CompanyService();

    private ComputerValidator computerValidator = ComputerValidator.INSTANCE;

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

        List<Company> companies = companyService.findAll();

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
            if (companyId == 0) {
                companyId = null;
            }
        } catch (NumberFormatException e) {
            companyId = null;
        }

        Computer computer = new Computer(computerName, introduced, discontinued, companyId);

        try {
            computerValidator.validate(computer);
        } catch (ValidatorException e) {
            LOGGER.warn("Validator " + e.getMessage());
            request.setAttribute("error", e.getMessage());
            doGet(request, response);
            return;
        }

        try {
            computerService.save(computer);
            response.sendRedirect("dashboard");

        } catch (DatabaseException e) {
            LOGGER.warn("Save Computer " + e.getMessage());
            request.setAttribute("error", "Error of saving");
            doGet(request, response);

        }

    }

}
