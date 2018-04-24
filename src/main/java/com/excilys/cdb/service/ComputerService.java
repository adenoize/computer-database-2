package main.java.com.excilys.cdb.service;

import main.java.com.excilys.cdb.constante.Constante;
import main.java.com.excilys.cdb.dao.ComputerDao;
import main.java.com.excilys.cdb.model.Computer;
import main.java.com.excilys.cdb.model.Page;

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
        if (computerDao.update(computer).equals(-1L)) {
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

        return computerDao.findById(id);
    }

}
