package com.excilys.cdb.persistence.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.excilys.cdb.binding.constante.Constante;
import com.excilys.cdb.core.model.Company;
import com.excilys.cdb.core.model.Page;
import com.excilys.cdb.core.model.QCompany;
import com.querydsl.jpa.hibernate.HibernateDeleteClause;
import com.querydsl.jpa.hibernate.HibernateQuery;

/**
 * DAO about company.
 * @author aurel
 */
@Repository
public class CompanyDao {

    private static QCompany qCompany = QCompany.company;

    private SessionFactory sessionFactory;
    private ComputerDao computerDao;

    /**
     * Constructor of CompanyDao.
     * @param dataSource The datasource
     * @param transactionManager The patformTransactionManager
     */
    public CompanyDao(ComputerDao computerDao, SessionFactory sessionFactory) {
        this.computerDao = computerDao;
        this.sessionFactory = sessionFactory;
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

        try (Session session = sessionFactory.openSession()) {
            HibernateQuery<Company> query = new HibernateQuery<>(session);
            companies = query.from(qCompany).limit(Constante.LIMIT_PAGE).offset(offset).fetch();
        }

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

        try (Session session = sessionFactory.openSession()) {
            HibernateQuery<Company> query = new HibernateQuery<>(session);
            company = query.from(qCompany).where(qCompany.id.eq(id)).fetchOne();
        }

        return Optional.ofNullable(company);
    }

    /**
     * Retrieve all the companies.
     * @return the company
     */
    public List<Company> findAll() {

        try (Session session = sessionFactory.openSession()) {
            HibernateQuery<Company> query = new HibernateQuery<>(session);
            return query.from(qCompany).fetch();
        }

    }

    /**
     * Remove a company. Caution ! This method delete all computers of the company.
     * @param id the company id
     * @throws DatabaseException Database exception
     */
    public void removeById(Long id) {

        try (Session session = sessionFactory.openSession()) {

            computerDao.deleteByCompanyId(id);
            HibernateDeleteClause delete = new HibernateDeleteClause(session, qCompany);
            delete.where(qCompany.id.eq(id)).execute();

        }

    }

}
