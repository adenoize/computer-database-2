/**
 * 
 */
package com.excilys.cdb.main.java;

import java.sql.Date;

import com.excilys.cdb.main.java.model.Computer;
import com.excilys.cdb.main.java.service.ComputerService;
import com.excilys.cdb.main.java.service.DatabaseException;
import com.excilys.cdb.main.java.ui.CommandLineInterface;

/**
 * @author Aurelien Denoize
 *
 */
public class Cdb {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		CommandLineInterface cli = new CommandLineInterface();
		
		cli.run(args);

	}

}
