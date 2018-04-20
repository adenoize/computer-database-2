/**
 * 
 */
package com.excilys.cdb.main.java.controller;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import com.excilys.cdb.main.java.model.Company;
import com.excilys.cdb.main.java.model.Computer;
import com.excilys.cdb.main.java.model.Page;
import com.excilys.cdb.main.java.service.CompanyService;
import com.excilys.cdb.main.java.service.ComputerService;
import com.excilys.cdb.main.java.service.DatabaseException;

/**
 * @author Aurelien Denoize
 *
 */
public class CliController {


	ComputerService computerService = new ComputerService();
	CompanyService companyService = new CompanyService();


	/**
	 * Add new Computer.
	 */
	public String addComputer(Map<String, String> args) {

		Computer computer = new Computer();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		for(Map.Entry<String, String> entry : args.entrySet()) {
			String key = entry.getKey();
			final String value = entry.getValue();

			switch (key) {
			case "name": computer.setName(value); 

			break;
			case "introduced": 
			{
				try {
					java.util.Date date = sdf.parse(value);
					Date introduced = new Date(date.getTime());
					computer.setIntroduced(introduced);
				}catch (ParseException e) {
					return "Error on date";
				}

			}
			break;
			case "discontinued": 

			{
				try {
					java.util.Date date = sdf.parse(value);
					Date discontinued = new Date(date.getTime());
					computer.setDiscontinued(discontinued);

				}catch (ParseException e) {
					return "Error on date";
				}
			}
			break;
			case "company": 
			{
				try {
					Long company_id = Long.parseLong(value);

					computer.setCompany(company_id);

				}catch (NumberFormatException  e) {
					return "Error on company id";
				}
			}
			break;
			default:
				break;
			}
		}

		try {
			computerService.save(computer);
		} catch (DatabaseException e) {
			return "An error occurred !";
		}

		//TODO
		return computer.toString();

	}



	/**
	 * Update a Computer.
	 */
	public String updateComputer(String id, Map<String, String> args) {


		Computer computer = new Computer();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		try {
			Long computerId = new Long(id);
			computer.setId(computerId);
		}catch (NumberFormatException e) {
			return "Computer not found";
		}

		for(Map.Entry<String, String> entry : args.entrySet()) {
			String key = entry.getKey();
			final String value = entry.getValue();

			switch (key) {
			case "name": computer.setName(value); 

			break;
			case "introduced": 
			{
				try {
					java.util.Date date = sdf.parse(value);
					Date introduced = new Date(date.getTime());
					computer.setIntroduced(introduced);
				}catch (ParseException e) {
					return "Error on date";
				}

			}
			break;
			case "discontinued": 

			{
				try {
					java.util.Date date = sdf.parse(value);
					Date discontinued = new Date(date.getTime());
					computer.setDiscontinued(discontinued);

				}catch (ParseException e) {
					return "Error on date";
				}
			}
			break;
			case "company": 
			{

				try {
					Long company_id = Long.parseLong(value);

					computer.setCompany(company_id);

				}catch (NumberFormatException  e) {
					return "Error on company id";
				}
			}

			break;
			default:
				break;
			}
		}

		try {
			computerService.update(computer);
		} catch (DatabaseException e) {
			return "An error occurred !";
		}

		//TODO
		return computer.toString();

	}


	public String removeComputer(String id) {
		
		Long computerId = null;
		
		try {
			computerId = new Long(id);
			computerService.removeById(computerId);
		}catch (NumberFormatException e) {
			return "Computer not found";
		} catch (DatabaseException e) {
			return "An error occurred !";
		}
		
		
		return "Computer is remove";
	}
	
	public Page<Company> listCompany(int page) {
		
		return companyService.getPage(page);
	}
	
	public Page<Computer> listComputer(int page) {
	
		return computerService.getPage(page);
	}
	
	


	/**
	 * Show the Computer with the given id
	 */
	public String showComputer(String id) {

		//TODO
		Long computerId = new Long(id);
		String result = "";

		try {
			Computer computer = computerService.findById(computerId);
			//TODO
			result = computer.toString();

		} catch (DatabaseException e) {
			result = "Computer not found !";
		}

		return result;
	}
	
	public Company getCompany(Long id) {
		return companyService.findById(id);
	}


}
