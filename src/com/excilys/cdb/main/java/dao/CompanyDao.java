/**
 * 
 */
package com.excilys.cdb.main.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.main.java.mapper.CompanyMapper;
import com.excilys.cdb.main.java.model.Company;

/**
 * 
 * @author aurel
 *
 */
public enum CompanyDao {
	INSTANCE;

	private static final String FIND_ALL = "SELECT * FROM company;";
	private static final String FIND_BY_ID     = "SELECT * FROM company WHERE id = ?";


	private JdbcTool jdbcTool;


	private CompanyDao() {

		jdbcTool = new JdbcTool();
		jdbcTool.init();


	}

	/**
	 * retrieve all companies.
	 * @return
	 */
	public List<Company> findAll(){

		Connection connection = null;
		List<Company> companies = new ArrayList<Company>();

		try{

			connection = jdbcTool.newConnection();
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(FIND_ALL);
			

			while (rs.next()) {
				companies.add(CompanyMapper.INSTANCE.map(rs));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// close connection
			jdbcTool.close(connection);
		}

		return companies;
	}

	/**
	 * retrieve the company with the given id.
	 * @param id
	 * @return
	 */
	public Company findById(Long id){
		Connection connection = null;
		Company company = null;
		try{

			connection = jdbcTool.newConnection();
			PreparedStatement st = connection.prepareStatement(FIND_BY_ID);
			st.setLong(1, id);
			ResultSet rs = st.executeQuery();

				company = CompanyMapper.INSTANCE.map(rs);
				

		} catch (SQLException e) {
			e.printStackTrace();
			company = null;
		} finally {
			// close connection
			jdbcTool.close(connection);
		}

		return company;
	}


}
