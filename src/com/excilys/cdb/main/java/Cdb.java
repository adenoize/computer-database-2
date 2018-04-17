/**
 * 
 */
package com.excilys.cdb.main.java;

import com.excilys.cdb.main.java.service.CompanyService;

/**
 * @author Aurelien Denoize
 *
 */
public class Cdb {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		CompanyService cs = new CompanyService();
		System.out.println(cs.findAll());
		System.out.println(cs.findById(44L));
		
//		ComputerService cs = new ComputerService();
//		System.out.println(cs.);

	}

}
