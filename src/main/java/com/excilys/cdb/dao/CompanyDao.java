package main.java.com.excilys.cdb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import main.java.com.excilys.cdb.constante.Constante;
import main.java.com.excilys.cdb.mapper.CompanyMapper;
import main.java.com.excilys.cdb.model.Company;
import main.java.com.excilys.cdb.model.Page;

/**
 * DAO about company.
 * @author aurel
 */
public enum CompanyDao {
    INSTANCE;

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyDao.class);

    private static final String FIND_ALL = "SELECT id, name FROM company";
    private static final String GET_PAGE = "SELECT id, name FROM company LIMIT ? OFFSET ?";
    private static final String FIND_BY_ID = "SELECT id, name FROM company WHERE id = ?";

    /**
     * Retrieve a page of companies.
     * @param offset index of the first company of the page
     * @return the page of company
     */
    public Page<Company> getPage(int offset) {

        List<Company> companies = new ArrayList<Company>();

        if (offset < 0) {
            offset = 0;
        }

        try (Connection connection = DataSource.getConnection()) {

            PreparedStatement st = connection.prepareStatement(GET_PAGE);
            st.setInt(1, Constante.LIMIT_PAGE);
            st.setInt(2, offset);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                companies.add(CompanyMapper.INSTANCE.map(rs));
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

        return new Page<Company>(companies);
    }

    /**
     * Retrieve the company with the given id.
     * @param id the id of company
     * @return the company
     */
    public Optional<Company> findById(Long id) {
        Company company = null;

        try (Connection connection = DataSource.getConnection()) {

            PreparedStatement st = connection.prepareStatement(FIND_BY_ID);
            st.setLong(1, id);
            ResultSet resultSet = st.executeQuery();

            if (resultSet.next()) {
                company = CompanyMapper.INSTANCE.map(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

        return Optional.ofNullable(company);
    }

    /**
     * Retrieve the company with the given id.
     * @return the company
     */
    public List<Company> findAll() {
        List<Company> companies = new ArrayList<>();

        try (Connection connection = DataSource.getConnection()) {

            Statement st = connection.createStatement();

            ResultSet resultSet = st.executeQuery(FIND_ALL);

            while (resultSet.next()) {
                companies.add(CompanyMapper.INSTANCE.map(resultSet));
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

        return companies;
    }

}
