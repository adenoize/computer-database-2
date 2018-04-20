/**
 * 
 */
package com.excilys.cdb.main.java.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.excilys.cdb.main.java.model.Computer;
import com.excilys.cdb.main.java.service.DatabaseException;

/**
 * @author Aurelien Denoize
 *
 */
public enum ComputerMapper {
	INSTANCE;

	public Computer map(ResultSet resultSet) throws SQLException {
		Computer computer = new Computer();


		try {
			
				computer.setId(resultSet.getLong("id"));
				computer.setName(resultSet.getString("name"));
				computer.setIntroduced( resultSet.getDate("introduced").toLocalDate());
				computer.setDiscontinued(resultSet.getDate("discontinued").toLocalDate());
				computer.setCompany(resultSet.getLong("company_id"));
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException();
		}


		return computer;
	}

}
