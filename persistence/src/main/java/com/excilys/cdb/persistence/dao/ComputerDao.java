package com.excilys.cdb.persistence.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.excilys.cdb.binding.constante.Constante;
import com.excilys.cdb.core.model.Company;
import com.excilys.cdb.core.model.Computer;
import com.excilys.cdb.core.model.Page;
import com.excilys.cdb.core.model.QCompany;
import com.excilys.cdb.core.model.QComputer;
import com.querydsl.jpa.hibernate.HibernateDeleteClause;
import com.querydsl.jpa.hibernate.HibernateQuery;
import com.querydsl.jpa.impl.JPAQuery;

/**
 * DAO about Computer.
 * @author Aurelien Denoize
 */
@Repository
public class ComputerDao {

    private static final QComputer qComputer = QComputer.computer;

    private static final Logger LOGGER = LoggerFactory.getLogger(ComputerDao.class);

    private SessionFactory sessionFactory;

    /**
     * Constructor of ComputerDao.
     * @param dataSource The datasource
     */
    public ComputerDao(SessionFactory sessionFactory) {

        this.sessionFactory = sessionFactory;
    }

    /**
     * Make persistent the given Computer.
     * @param computer the computer to persist
     * @return true if computer was saved
     */
    public boolean create(Computer computer) {

        try (Session session = sessionFactory.openSession()) {
            session.save(computer);
        }

        return computer.getId() != null;
    }

    /**
     * Update the given Computer into database.
     * @param computer the computer to update
     * @return true if was update
     */
    public boolean update(Computer computer) {

        try (Session session = sessionFactory.openSession()) {
            session.update(computer);
            session.flush();
        }
        return true;
    }

    /**
     * Remove the Computer with the given id.
     * @param id the id of computer
     * @return true if the given computer was remove
     */
    public boolean removeById(Long id) {

        try (Session session = sessionFactory.openSession()) {
            HibernateDeleteClause delete = new HibernateDeleteClause(session, qComputer);
            delete.where(qComputer.id.eq(id)).execute();
        }

        return true;
    }

    public void deleteByCompanyId(long id) {
        try (Session session = sessionFactory.openSession()) {
            HibernateDeleteClause delete = new HibernateDeleteClause(session, qComputer);
            delete.where(qComputer.company.id.eq(id)).execute();
        }
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

        try (Session session = sessionFactory.openSession()) {
            HibernateQuery<Computer> query = new HibernateQuery<>(session);
            computers = query.from(qComputer).offset(offset).limit(Constante.LIMIT_PAGE).fetch();
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
        try (Session session = sessionFactory.openSession()) {
            HibernateQuery<Computer> query = new HibernateQuery<>(session);
            computers = query.from(qComputer).offset(offset).limit(limit).fetch();
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

        try (Session session = sessionFactory.openSession()) {
            HibernateQuery<Computer> query = new HibernateQuery<>(session);

            computers = query.from(qComputer).where(qComputer.name.contains(search).or(QCompany.company.name.contains(search)))
                    .limit(limit).offset(offset).fetch();
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
        try (Session session = sessionFactory.openSession()) {
            HibernateQuery<Computer> query = new HibernateQuery<>(session);
        
            computer = query.from(qComputer).where(qComputer.id.eq(id)).fetchOne();
        }
        return Optional.ofNullable(computer);
    }

    /**
     * Retrieve the number of computers.
     * @return the number of computers
     */
    public int count() {

        try (Session session = sessionFactory.openSession()) {
            HibernateQuery<Computer> query = new HibernateQuery<>(session);
            return (int) query.from(qComputer).fetchCount();
        }
      
    }

    /**
     * Retrieve the number of computers.
     * @param search the criteria
     * @return the number of computers
     */
    public int count(String search) {

        try (Session session = sessionFactory.openSession()) {
            HibernateQuery<Computer> query = new HibernateQuery<>(session);
            
            return (int) query.from(qComputer).where(qComputer.name.contains(search).or(QCompany.company.name.contains(search)))
                    .fetchCount();
        }
    }

    /**
     * Retrieve the computers with the given company id.
     * @param companyId the id of computer
     * @param offset index for pagination
     * @throws IllegalArgumentException if offset is negative
     * @return the computer
     */
    public Page<Computer> findByComapnyId(Long companyId, int offset) throws IllegalArgumentException {

        List<Computer> computers = new ArrayList<Computer>();
  
        if (offset < 0) {
            throw new IllegalArgumentException();
        }

        try (Session session = sessionFactory.openSession()) {
            HibernateQuery<Computer> query = new HibernateQuery<>(session);
            
        computers = query.from(qComputer).where(qComputer.company.id.eq(companyId)).limit(Constante.LIMIT_PAGE)
                .offset(offset).fetch();
        }
        return new Page<Computer>(computers);
    }

    /**
     * Retrieve the number of computers.
     * @param companyId the computer id
     * @return the number of computers
     */
    public int countByCompanyId(Long companyId) {

        try (Session session = sessionFactory.openSession()) {
            HibernateQuery<Computer> query = new HibernateQuery<>(session);
            return (int) query.from(qComputer).where(qComputer.company.id.eq(companyId)).fetchCount();
        }
    }
}
