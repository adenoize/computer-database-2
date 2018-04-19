package com.excilys.cdb.main.java.service;

import java.util.List;

import com.excilys.cdb.main.java.dao.ComputerDao;
import com.excilys.cdb.main.java.model.Computer;

public class ComputerService {

	private ComputerDao computerDao = ComputerDao.INSTANCE;

	/**
	 * Save a Computer into Database.
	 * @param Computer
	 * @return
	 * @throws DatabaseException 
	 * @throws SaveException 
	 */
	public void save(Computer computer) throws DatabaseException  {

		// if save fail
		if(!computerDao.create(computer)) {
			throw new DatabaseException();
		}

	}

	/**
	 * Update the given Computer into database.
	 * @param Computer
	 * @return
	 * @throws UpdateException 
	 */
	public void update(Computer computer) throws DatabaseException {

		// if update fail
		if(!computerDao.update(computer)) {
			throw new DatabaseException();
		}
	}

	/**
	 * Remove the given Computer.
	 * @param Computer
	 * @throws RemoveException 
	 */
	public void remove(Computer computer) throws DatabaseException {
		// if remove fail
		if(!computerDao.remove(computer)) {
			throw new DatabaseException();
		}
	}

	/**
	 * Remove the Computer with the given id.
	 * @param id
	 */
	public void removeById(Long id)throws DatabaseException {
		// if remove fail
		if(!computerDao.removeById(id)) {
			throw new DatabaseException();
		}
	}
	/**
	 * retrieve all computers.
	 * @return
	 */
	public List<Computer> findAll(){
		return computerDao.findAll();
	}

	/**
	 * retrieve the computer with the given id.
	 * @param id
	 * @return
	 * @throws FindException 
	 */
	public Computer findById(Long id) throws DatabaseException {
		
		Computer computer = computerDao.findById(id);
		
		if(computer == null) {
			throw new DatabaseException();
		}
		
		return computerDao.findById(id);
	}

}
