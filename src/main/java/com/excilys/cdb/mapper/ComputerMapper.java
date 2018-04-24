package main.java.com.excilys.cdb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import main.java.com.excilys.cdb.model.Computer;

/**
 * Computer mapper.
 * @author Aurelien Denoize
 *
 */
public enum ComputerMapper {
    INSTANCE;

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
            computer.setCompany(resultSet.getLong("company_id"));

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException();
        }

        return computer;
    }

}
