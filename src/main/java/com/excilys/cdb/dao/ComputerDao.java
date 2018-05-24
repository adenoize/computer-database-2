package main.java.com.excilys.cdb.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;

import main.java.com.excilys.cdb.constante.Constante;
import main.java.com.excilys.cdb.mapper.ComputerMapper;
import main.java.com.excilys.cdb.model.Company;
import main.java.com.excilys.cdb.model.Computer;
import main.java.com.excilys.cdb.model.Page;

/**
 * DAO about Computer.
 * @author Aurelien Denoize
 */
@Repository
public class ComputerDao {

    private static final String CREATE = "INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (?,?,?,?);";
    private static final String UPDATE = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?";
    private static final String REMOVE = "DELETE FROM computer where id = ?";
    private static final String GET_PAGE = "SELECT id, name, introduced, discontinued, company_id FROM computer LIMIT ? OFFSET ?";
    private static final String FIND_BY_ID = "SELECT id, name, introduced, discontinued, company_id FROM computer WHERE id = ?";
    private static final String COUNT = "SELECT count(id) FROM computer";
    private static final String COUNT_PAGE_SEARCH = "SELECT count(id) FROM computer WHERE name LIKE ? OR company_id = ANY ( SELECT id FROM company WHERE name LIKE ? )";
    private static final String GET_PAGE_SEARCH = "SELECT id, name, introduced, discontinued, company_id FROM computer WHERE name LIKE ? OR company_id = ANY ( SELECT id FROM company WHERE name LIKE ? ) ORDER BY name LIMIT ? OFFSET ?";
    private static final String FIND_BY_COMPANYID = "SELECT id, name, introduced, discontinued, company_id FROM computer WHERE company_id = ? LIMIT ? OFFSET ?";
    private static final String COUNT_BY_COMPANYID = "SELECT count(id) FROM computer WHERE company_id = ?";

    private JdbcTemplate jdbcTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(ComputerDao.class);

    @Autowired
    private ComputerMapper computerMapper;

    /**
     * Constructor of ComputerDao.
     * @param dataSource The datasource
     */
    public ComputerDao(DataSource dataSource) {

        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * Make persistent the given Computer.
     * @param computer the computer to persist
     * @return the id of entity
     */
    public Optional<Long> create(Computer computer) {

        Long id = null;
        String name = computer.getName();
        Date introduced = null;
        Date discontinued = null;
        Long companyId = null;

        try {

            try {
                introduced = Date.valueOf(computer.getIntroduced());
            } catch (NullPointerException e) {
                introduced = null;
            }

            try {
                discontinued = Date.valueOf(computer.getDiscontinued());
            } catch (NullPointerException e) {
                discontinued = null;
            }

            if (computer.getCompany() != null) {
                companyId = computer.getCompany().getId();
            } else {
                companyId = null;
            }

            jdbcTemplate.update(CREATE, computer.getName(), Date.valueOf(computer.getIntroduced()),
                    Date.valueOf(computer.getDiscontinued()), computer.getCompany().getId());

        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage());
            id = null;
        }

        return Optional.ofNullable(id);
    }

    /**
     * Update the given Computer into database.
     * @param computer the computer to update
     * @return true if was update
     */
    public boolean update(Computer computer) {

        int result = 0;

        try (Connection connection = datasource.getConnection()) {

            PreparedStatement st = connection.prepareStatement(UPDATE);
            st.setString(1, computer.getName());
            if (computer.getIntroduced() != null) {
                st.setDate(2, Date.valueOf(computer.getIntroduced()));
            } else {
                st.setDate(2, null);
            }
            if (computer.getDiscontinued() != null) {
                st.setDate(3, Date.valueOf(computer.getDiscontinued()));
            } else {
                st.setDate(3, null);
            }
            if (computer.getCompany() != null) {
                st.setLong(4, computer.getCompany().getId());
            } else {
                st.setObject(4, null);
            }
            st.setLong(5, computer.getId());

            result = st.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return false;
        }

        return (result == 1);
    }

    /**
     * Remove the Computer with the given id.
     * @param id the id of computer
     * @return true if the given computer was remove
     */
    public boolean removeById(Long id) {

        int result = 0;

        try (Connection connection = datasource.getConnection()) {

            PreparedStatement st = connection.prepareStatement(REMOVE);

            st.setLong(1, id);

            result = st.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return false;
        }

        return (result == 1);

    }

    /**
     * Remove the Computer with the given id.
     * @param id the id of computer
     * @param connection the connection to database
     * @return true if the given computer was remove
     */
    public boolean removeById(Long id, Connection connection) {

        int result = 0;

        try {

            PreparedStatement st = connection.prepareStatement(REMOVE);

            st.setLong(1, id);

            result = st.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return false;
        }

        return (result == 1);

    }

