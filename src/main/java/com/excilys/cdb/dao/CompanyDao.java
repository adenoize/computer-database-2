package main.java.com.excilys.cdb.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import main.java.com.excilys.cdb.constante.Constante;
import main.java.com.excilys.cdb.exception.DatabaseException;
import main.java.com.excilys.cdb.mapper.CompanyMapper;
import main.java.com.excilys.cdb.model.Company;
import main.java.com.excilys.cdb.model.Page;
import main.java.com.excilys.cdb.model.QCompany;
import main.java.com.excilys.cdb.model.QComputer;

/**
 * DAO about company.
 * @author aurel
 */
@Repository
public class CompanyDao {

//    private JdbcTemplate jdbcTemplate;
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyDao.class);
//
//    private static final String FIND_ALL = "SELECT id, name FROM company";
//    private static final String GET_PAGE = "SELECT id, name FROM company LIMIT ? OFFSET ?";
//    private static final String FIND_BY_ID = "SELECT id, name FROM company WHERE id = ?";
//    private static final String REMOVE_BY_ID = "DELETE FROM company where id = ?";
//    private static final String REMOVE_COMPUTER_BY_COMPANY = "DELETE FROM computer WHERE company_id = ?";
//
//    private PlatformTransactionManager transactionManager;
//
//    @Autowired
//    private CompanyMapper companyMapper;

    EntityManager em;
    JPAQueryFactory queryFactory = new JPAQueryFactory(em);

    /**
     * Constructor of CompanyDao.
     * @param dataSource The datasource
     * @param transactionManager The patformTransactionManager
     */
    public CompanyDao(DataSource dataSource, PlatformTransactionManager transactionManager,
            EntityManager entityManager) {

//        this.transactionManager = transactionManager;
        this.em = entityManager;
//        jdbcTemplate = new JdbcTemplate(dataSource);
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
        //
        // try {
        //
        // companies = jdbcTemplate.query(GET_PAGE, new Object[] {Constante.LIMIT_PAGE,
        // offset }, companyMapper);
        //
        // } catch (DataAccessException e) {
        // LOGGER.error(e.getMessage());
        // }

        final JPAQuery<Company> query = new JPAQuery<>(em);
        final QCompany qCompany = QCompany.company;

        companies = query.from(qCompany).limit(Constante.LIMIT_PAGE).offset(offset).fetch();
        return new Page<Company>(companies);
    }

    /**
     * Retrieve the company with the given id.
     * @param id the id of company
     * @return the company
     * @throws NoSuchElementException if id not found
     */
    public Optional<Company> findById(Long id) throws NoSuchElementException {
        Company company = null;

        // try {
        // company = jdbcTemplate.queryForObject(FIND_BY_ID, new Object[] {id },
        // companyMapper);
        // } catch (EmptyResultDataAccessException e) {
        // return Optional.empty();
        // } catch (DataAccessException e) {
        // LOGGER.warn(e.getMessage());
        // return Optional.empty();
        // }
        final JPAQuery<Company> query = new JPAQuery<>(em);
        final QCompany qCompany = QCompany.company;

        company = query.from(qCompany).where(qCompany.id.eq(id)).fetchOne();
        
        return Optional.ofNullable(company);
    }

    /**
     * Retrieve all the companies.
     * @return the company
     */
    public List<Company> findAll() {
        // List<Company> companies = new ArrayList<>();
        //
        // try {
        //
        // companies = jdbcTemplate.query(FIND_ALL, companyMapper);
        //
        // } catch (DataAccessException e) {
        // LOGGER.error(e.getMessage());
        // }
        //
        // return companies;

        final JPAQuery<Company> query = new JPAQuery<>(em);
        final QCompany company = QCompany.company;

        return query.from(company).fetch();

    }

    /**
     * Remove a company. Caution ! This method delete all computers of the company.
     * @param id the company id
     * @throws DatabaseException Database exception
     */
    public void removeById(Long id) throws DatabaseException {

        // TransactionDefinition def = new DefaultTransactionDefinition();
        // TransactionStatus status = transactionManager.getTransaction(def);
        //
        // try {
        //
        // jdbcTemplate.update(REMOVE_COMPUTER_BY_COMPANY, id);
        // jdbcTemplate.update(REMOVE_BY_ID, id);
        // transactionManager.commit(status);
        // } catch (DataAccessException e) {
        // transactionManager.rollback(status);
        // }

        Query queryComputer = em.createQuery("DELETE FROM Computer c WHERE c.company_id = :id");
        queryComputer.setParameter("id", id).executeUpdate();

        Query queryCompany = em.createQuery("DELETE FROM Company c WHERE c.id = :id");
        queryCompany.setParameter("id", id).executeUpdate();

    }

}
