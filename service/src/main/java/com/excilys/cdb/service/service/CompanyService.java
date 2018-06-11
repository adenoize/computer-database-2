package com.excilys.cdb.service.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.excilys.cdb.binding.constante.Constante;
import com.excilys.cdb.core.model.Company;
import com.excilys.cdb.core.model.Page;
import com.excilys.cdb.persistence.dao.CompanyDao;
import com.excilys.cdb.service.exception.DatabaseException;

@Service
public class CompanyService {

    private CompanyDao companyDao;
    
    /**
     * @param companyDao
     */
    public CompanyService(CompanyDao companyDao) {
        super();
        this.companyDao = companyDao;
    }
    

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
     * @throws NoSuchElementException NoSuchElementException
     */
    public Company findById(Long id) throws NoSuchElementException {
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
