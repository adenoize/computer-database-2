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
public class PageCompany implements Page{



	private static final int PAGE_LIMIT = 20;
	private List<Company> list;
	private int currentPage;
	private int totalPage;

	public PageCompany(List<Company> listCompany) {


		list = listCompany;
		currentPage = 1;
		totalPage = (list.size() / PAGE_LIMIT);

	}


	public List<Company> getPage() {

	
		int fromIndex = PAGE_LIMIT * currentPage;
		int toIndex =  (PAGE_LIMIT * currentPage) + PAGE_LIMIT;
		
		if(toIndex > list.size()) toIndex = list.size() - 1;

		return list.subList(fromIndex, toIndex);
	}

	public boolean hasNext() {

		return false;		
	}

	public List<Company> next() {
		
		if(currentPage < totalPage) {
			currentPage++;
		}
		
		return getPage();

	}

	public List<Company> previous() {
		
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
