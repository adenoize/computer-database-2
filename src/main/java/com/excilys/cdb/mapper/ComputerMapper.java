package main.java.com.excilys.cdb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import main.java.com.excilys.cdb.exception.DatabaseException;
import main.java.com.excilys.cdb.model.Company;
import main.java.com.excilys.cdb.model.Computer;
import main.java.com.excilys.cdb.service.CompanyService;

/**
 * Computer mapper.
 * @author Aurelien Denoize
 */
public enum ComputerMapper {
    INSTANCE;

    private static final Logger LOGGER = LoggerFactory.getLogger(ComputerMapper.class);

    CompanyService companyService = new CompanyService();

    /**
     * Mapper resultSet to computer.
     * @param resultSet the resultset
     * @return the Computer
     * @throws SQLException Exception if error into database
     */
    public Computer map(ResultSet resultSet) throws SQLException {
        Computer computer = new Computer();

        try {

            computer.setId(resultSet.getLong("id"));
            computer.setName(resultSet.getString("name"));
            computer.setIntroduced(
                    (resultSet.getDate("introduced") != null ? resultSet.getDate("introduced").toLocalDate() : null));
            computer.setDiscontinued(
                    (resultSet.getDate("discontinued") != null ? resultSet.getDate("discontinued").toLocalDate()
                            : null));
            computer.setCompany(findComputer(resultSet.getLong("company_id")));

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException();
        }

        return computer;
    }

    /**
     * Retrieve the company of the given id.
     * @param id the comapny id
     * @return the company
     */
    private Company findComputer(Long id) {

        try {
            return companyService.findById(id);
        } catch (DatabaseException e) {
            e.printStackTrace();
            LOGGER.warn(e.getMessage());
        }

        return null;

    }

}
