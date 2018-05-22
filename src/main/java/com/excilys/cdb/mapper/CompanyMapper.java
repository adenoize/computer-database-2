package main.java.com.excilys.cdb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import main.java.com.excilys.cdb.model.Company;

/**
 * Company mapper.
 * @author Aurelien Denoize
 *
 */
@Component
public class CompanyMapper {

    /**
     * Mapper resultset to Company.
     * @param resultSet the resultset
     * @return the Company
     */
    public Company map(ResultSet resultSet) {
        Company company = new Company();

        try {
            company.setId(resultSet.getLong("id"));
            company.setName(resultSet.getString("name"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return company;
    }

}
