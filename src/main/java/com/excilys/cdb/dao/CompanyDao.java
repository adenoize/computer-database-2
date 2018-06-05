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

    
    private static final Logger LOGGER = LoggerFactory.getLogger(ComputerDao.class);

    private EntityManager em;

    /**
     * Constructor of CompanyDao.
     * @param dataSource The datasource
     * @param transactionManager The patformTransactionManager
     */
    public CompanyDao(EntityManager entityManager) {

        this.em = entityManager;
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
        
        final QComputer qComputer = QComputer.computer;
        final QCompany qCompany = QCompany.company;
        
        try {

            em.getTransaction().begin();
            JPAQueryFactory queryFactory = new JPAQueryFactory(em);
            
            queryFactory.delete(qComputer)
            .where(qComputer.company.id.eq(id))
            .execute();
            
            queryFactory.delete(qCompany)
            .where(qCompany.id.eq(id))
            .execute();
            em.getTransaction().commit();
            
        } catch (Exception e ) {
            LOGGER.warn(e.getMessage());
            em.getTransaction().rollback();
        }
        
       

    }

}
