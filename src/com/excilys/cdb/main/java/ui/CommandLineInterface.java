/**
 * 
 */
package com.excilys.cdb.main.java.ui;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import com.excilys.cdb.main.java.controller.CliController;

/**
 * @author Aurelien Denoize
 *
 */
public class CommandLineInterface {


	CliController cliController = new CliController();
	Queue<String> args =  new LinkedList<String>();;


	public void run(String[] arguments) {

		for (String a : arguments) {
			args.add(a);
		}

		if(args.size() < 2) {
			printHelp();
			return;
		}

		String arg = args.poll();

		switch(arg) {
		case "-a":
		case "--add": addComputer(); break;
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

	/**
	 * Add new Computer.
	 */
	private void addComputer() {

		String arg = args.poll();

		if (!arg.contains("=")) {
			System.err.println("Bad parameter: " + arg);
			printHelp();
			return;
		}

		final String[] tab = arg.split("=");
		
		if(!tab[0].equals("name")) {
			System.err.println("Bad parameter: " + arg);
			printHelp();
			return;
		}
		
		
		Map<String, String> parameters = parseParameters();
		parameters.put(tab[0], tab[1]);
		
		System.out.println(cliController.addComputer(parameters));
		

	}
	
	/**
	 * Update a Computer
	 */
	private void updateComputer() {
		
		String arg = args.poll();

		int id = 0;

		try {
			id = Integer.parseInt(arg);
		}catch (NumberFormatException e) {
			System.err.println("Please enter an ID; (ex : 123)");
			printHelp();
			return;
		}
		
		
		Map<String, String> parameters = parseParameters();
		
		System.out.println(cliController.updateComputer(arg, parameters));
	}
	
	/**
	 * remove a computer
	 */
	private void removeComputer() {
		String arg = args.poll();

		int id = 0;

		try {
			id = Integer.parseInt(arg);
		}catch (NumberFormatException e) {
			System.err.println("Please enter an ID; (ex : 123)");
			printHelp();
			return;
		}
		
		System.out.println(cliController.removeComputer(arg));

	}


	private void list() {
		// TODO Auto-generated method stub

	}


	/**
	 * Show informations about the Computer with the given id
	 */
	private void showComputer() {
		String arg = args.poll();
		int id = 0;

		try {
			id = Integer.parseInt(arg);
		}catch (NumberFormatException e) {
			System.err.println("Please enter an ID; (ex : 123)");
			printHelp();
			return;
		}

		System.out.println(cliController.showComputer(arg));


	}



	private Map<String, String> parseParameters() {

		final Map<String, String> parameters = new HashMap<>();

		for (final String arg : args) {
			if (!arg.contains("=")) {
				System.err.println("Bad parameter: " + arg);
				printHelp();
				System.exit(2);
			}

			final String[] tab = arg.split("=");
			parameters.put(tab[0], tab[1]);
		}
		return parameters;
	}


	private void printHelp() {
		System.out.println("Help :");
		System.out.println("cdbCli --add name=[value] company=[value] [field=value]");
		System.out.println("cdbCli --update (id) [field=value]");
		System.out.println("cdbCli --remove (id)");
		System.out.println("cdbCli --show (id)");
		System.out.println("cbdCli --list --computer");
		System.out.println("cbdCli --list --company");	
	}

}
