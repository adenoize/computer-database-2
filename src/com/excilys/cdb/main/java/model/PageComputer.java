/**
 * 
 */
package com.excilys.cdb.main.java.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Aurelien Denoize
 *
 */
public class PageComputer implements Page{



	private static final int PAGE_LIMIT = 20;
	private List<Computer> list;
	private int currentPage;
	private int totalPage;


	public PageComputer(List<Computer> listComputer) {


		list = listComputer;
		totalPage = list.size() / PAGE_LIMIT;
	}


	public List<Computer> getPage() {

	
		int fromIndex = PAGE_LIMIT * currentPage;
		int toIndex =  (PAGE_LIMIT * currentPage) + PAGE_LIMIT;

		return list.subList(fromIndex, toIndex);
	}

	public boolean hasNext() {

		return false;		
	}

	public List<Computer> next() {
		
		if(currentPage < totalPage) {
			currentPage++;
		}
		
		return getPage();

	}

	public List<Computer> previous() {
		
		if(currentPage > 1) {
			currentPage--;
		}
		
		return getPage();

	}

	public int getCurrentPage() {
		return currentPage;
	}


	public int getTotalPage() {
		return totalPage;
	}



}
