package com.excilys.cdb.main.java.model;

import java.util.List;

public interface Page {
	
	/**
	 * Get the current page
	 * @return
	 */
	public List<?> getPage();
		

	/**
	 * Get the next page
	 * @return
	 */
	public List<?> next();

	/**
	 * Get the previous page
	 * @return
	 */
	public List<?> previous();

	/**
	 * Get the current page
	 * @return
	 */
	public int getCurrentPage();

	/**
	 * Return the number of pages
	 * @return
	 */
	public int getTotalPage();
	
	

}
