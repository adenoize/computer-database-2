/**
 * 
 */
package com.excilys.cdb.main.java.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.excilys.cdb.main.java.model.Company;

/**
 * @author Aurelien Denoize
 *
 */
public enum CompanyMapper {
	INSTANCE;
	
	public Company map(ResultSet resultSet) {
		Company company = new Company();
		
		try {
			company.setId(resultSet.getLong("id"));
			company.setName(resultSet.getString("name"));		
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return company;
		
	}
	

}