    /**
     * Retrieve a page of computers.
     * @param offset index of the first computer of the page
     * @throws IllegalArgumentException if offset is negative
     * @return Page of computers
     */
    public Page<Computer> getPage(int offset) throws IllegalArgumentException {

        List<Computer> computers = new ArrayList<Computer>();

        if (offset < 0) {
            throw new IllegalArgumentException();
        }

        try (Connection connection = datasource.getConnection()) {

            PreparedStatement st = connection.prepareStatement(GET_PAGE);
            st.setInt(1, Constante.LIMIT_PAGE);
            st.setInt(2, offset);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                computers.add(computerMapper.mapRow(rs, 2));
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

        return new Page<Computer>(computers);
    }

    /**
     * Retrieve a page of computers.
     * @param offset index of the first computer of the page
     * @param limit the number of computer for the page
     * @throws IllegalArgumentException if offset or limit is negative
     * @return Page of computers
     */
    public Page<Computer> getPage(int offset, int limit) throws IllegalArgumentException {

        List<Computer> computers = new ArrayList<Computer>();

        if (limit < 0 || offset < 0) {
            throw new IllegalArgumentException();
        }

        try (Connection connection = datasource.getConnection()) {

            PreparedStatement st = connection.prepareStatement(GET_PAGE);
            st.setInt(1, limit);
            st.setInt(2, offset);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                computers.add(computerMapper.map(rs));
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

        return new Page<Computer>(computers);
    }

    /**
     * Retrieve a page of computers.
     * @param offset index of the first computer of the page
     * @param limit the number of computer for the page
     * @param search the criteria
     * @throws IllegalArgumentException if offset or limit is negative
     * @return Page of computers
     */
    public Page<Computer> getPage(int offset, int limit, String search) throws IllegalArgumentException {
        List<Computer> computers = new ArrayList<Computer>();

        if (limit < 0 || offset < 0) {
            throw new IllegalArgumentException();
        }

        try (Connection connection = datasource.getConnection()) {

            PreparedStatement st = connection.prepareStatement(GET_PAGE_SEARCH);
            st.setString(1, "%" + search + "%");
            st.setString(2, "%" + search + "%");
            st.setInt(3, limit);
            st.setInt(4, offset);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                computers.add(computerMapper.map(rs));
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

        return new Page<Computer>(computers);
    }

    /**
     * Retrieve the computer with the given id.
     * @param id the id of computer
     * @return the computer
     */
    public Optional<Computer> findById(Long id) {

        Computer computer = null;

        try (Connection connection = datasource.getConnection()) {

            PreparedStatement st = connection.prepareStatement(FIND_BY_ID);

            st.setLong(1, id);

            ResultSet resultSet = st.executeQuery();

            if (resultSet.next()) {

                computer = computerMapper.map(resultSet);

            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return null;
        }

        return Optional.ofNullable(computer);
    }

    /**
     * Retrieve the number of computers.
     * @return the number of computers
     */
    public int count() {

        int count = 0;

        try (Connection connection = datasource.getConnection()) {

            Statement st = connection.createStatement();

            ResultSet resultSet = st.executeQuery(COUNT);

            if (resultSet.next()) {

                count = resultSet.getInt(1);

            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return 0;
        }

        return count;
    }

    /**
     * Retrieve the number of computers.
     * @param search the criteria
     * @return the number of computers
     */
    public int count(String search) {

        int count = 0;

        try (Connection connection = datasource.getConnection()) {

            PreparedStatement st = connection.prepareStatement(COUNT_PAGE_SEARCH);
            st.setString(1, "%" + search + "%");
            st.setString(2, "%" + search + "%");

            ResultSet resultSet = st.executeQuery();

            if (resultSet.next()) {

                count = resultSet.getInt(1);

            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return 0;
        }

        return count;
    }

    /**
     * Retrieve the computer with the given company id.
     * @param computerId the id of computer
     * @param offset index for pagination
     * @throws IllegalArgumentException if offset is negative
     * @return the computer
     */
    public Page<Computer> findByComputerId(Long computerId, int offset) throws IllegalArgumentException {

        List<Computer> computers = new ArrayList<Computer>();

        if (offset < 0) {
            throw new IllegalArgumentException();
        }

        try (Connection connection = datasource.getConnection()) {

            PreparedStatement st = connection.prepareStatement(FIND_BY_COMPANYID);
            st.setLong(1, computerId);
            st.setInt(2, Constante.LIMIT_PAGE);
            st.setInt(3, offset);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                computers.add(computerMapper.map(rs));
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

        return new Page<Computer>(computers);
    }

    /**
     * Retrieve the computer with the given company id.
     * @param computerId the id of computer
     * @param offset index for pagination
     * @param connection the connection
     * @throws IllegalArgumentException if offset is negative
     * @return the computer
     */
    public Page<Computer> findByComputerId(Long computerId, int offset, Connection connection)
            throws IllegalArgumentException {

        List<Computer> computers = new ArrayList<Computer>();

        if (offset < 0) {
            throw new IllegalArgumentException();
        }

        try {

            PreparedStatement st = connection.prepareStatement(FIND_BY_COMPANYID);
            st.setLong(1, computerId);
            st.setInt(2, Constante.LIMIT_PAGE);
            st.setInt(3, offset);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                computers.add(computerMapper.map(rs));
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
        }

        return new Page<Computer>(computers);
    }

    /**
     * Retrieve the number of computers.
     * @param companyId the computer id
     * @return the number of computers
     */
    public int countByComputerId(Long companyId) {

        int count = 0;

        try (Connection connection = datasource.getConnection()) {

            PreparedStatement st = connection.prepareStatement(COUNT_BY_COMPANYID);
            st.setLong(1, companyId);

            ResultSet resultSet = st.executeQuery();

            if (resultSet.next()) {

                count = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            return 0;
        }

        return count;
    }
}
