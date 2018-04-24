package main.java.com.excilys.cdb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.java.com.excilys.cdb.constante.Constante;
import main.java.com.excilys.cdb.mapper.CompanyMapper;
import main.java.com.excilys.cdb.model.Company;
import main.java.com.excilys.cdb.model.Page;

/**
 * DAO about company.
 * @author aurel
 *
 */
public enum CompanyDao {
    INSTANCE;

    private static final String GET_PAGE = "SELECT * FROM company LIMIT ? OFFSET ?";
    private static final String FIND_BY_ID = "SELECT * FROM company WHERE id = ?";

    /**
     * Retrieve a page of companies.
     * @param offset index of the first company of the page
     * @return the page of company
     */
    public Page<Company> getPage(int offset) {

        List<Company> companies = new ArrayList<Company>();

        try (Connection connection = JdbcTool.INSTANCE.newConnection()) {

            PreparedStatement st = connection.prepareStatement(GET_PAGE);
            st.setInt(1, Constante.LIMIT_PAGE);
            st.setInt(2, offset);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                companies.add(CompanyMapper.INSTANCE.map(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new Page<Company>(companies);
    }

    /**
     * Retrieve the company with the given id.
     * @param id the id of company
     * @return the company
     */
    public Company findById(Long id) {
        Company company = null;

        try (Connection connection = JdbcTool.INSTANCE.newConnection()) {

            PreparedStatement st = connection.prepareStatement(FIND_BY_ID);
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                company = CompanyMapper.INSTANCE.map(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            company = null;
        }

        return company;
    }

}
