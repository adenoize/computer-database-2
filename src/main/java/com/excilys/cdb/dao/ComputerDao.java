package main.java.com.excilys.cdb.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.TransactionRequiredException;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import main.java.com.excilys.cdb.constante.Constante;
import main.java.com.excilys.cdb.mapper.ComputerMapper;
import main.java.com.excilys.cdb.model.Company;
import main.java.com.excilys.cdb.model.Computer;
import main.java.com.excilys.cdb.model.Page;
import main.java.com.excilys.cdb.model.QCompany;
import main.java.com.excilys.cdb.model.QComputer;

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
    
    private EntityManager em;
    private JPAQueryFactory queryFactory;
    /**
     * Constructor of ComputerDao.
     * @param dataSource The datasource
     */
    public ComputerDao(DataSource dataSource, EntityManager entityManager) {

        jdbcTemplate = new JdbcTemplate(dataSource);
        em = entityManager;
        queryFactory = new JPAQueryFactory(em);
    }

    /**
     * Make persistent the given Computer.
     * @param computer the computer to persist
     * @return true if computer was saved
     */
    public boolean create(Computer computer) {

        try {

            em.getTransaction().begin();
            em.persist(computer);
            em.getTransaction().commit();
            
        } catch (Exception e ) {
            LOGGER.error(e.getMessage());
            em.getTransaction().rollback();
            return false;
        }
        return true;
    }

    /**
     * Update the given Computer into database.
     * @param computer the computer to update
     * @return true if was update
     */
    public boolean update(Computer computer) {

        try {

            Computer computerToUpdate = em.find(Computer.class, computer.getId());
            em.getTransaction().begin();
            computerToUpdate.setName(computer.getName());
            computerToUpdate.setIntroduced(computer.getIntroduced());
            computerToUpdate.setDiscontinued(computer.getDiscontinued());
            computerToUpdate.setCompany(computer.getCompany());
            em.getTransaction().commit();

        }  catch (Exception e ) {
            LOGGER.error(e.getMessage());
            em.getTransaction().rollback();
            return false;
        }

        return true;
    }

    /**
     * Remove the Computer with the given id.
     * @param id the id of computer
     * @return true if the given computer was remove
     */
    public boolean removeById(Long id) {

        int result = 0;
        QComputer qComputer = QComputer.computer;

        try {

            queryFactory.delete(qComputer).where(qComputer.id.eq(id)).execute();

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
        final JPAQuery<Computer> query = new JPAQuery<>(em);
        final QComputer qComputer = QComputer.computer;
        
        if (offset < 0) {
            throw new IllegalArgumentException();
        }

        try {

            

            computers = query.from(qComputer).limit(Constante.LIMIT_PAGE).offset(offset).fetch();
//            computers = jdbcTemplate.query(GET_PAGE, new Object[] {Constante.LIMIT_PAGE, offset }, computerMapper);

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
        final JPAQuery<Computer> query = new JPAQuery<>(em);
        final QComputer qComputer = QComputer.computer;
        
        if (limit < 0 || offset < 0) {
            throw new IllegalArgumentException();
        }

        try {

//          computers = jdbcTemplate.query(GET_PAGE, new Object[] {limit, offset }, computerMapper);
            computers = query.from(qComputer).limit(limit).offset(offset).fetch();
            
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
        final JPAQuery<Computer> query = new JPAQuery<>(em);
        final QComputer qComputer = QComputer.computer;
        
        if (limit < 0 || offset < 0) {
            throw new IllegalArgumentException();
        }

        try {

//            computers = jdbcTemplate.query(GET_PAGE_SEARCH, new Object[] {"%" + search + "%", "%" + search + "%", limit, offset }, computerMapper);
            computers = query.from(qComputer).where(qComputer.name.likeIgnoreCase("%" + search + "%").or(qComputer.company.name.likeIgnoreCase("%" + search + "%"))).limit(limit).offset(offset).fetch();
           
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
        final JPAQuery<Computer> query = new JPAQuery<>(em);
        final QComputer qComputer = QComputer.computer;
        
        try {

//            computer = jdbcTemplate.queryForObject(FIND_BY_ID, new Object[] {id }, computerMapper);
            
//            computer = query.from(qComputer).where();
            computer = query.from(qComputer).where(qComputer.id.eq(id)).fetchOne();

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
        final JPAQuery<Computer> query = new JPAQuery<>(em);
        final QComputer qComputer = QComputer.computer;

        try {

//            count = jdbcTemplate.queryForObject(COUNT, Integer.class);
            count = (int) query.from(qComputer).fetchCount();
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
        final JPAQuery<Computer> query = new JPAQuery<>(em);
        final QComputer qComputer = QComputer.computer;

        try {

//            count = jdbcTemplate.queryForObject(COUNT_PAGE_SEARCH,
//                    new Object[] {"%" + search + "%", "%" + search + "%" }, Integer.class);
            count = (int) query.from(qComputer).where(qComputer.name.likeIgnoreCase("%" + search + "%").or(qComputer.company.name.likeIgnoreCase("%" + search + "%"))).fetchCount();
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage());
            return 0;
        }

        return count;
    }

    /**
     * Retrieve the computers with the given company id.
     * @param companyId the id of computer
     * @param offset index for pagination
     * @throws IllegalArgumentException if offset is negative
     * @return the computer
     */
    public Page<Computer> findByComputerId(Long companyId, int offset) throws IllegalArgumentException {

        List<Computer> computers = new ArrayList<Computer>();
        final JPAQuery<Computer> query = new JPAQuery<>(em);
        final QComputer qComputer = QComputer.computer;

        
        if (offset < 0) {
            throw new IllegalArgumentException();
        }

        try {

//            computers = jdbcTemplate.query(FIND_BY_COMPANYID, new Object[] {companyId, Constante.LIMIT_PAGE, offset },
//                    computerMapper);
            computers = query.from(qComputer).where(qComputer.company.id.eq(companyId)).limit(Constante.LIMIT_PAGE).offset(offset).fetch();

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
    public int countByCompanyId(Long companyId) {

        int count = 0;
        final JPAQuery<Computer> query = new JPAQuery<>(em);
        final QComputer qComputer = QComputer.computer;

        try {

//            count = jdbcTemplate.queryForObject(COUNT_BY_COMPANYID, new Object[] {companyId }, Integer.class);
            count = (int) query.from(qComputer).where(qComputer.company.id.eq(companyId)).fetchCount();
                    
        } catch (DataAccessException e) {
            LOGGER.error(e.getMessage());
            return 0;
        }

        return count;
    }
}
