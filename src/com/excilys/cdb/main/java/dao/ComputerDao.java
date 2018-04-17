/**
 * 
 */
package com.excilys.cdb.main.java.dao;

import java.util.List;

import com.excilys.cdb.main.java.model.Computer;

/**
 * 
 * 
 * @author Aurelien Denoize
 *
 */
public interface ComputerDao {
	
	/**
	 * Make persistent the given Computer.
	 * @param Computer
	 * @return
	 */
	public boolean create(Computer computer);
	
	/**
	 * Update the given Computer into database.
	 * @param Computer
	 * @return
	 */
	public boolean update(Computer computer);
	
	/**
	 * Remove the given Computer.
	 * @param Computer
	 */
	public boolean remove(Computer computer);
	
	/**
	 * Remove the Computer with the given id.
	 * @param id
	 */
	public boolean removeById(Long id);
	
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
