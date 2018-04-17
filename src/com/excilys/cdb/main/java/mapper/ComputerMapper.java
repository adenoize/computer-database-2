/**
 * 
 */
package com.excilys.cdb.main.java.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.excilys.cdb.main.java.model.Computer;

/**
 * @author Aurelien Denoize
 *
 */
public enum ComputerMapper {
	INSTANCE;

	public Computer map(ResultSet resultSet) {
		Computer computer = new Computer();

		try {
			computer.setId(resultSet.getLong("id"));
			computer.setName(resultSet.getString("name"));
			computer.setIntroduced(resultSet.getDate("introduced"));
			computer.setDiscontinued(resultSet.getDate("discontinued"));
			computer.setCompany(resultSet.getLong("company_id"));

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return computer;
	}

}
