/**
 * 
 */
package main.java.com.excilys.cdb.model;

import java.util.List;

/**
 * @author Aurelien Denoize
 *
 */
public class Page<T> {


	private List<T> list;


	public Page(List<T> listCompany) {

		list = listCompany;
	}


	/**
	 * Get the page
	 * @param page the number of the page
	 * @return
	 */
	public List<T> getPage() {
		return list;
	}



	/**
	 * check if the list is empty
	 * @return
	 */
	public boolean isEmpty() {
		return list.isEmpty();
	}




}
