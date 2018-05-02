package main.java.com.excilys.cdb.service;

import main.java.com.excilys.cdb.constante.Constante;
import main.java.com.excilys.cdb.dao.ComputerDao;
import main.java.com.excilys.cdb.model.Computer;
import main.java.com.excilys.cdb.model.Page;

/**
 * @author Aurelien Denoize
 */
public class ComputerService {

    private ComputerDao computerDao = ComputerDao.INSTANCE;

    /**
     * Save a Computer into Database.
     * @param computer the computer to save
     * @throws DatabaseException Exception if computer was not save
     */
    public void save(Computer computer) throws DatabaseException {

        // if save fail
        if (computerDao.create(computer).equals(-1L)) {
            throw new DatabaseException();
        }
    }

    /**
     * Update the given Computer into database.
     * @param computer computer to update
     * @throws DatabaseException Exception if computer was not update
     */
    public void update(Computer computer) throws DatabaseException {

        // if update fail
        if (!computerDao.update(computer)) {
            throw new DatabaseException();
        }
    }

    /**
     * Remove the Computer with the given id.
     * @param id the id of the computer
     * @throws DatabaseException Exception if computer was not remove
     */
    public void removeById(Long id) throws DatabaseException {
        // if remove fail
        if (!computerDao.removeById(id)) {
            throw new DatabaseException();
        }
    }

    /**
     * Retrieve all computers.
     * @param page the number of the page
     * @return the page
     */
    public Page<Computer> getPage(int page) {

        int offset = Constante.LIMIT_PAGE * page;

        return computerDao.getPage(offset);
    }

    /**
     * Retrieve all computers.
     * @param page the number of the page
     * @param limit the number of item per page
     * @return the page
     */
    public Page<Computer> getPage(int page, int limit) {

        int offset = limit * (page - 1);

        return computerDao.getPage(offset, limit);
    }

    /**
     * Retrieve the computer with the given id.
     * @param id the id of the computer
     * @return the computer
     * @throws DatabaseException Exception if computer was not found
     */
    public Computer findById(Long id) throws DatabaseException {

        Computer computer = computerDao.findById(id);

        if (computer == null) {
            throw new DatabaseException();
        }

        return computer;
    }

    /**
     * Retrieve the number total of pages.
     * @param limit the limit of the computer per page
     * @return the number of pages
     */
    public int totalPages(int limit) {

        int totalPages = 0;
        int numberOfComputers = computerDao.count();

        if (limit != 0) {
            totalPages = numberOfComputers / limit;
        }

        if (numberOfComputers % limit != 0) {
            totalPages++;
        }

        return totalPages;
    }

    /**
     * Retrieve the number total of pages with search.
     * @param limit the limit of the computer per page
     * @param search the search
     * @return the number of pages
     */
    public int totalPages(int limit, String search) {

        int totalPages = 0;
        int numberOfComputers = computerDao.count(search);

        if (limit != 0) {
            totalPages = numberOfComputers / limit;
        }

        if (numberOfComputers % limit != 0) {
            totalPages++;
        }

        return totalPages;
    }

    /**
     * Retrieve all computers.
     * @param page the number of the page
     * @param limit the number of item per page
     * @param search the search
     * @return the page
     */
    public Page<Computer> getPageBySearch(int page, int limit, String search) {
        int offset = limit * (page - 1);

        return computerDao.getPage(offset, limit, search);
    }

    /**
     * Retrieve the number total of computers.
     * @return the number of computers
     */
    public int count() {
        return computerDao.count();
    }

    /**
     * Retrieve the number total of computers for the search.
     * @param search the criteria of the search
     * @return the number of computers for the search
     */
    public int count(String search) {

        int numberOfComputers = computerDao.count(search);

        return numberOfComputers;
    }

}
