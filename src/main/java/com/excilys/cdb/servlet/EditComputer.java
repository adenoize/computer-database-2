package main.java.com.excilys.cdb.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import main.java.com.excilys.cdb.exception.DatabaseException;
import main.java.com.excilys.cdb.exception.ValidatorException;
import main.java.com.excilys.cdb.model.Company;
import main.java.com.excilys.cdb.model.Computer;
import main.java.com.excilys.cdb.service.CompanyService;
import main.java.com.excilys.cdb.service.ComputerService;
import main.java.com.excilys.cdb.validator.ComputerValidator;

/**
 * Servlet implementation class EditComputer.
 */
@WebServlet("/editComputer")
public class EditComputer extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final Logger LOGGER = LoggerFactory.getLogger(AddComputer.class);

    private ComputerService computerService = new ComputerService();
    private CompanyService companyService = new CompanyService();

    private ComputerValidator computerValidator = ComputerValidator.INSTANCE;

    private Long id;
    private String computerName;
    private LocalDate introduced;
    private LocalDate discontinued;
    private Company company;
    private Computer computer;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
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

        try {
            id = Long.parseLong(request.getParameter("id"));
        } catch (NumberFormatException e) {
            errorUrl(request, response);
            return;
        }

        try {
            computer = computerService.findById(id);
        } catch (DatabaseException e) {
            LOGGER.warn(e.getMessage());
            response.sendRedirect("404.jsp");
            return;
        }

        request.setAttribute("companies", companies);
        request.setAttribute("id", computer.getId());
        request.setAttribute("computerName", computer.getName());
        request.setAttribute("introduced", computer.getIntroduced());
        request.setAttribute("discontinued", computer.getDiscontinued());
        request.setAttribute("companyId", computer.getCompany());

        this.getServletContext().getRequestDispatcher("/jsp/editComputer.jsp").forward(request, response);
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

        Computer computer = buildComputer(request);

        try {
            computerValidator.validate(computer);
        } catch (ValidatorException e) {
            LOGGER.warn("Validator " + e.getMessage());
            request.setAttribute("error", e.getMessage());
            doGet(request, response);
            return;
        }

        try {
            computerService.update(computer);
            response.sendRedirect("dashboard");

        } catch (DatabaseException e) {
            LOGGER.warn("Edit Computer id = " + id + " with cause :" + e.getMessage());
            request.setAttribute("error", "Error of editing");
            doGet(request, response);

        }
    }

    /**
     * @see HttpServlet#errorUrl(HttpServletRequest request, HttpServletResponse
     *      response)
     * @param request the request
     * @param response the response
     * @throws IOException IOException
     */
    private void errorUrl(HttpServletRequest request, HttpServletResponse response) throws IOException {
        LOGGER.warn("Bad request on '/editComputer' with param id=" + request.getParameter("id"));
        response.sendRedirect("404.jsp");
    }

    /**
     * Construct computers with data from form.
     * @param request the servlet request object
     * @return the computer
     */
    private Computer buildComputer(HttpServletRequest request) {
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
            Long companyId = Long.valueOf(request.getParameter("companyId"));
            if (companyId > 0) {
                try {
                    company = companyService.findById(companyId);
                } catch (DatabaseException e) {
                    company = null;
                }
            }
        } catch (NumberFormatException e) {
            company = null;
        }

        Computer computer = new Computer(computerName, introduced, discontinued, company);
        computer.setId(id);
        return computer;
    }

}
