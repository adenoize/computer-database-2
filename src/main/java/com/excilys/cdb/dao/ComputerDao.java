package main.java.com.excilys.cdb.dao;

import java.sql.Date;
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

import main.java.com.excilys.cdb.constante.Constante;
import main.java.com.excilys.cdb.mapper.ComputerMapper;
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
     * @return true if computer was saved
     */
    public boolean create(Computer computer) {

        int result = 0;        String name = computer.getName();
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

            result = jdbcTemplate.update(CREATE, name, introduced, discontinued, companyId);

        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage());
            return false;
        }

        return (result == 1);
    }

    /**
     * Update the given Computer into database.
     * @param computer the computer to update
     * @return true if was update
     */
    public boolean update(Computer computer) {

        int result = 0;
        Long id = null;
        String name = computer.getName();
        Date introduced = null;
        Date discontinued = null;
        Long companyId = null;

        try {

            if (computer.getId() != null) {
                id = computer.getId();
            } else {
                return false;
            }

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

            if (computer.getCompany() != null && computer.getCompany().getId() != null) {
                companyId = computer.getCompany().getId();
            } else {
                companyId = null;
            }

            result = jdbcTemplate.update(UPDATE, name, introduced, discontinued, companyId, id);

        } catch (DataAccessException e) {
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

        try {

            result = jdbcTemplate.update(REMOVE, id);

        } catch (DataAccessException e) {
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

        try {

            computers = jdbcTemplate.query(GET_PAGE, new Object[] {Constante.LIMIT_PAGE, offset }, computerMapper);

        } catch (DataAccessException e) {
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

        try {

            computers = jdbcTemplate.query(GET_PAGE, new Object[] {limit, offset }, computerMapper);

        } catch (DataAccessException e) {
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

        try {

            computers = jdbcTemplate.query(GET_PAGE_SEARCH,
                    new Object[] {"%" + search + "%", "%" + search + "%", limit, offset }, computerMapper);

        } catch (DataAccessException e) {
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

        try {

            computer = jdbcTemplate.queryForObject(FIND_BY_ID, new Object[] {id }, computerMapper);

        } catch (DataAccessException e) {
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

        try {

            count = jdbcTemplate.queryForObject(COUNT, Integer.class);

        } catch (DataAccessException e) {
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

        try {

            count = jdbcTemplate.queryForObject(COUNT_PAGE_SEARCH,
                    new Object[] {"%" + search + "%", "%" + search + "%" }, Integer.class);

        } catch (DataAccessException e) {
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

        try {

            computers = jdbcTemplate.query(FIND_BY_COMPANYID, new Object[] {computerId, Constante.LIMIT_PAGE, offset },
                    computerMapper);

        } catch (DataAccessException e) {
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

        try {

            count = jdbcTemplate.queryForObject(COUNT_BY_COMPANYID, new Object[] {companyId }, Integer.class);

        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage());
            return 0;
        }

        return count;
    }
}
