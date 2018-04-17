package com.excilys.cdb.main.java.service;

import java.util.List;

import com.excilys.cdb.main.java.dao.ComputerDao;
import com.excilys.cdb.main.java.dao.impl.ComputerDaoImpl;
import com.excilys.cdb.main.java.model.Computer;

public class ComputerService {

	private ComputerDao computerDao;

	public ComputerService() {
		computerDao = new ComputerDaoImpl();
	}

	/**
	 * Save a Computer into Database.
	 * @param Computer
	 * @return
	 * @throws SaveException 
	 */
	public void save(Computer computer) throws SaveException {

		// if save fail
		if(!computerDao.create(computer)) {
			throw new SaveException();
		}

	}

	/**
	 * Update the given Computer into database.
	 * @param Computer
	 * @return
	 * @throws UpdateException 
	 */
	public void update(Computer computer) throws UpdateException {

		// if update fail
		if(!computerDao.update(computer)) {
			throw new UpdateException();
		}
	}

	/**
	 * Remove the given Computer.
	 * @param Computer
	 * @throws RemoveException 
	 */
	public void remove(Computer computer) throws RemoveException {
		// if remove fail
		if(!computerDao.remove(computer)) {
			throw new RemoveException();
		}
	}

	/**
	 * Remove the Computer with the given id.
	 * @param id
	 */
	public void removeById(Long id)throws RemoveException {
		// if remove fail
		if(!computerDao.removeById(id)) {
			throw new RemoveException();
		}
	}
	/**
	 * retrieve all computers.
	 * @return
	 */
	public List<Computer> findAll();

	/**
	 * retrieve the computer with the given id.
	 * @param id
	 * @return
	 */
	public Computer findById(Long id);

}
