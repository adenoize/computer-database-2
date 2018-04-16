/**
 * 
 */
package com.excilys.cdb.main.java.dao;

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
	public Computer create(Computer computer);
	
	/**
	 * Update the given Computer into database.
	 * @param Computer
	 * @return
	 */
	public Computer update(Computer computer);
	
	/**
	 * Remove the given Computer.
	 * @param Computer
	 */
	public void remove(Computer computer);
	
	/**
	 * Remove the Computer with the given id.
	 * @param id
	 */
	public void removeById(Long id);

}
