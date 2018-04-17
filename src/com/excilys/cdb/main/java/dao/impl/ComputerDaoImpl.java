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

import com.excilys.cdb.main.java.dao.ComputerDao;
import com.excilys.cdb.main.java.dao.JdbcTool;
import com.excilys.cdb.main.java.model.Computer;

/**
 * @author Aurelien Denoize
 *
 */
public class ComputerDaoImpl implements ComputerDao {
	
	

	private static final String CREATE = "INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (?,?,?,?);";
	private static final String UPDATE = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?";
	private static final String FIND_ALL = "SELECT * FROM computer";
	private static final String FIND_BY_ID = "SELECT * FROM computer WHERE id = ?";
	
	private JdbcTool jdbcTool;
	
	
	public ComputerDaoImpl() {
		
		jdbcTool = new JdbcTool();
		jdbcTool.init();		
	}
	

	/* (non-Javadoc)
	 * @see com.excilys.cdb.main.java.dao.ComputerDao#create(com.excilys.cdb.main.java.model.Computer)
	 */
	@Override
	public boolean create(Computer computer) {
		Connection connection = null;
		
		boolean isCreated = true;
		
		try{
	   
			// open connection
			connection = jdbcTool.newConnection();
			
			// prepare query
	        PreparedStatement st = connection.prepareStatement(CREATE);
	        st.setString(1, computer.getName());
	        st.setDate(2, computer.getIntroduced());
	        st.setDate(3, computer.getDiscontinued());
	        st.setLong(4, computer.getCompany());
	        st.setLong(5, computer.getId());
	        
	        st.executeUpdate();
       
	    } catch (SQLException e) {
			e.printStackTrace();
			isCreated = false;
		} finally {
	        // close connection
	        jdbcTool.close(connection);
	    }
		
		return isCreated;
	}

	/* (non-Javadoc)
	 * @see com.excilys.cdb.main.java.dao.ComputerDao#update(com.excilys.cdb.main.java.model.Computer)
	 */
	@Override
	public boolean update(Computer computer) {
		Connection connection = null;
		boolean isCreated = true;
		
		try{
	   
			// open connection
			connection = jdbcTool.newConnection();
			
			// prepare query
	        PreparedStatement st = connection.prepareStatement(UPDATE);
	        st.setString(1, computer.getName());
	        st.setDate(2, computer.getIntroduced());
	        st.setDate(3, computer.getDiscontinued());
	        st.setLong(4, computer.getCompany());
	        
	        st.executeUpdate();
       
	    } catch (SQLException e) {
			e.printStackTrace();
			isCreated = false;
		} finally {
	        // close connection
	        jdbcTool.close(connection);
	    }
		
		return isCreated;
	}

	/* (non-Javadoc)
	 * @see com.excilys.cdb.main.java.dao.ComputerDao#remove(com.excilys.cdb.main.java.model.Computer)
	 */
	@Override
	public boolean remove(Computer computer) {
		Connection connection = null;
		boolean isCreated = true;
		
		try{
	   
			// open connection
			connection = jdbcTool.newConnection();
			
			// prepare query
	        PreparedStatement st = connection.prepareStatement(UPDATE);
	       
	        st.setLong(1, computer.getId());
	        
	        st.executeUpdate();
       
	    } catch (SQLException e) {
			e.printStackTrace();
			isCreated = false;
		} finally {
	        // close connection
	        jdbcTool.close(connection);
	    }
		
		return isCreated;

	}

	/* (non-Javadoc)
	 * @see com.excilys.cdb.main.java.dao.ComputerDao#removeById(java.lang.Long)
	 */
	@Override
	public boolean removeById(Long id) {
		Connection connection = null;
		boolean isCreated = true;
		
		try{
	   
			// open connection
			connection = jdbcTool.newConnection();
			
			// prepare query
	        PreparedStatement st = connection.prepareStatement(UPDATE);
	       
	        st.setLong(1, id);
	        
	        st.executeUpdate();
       
	    } catch (SQLException e) {
			e.printStackTrace();
			isCreated = false;
		} finally {
	        // close connection
	        jdbcTool.close(connection);
	    }
		
		return isCreated;

	}


	/* (non-Javadoc)
	 * @see com.excilys.cdb.main.java.dao.CompanyDao#findAll()
	 */
	@Override
	public List<Computer> findAll() {

		Connection connection = null;
		List<Computer> computers = new ArrayList<Computer>();
		
		try{
	   
			connection = jdbcTool.newConnection();
	        Statement st = connection.createStatement();
	        ResultSet rs = st.executeQuery(FIND_ALL);

	        while (rs.next()) {
	        	Computer computer = new Computer();
	        	computer.setId(rs.getLong("id"));
	        	computer.setName(rs.getString("name"));
	        	computer.setIntroduced(rs.getDate("introduced"));
	        	computer.setDiscontinued(rs.getDate("discontinued"));
	        	computer.setCompany(rs.getLong("company_id"));
	        	
	        	computers.add(computer);
	           
	        }
	        
	    } catch (SQLException e) {
			e.printStackTrace();
		} finally {
	        // close connection
	        jdbcTool.close(connection);
	    }
		
		return computers;
		
	}


	/* (non-Javadoc)
	 * @see com.excilys.cdb.main.java.dao.ComputerDao#findById(java.lang.Long)
	 */
	@Override
	public Computer findById(Long id) {

		Connection connection = null;
		Computer computer = null;
		
		try{
	   
			connection = jdbcTool.newConnection();
	        PreparedStatement st = connection.prepareStatement(FIND_BY_ID);
	        
	        st.setLong(1, id);

	        ResultSet rs = st.executeQuery();
	        
	        while (rs.next()) {
	        	computer = new Computer();
	        	computer.setId(rs.getLong("id"));
	        	computer.setName(rs.getString("name"));
	        	computer.setIntroduced(rs.getDate("introduced"));
	        	computer.setDiscontinued(rs.getDate("discontinued"));
	        	computer.setCompany(rs.getLong("company_id"));
	        }
	        
	    } catch (SQLException e) {
			e.printStackTrace();
			computer = null;
		} finally {
	        // close connection
	        jdbcTool.close(connection);
	    }
		
		return computer;
	}
	
	

}
