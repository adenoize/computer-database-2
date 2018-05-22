package main.java.com.excilys.cdb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.java.com.excilys.cdb.constante.Constante;
import main.java.com.excilys.cdb.dao.CompanyDao;
import main.java.com.excilys.cdb.exception.DatabaseException;
import main.java.com.excilys.cdb.model.Company;
import main.java.com.excilys.cdb.model.Page;

@Service
public class CompanyService {

    @Autowired
    private CompanyDao companyDao;

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
        return companyDao.findById(id).get();
    }

    /**
     * Retrieve all companies.
     * @return the company
     * @throws DatabaseException DatabaseException
     */
    public List<Company> findAll() {
        return companyDao.findAll();
    }

    /**
     * Remove the company with the given id.
     * @param id the company id
     * @throws DatabaseException Database Exception
     */
    public void removeById(Long id) throws DatabaseException {

        if (companyDao.findById(id).isPresent()) {
            companyDao.removeById(id);
        } else {
            throw new DatabaseException("Can't remove the company");
        }

    }
}
