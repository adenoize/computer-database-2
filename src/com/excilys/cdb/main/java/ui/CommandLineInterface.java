/**
 * 
 */
package com.excilys.cdb.main.java.ui;

import java.util.HashMap;
import java.util.Map;

import com.excilys.cdb.main.java.service.ComputerService;

/**
 * @author Aurelien Denoize
 *
 */
public enum CommandLineInterface {
	INSTANCE;
	
	ComputerService computerService = new ComputerService();
	
	
	public void run(final String[] args) {
		
		if(args.length < 2) {
			printHelp();
			return;
		}
		
		String arg = args[0];
		
		switch(arg) {
			case "-a":
			case "--all": addComputer(); break;
			case "-u":
			case "--update": updateComputer(); break;
			case "-r":
			case "--remove": removeComputer(); break;
			case "-s":
			case "--show": showComputer(); break;
			case "-l":
			case "--list": list(); break;
			case "-h":
			case "--help": printHelp(); break;
			default: printHelp();
		}
		
		
	}
	
	
	private void list() {
		// TODO Auto-generated method stub
		
	}


	private void showComputer() {
		// TODO Auto-generated method stub
		
	}


	private void removeComputer() {
		// TODO Auto-generated method stub
		
	}


	private void updateComputer() {
		// TODO Auto-generated method stub
		
	}


	/**
	 * Add new Computer.
	 */
	public void addComputer() {
		
	}
	
	private Map<String, String> parseParameters(final String[] args) {

	    final Map<String, String> parameters = new HashMap<>();

	    for (final String arg : args) {
	        if (!arg.contains("=")) {
	            System.err.println("Bad parameter: " + arg);
	            printHelp();
	            System.exit(2);
	        }

	        final String[] tab = arg.substring(2).split("=");
	        parameters.put(tab[0], tab[1]);
	    }
	    return parameters;
	}


	private void printHelp() {
		System.out.println("Help :");
		System.out.println("cdbCli --add name=[value] [field=value]");
		System.out.println("cdbCli --update (id) [field=value]");
		System.out.println("cdbCli --remove (id)");
		System.out.println("cdbCli --show (id)");
		System.out.println("cbdCli --list --computer");
		System.out.println("cbdCli --list --company");	
	}

}
