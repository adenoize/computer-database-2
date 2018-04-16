/**
 * 
 */
package com.excilys.cdb.main.java.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.main.java.dao.CompanyDao;
import com.excilys.cdb.main.java.dao.JdbcTool;
import com.excilys.cdb.main.java.model.Company;



/**
 * @author Aurelien Denoize
 *
 */
public class CompanyDaoImpl implements CompanyDao {

	
	private final String LIST_COMPANIES = "SELECT * FROM company;";
	private final String FIND_BY_ID     = "SELECT * FROM company WHERE id = ?";
	
	
	private JdbcTool jdbcTool;
	
	
	public CompanyDaoImpl() {
		
		jdbcTool = new JdbcTool();
		jdbcTool.init();
		
		
	}
	
	
	/* (non-Javadoc)
	 * @see com.excilys.cdb.main.java.dao.CompanyDao#getAll()
	 */
	@Override
	public List<Company> findAll() throws SQLException{
		
		Connection connection = null;
		List<Company> companies = new ArrayList<Company>();
		
		try{
	   
			connection = jdbcTool.newConnection();
	        Statement st = connection.createStatement();
	        ResultSet rs = st.executeQuery(LIST_COMPANIES);

	        while (rs.next()) {
	        	Company company = new Company();
	        	company.setId(rs.getLong("id"));
	        	company.setName(rs.getString("name"));
	        	
	        	companies.add(company);
	           
	        }
	        
	    } finally {
	        // close connection
	        jdbcTool.close(connection);
	    }
		
		return companies;
	}

	/* (non-Javadoc)
	 * @see com.excilys.cdb.main.java.dao.CompanyDao#getById(java.lang.Long)
	 */
	@Override
	public Company findById(Long id) throws SQLException {
		Connection connection = null;
		Company company = null;
		try{
	   
			connection = jdbcTool.newConnection();
	        PreparedStatement st = connection.prepareStatement(FIND_BY_ID);
	        st.setLong(1, id);
	        ResultSet rs = st.executeQuery();

	        while (rs.next()) {
	        	company = new Company();
	        	company.setId(rs.getLong("id"));
	        	company.setName(rs.getString("name"));
	        	       
	        }
	        
	    } finally {
	        // close connection
	        jdbcTool.close(connection);
	    }
		
		return company;
	}

}
