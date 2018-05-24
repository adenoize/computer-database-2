package main.java.com.excilys.cdb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import main.java.com.excilys.cdb.model.Company;
import main.java.com.excilys.cdb.model.Computer;
import main.java.com.excilys.cdb.service.CompanyService;

/**
 * Computer mapper.
 * @author Aurelien Denoize
 */
@Component
public class ComputerMapper  implements RowMapper<Computer>  {

    private static final Logger LOGGER = LoggerFactory.getLogger(ComputerMapper.class);

    @Autowired
    CompanyService companyService;

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
            computer.setCompany(findCompany(resultSet.getLong("company_id")));

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
    private Company findCompany(Long id) {

        try {
            return companyService.findById(id);
        } catch (NoSuchElementException e) {
            LOGGER.warn(e.getMessage());
        }
        return null;
    }

    /**
     * Mapper resultSet to computer.
     * @param resultSet the resultset
     * @param rowNum the number of rows
     * @return the Computer
     * @throws SQLException Exception if error into database
     */
    @Override
    public Computer mapRow(ResultSet resultSet, int rowNum) {
        Computer computer = new Computer();

        try {

            computer.setId(resultSet.getLong("id"));
            computer.setName(resultSet.getString("name"));
            computer.setIntroduced(
                    (resultSet.getDate("introduced") != null ? resultSet.getDate("introduced").toLocalDate() : null));
            computer.setDiscontinued(
                    (resultSet.getDate("discontinued") != null ? resultSet.getDate("discontinued").toLocalDate()
                            : null));
            computer.setCompany(findCompany(resultSet.getLong("company_id")));

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

        return computer;
    }

}
