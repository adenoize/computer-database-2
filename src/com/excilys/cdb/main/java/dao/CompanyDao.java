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

import com.excilys.cdb.main.java.constante.Constante;
import com.excilys.cdb.main.java.mapper.CompanyMapper;
import com.excilys.cdb.main.java.model.Company;
import com.excilys.cdb.main.java.model.Page;

/**
 * 
 * @author aurel
 *
 */
public enum CompanyDao {
	INSTANCE;

	private static final String GET_PAGE = "SELECT * FROM company LIMIT ? OFFSET ?";
	private static final String FIND_BY_ID     = "SELECT * FROM company WHERE id = ?";


	private JdbcTool jdbcTool;


	private CompanyDao() {}

	/**
	 * retrieve a page of companies.
	 * @return
	 */
	public Page<Company> getPage(int offset){

		List<Company> companies = new ArrayList<Company>();

		try(Connection connection = JdbcTool.INSTANCE.newConnection()){
			
			PreparedStatement st = connection.prepareStatement(GET_PAGE);
			st.setInt(1, Constante.LIMIT_PAGE);
			st.setInt(2, offset);
			ResultSet rs = st.executeQuery();
			

			while (rs.next()) {
				companies.add(CompanyMapper.INSTANCE.map(rs));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return new Page<Company>(companies);
	}

	/**
	 * retrieve the company with the given id.
	 * @param id
	 * @return
	 */
	public Company findById(Long id){
		Company company = null;
		
		try(Connection connection = JdbcTool.INSTANCE.newConnection()){
			
		
			PreparedStatement st = connection.prepareStatement(FIND_BY_ID);
			st.setLong(1, id);
			ResultSet rs = st.executeQuery();

				company = CompanyMapper.INSTANCE.map(rs);
				

		} catch (SQLException e) {
			e.printStackTrace();
			company = null;
		}

		return company;
	}


}
