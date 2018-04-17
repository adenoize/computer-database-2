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

	
	private static final String FIND_ALL = "SELECT * FROM company;";
	private static final String FIND_BY_ID     = "SELECT * FROM company WHERE id = ?";
	
	
	private JdbcTool jdbcTool;
	
	
	public CompanyDaoImpl() {
		
		jdbcTool = new JdbcTool();
		jdbcTool.init();
		
		
	}
	
	
	/* (non-Javadoc)
	 * @see com.excilys.cdb.main.java.dao.CompanyDao#findAll()
	 */
	@Override
	public List<Company> findAll(){
		
		Connection connection = null;
		List<Company> companies = new ArrayList<Company>();
		
		try{
	   
			connection = jdbcTool.newConnection();
	        Statement st = connection.createStatement();
	        ResultSet rs = st.executeQuery(FIND_ALL);

	        while (rs.next()) {
	        	Company company = new Company();
	        	company.setId(rs.getLong("id"));
	        	company.setName(rs.getString("name"));
	        	
	        	companies.add(company);
	           
	        }
	        
	    } catch (SQLException e) {
			e.printStackTrace();
		} finally {
	        // close connection
	        jdbcTool.close(connection);
	    }
		
		return companies;
	}

	/* (non-Javadoc)
	 * @see com.excilys.cdb.main.java.dao.CompanyDao#findById(java.lang.Long)
	 */
	@Override
	public Company findById(Long id){
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
	        
	    } catch (SQLException e) {
			e.printStackTrace();
		} finally {
	        // close connection
	        jdbcTool.close(connection);
	    }
		
		return company;
	}

}
