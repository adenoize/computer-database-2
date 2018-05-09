package main.java.com.excilys.cdb.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import main.java.com.excilys.cdb.constante.Constante;
import main.java.com.excilys.cdb.dao.CompanyDao;
import main.java.com.excilys.cdb.exception.DatabaseException;
import main.java.com.excilys.cdb.model.Company;
import main.java.com.excilys.cdb.model.Page;

public class CompanyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CompanyService.class);
    private CompanyDao companyDao = CompanyDao.INSTANCE;

    /**
     * Retrieve all companies.
     * @param page number of the page
     * @return the page
     */
    public Page<Company> getPage(int page) {

        int offset = Constante.LIMIT_PAGE * page;

        return companyDao.getPage(offset);
    }

    /**
     * Retrieve the company with the given id.
     * @param id the id of the company
     * @return the company
     * @throws DatabaseException DatabaseException
     */
    public Company findById(Long id) throws DatabaseException {
        Optional<Company> company = companyDao.findById(id);

        if (!company.isPresent()) {
            throw new DatabaseException();
        }

        return company.get();
    }

    /**
     * Retrieve the company with the given id.
     * @return the company
     * @throws DatabaseException DatabaseException
     */
    public List<Company> findAll() {
        List<Company> companies = companyDao.findAll();

        return companies;
    }

    /**
     * Remove the company with the given id.
     * @param id the company id
     * @throws DatabaseException Database Exception
     */
    public void removeById(Long id) throws DatabaseException {

        companyDao.removeById(id);

    }

}
