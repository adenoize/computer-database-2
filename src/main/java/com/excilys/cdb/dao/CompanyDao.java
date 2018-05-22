package main.java.com.excilys.cdb.dao;

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
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import main.java.com.excilys.cdb.constante.Constante;
import main.java.com.excilys.cdb.exception.DatabaseException;
import main.java.com.excilys.cdb.mapper.CompanyMapper;
import main.java.com.excilys.cdb.model.Company;
import main.java.com.excilys.cdb.model.Page;

/**
 * DAO about company.
 * @author aurel
 */
@Repository
public class CompanyDao {

    // @Autowired
    private DataSource dataSource;

    private JdbcTemplate jdbcTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyDao.class);

    private static final String FIND_ALL = "SELECT id, name FROM company";
    private static final String GET_PAGE = "SELECT id, name FROM company LIMIT ? OFFSET ?";
    private static final String FIND_BY_ID = "SELECT id, name FROM company WHERE id = ?";
    private static final String REMOVE_BY_ID = "DELETE FROM company where id = ?";
    private static final String REMOVE_COMPUTER_BY_COMPANY = "DELETE FROM computer WHERE company_id = ?";

    // private TransactionTemplate transactionTemplate;

    private PlatformTransactionManager transactionManager;

    /**
     * Constructor of CompanyDao.
     * @param dataSource The datasource
     * @param transactionManager The patformTransactionManager
     */
    @Autowired
    public CompanyDao(DataSource dataSource, PlatformTransactionManager transactionManager) {

        this.dataSource = dataSource;
        this.transactionManager = transactionManager;
        jdbcTemplate = new JdbcTemplate(dataSource);

    }

    /**
     * Retrieve a page of companies.
     * @param offset index of the first company of the page
     * @return the page of company
     */
    public Page<Company> getPage(int offset) {

        if (offset < 0) {
            offset = 0;
        }

        List<Company> companies = new ArrayList<>();

        try {

            companies = jdbcTemplate.query(GET_PAGE, new Object[] {Constante.LIMIT_PAGE, offset },
                    new CompanyMapper());

        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage());
        }

        return new Page<Company>(companies);
    }

    /**
     * Retrieve the company with the given id.
     * @param id the id of company
     * @return the company
     * @throws DatabaseException if id not found
     */
    public Optional<Company> findById(Long id) {
        Company company = null;
        try {

            company = jdbcTemplate.queryForObject(FIND_BY_ID, new Object[] {id }, new CompanyMapper());

        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage());
        }

        return Optional.ofNullable(company);
    }

    /**
     * Retrieve all the companies.
     * @return the company
     */
    public List<Company> findAll() {
        List<Company> companies = new ArrayList<>();

        try {

            companies = jdbcTemplate.query(FIND_ALL, new CompanyMapper());

        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage());
        }

        return companies;
    }

    /**
     * Remove a company. Caution ! This method delete all computers of the company.
     * @param id the company id
     * @throws DatabaseException Database exception
     */
    public void removeById(Long id) throws DatabaseException {

        TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(def);

        try {

            jdbcTemplate.update(REMOVE_COMPUTER_BY_COMPANY, id);
            jdbcTemplate.update(REMOVE_BY_ID, id);
            transactionManager.commit(status);
        } catch (DataAccessException e) {
            transactionManager.rollback(status);
        }
    }

}
