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

import com.excilys.cdb.main.java.mapper.ComputerMapper;
import com.excilys.cdb.main.java.model.Computer;

/**
 * 
 * 
 * @author Aurelien Denoize
 *
 */
public enum ComputerDao {
	INSTANCE;

	private static final String CREATE = "INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (?,?,?,?);";
	private static final String UPDATE = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?";
	private static final String REMOVE = "DELETE FROM computer where id = ?";
	private static final String FIND_ALL = "SELECT * FROM computer";
	private static final String FIND_BY_ID = "SELECT * FROM computer WHERE id = ?";

	private JdbcTool jdbcTool;


	private ComputerDao() {

		jdbcTool = new JdbcTool();
		jdbcTool.init();		
	}

	/**
	 * Make persistent the given Computer.
	 * @param Computer
	 * @return
	 */
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

	/**
	 * Update the given Computer into database.
	 * @param Computer
	 * @return
	 */
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

	/**
	 * Remove the given Computer.
	 * @param Computer
	 */
	public boolean remove(Computer computer) {
		Connection connection = null;
		boolean isCreated = true;

		try{

			// open connection
			connection = jdbcTool.newConnection();

			// prepare query
			PreparedStatement st = connection.prepareStatement(REMOVE);

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

	/**
	 * Remove the Computer with the given id.
	 * @param id
	 */
	public boolean removeById(Long id) {
		Connection connection = null;
		boolean isCreated = true;

		try{

			// open connection
			connection = jdbcTool.newConnection();

			// prepare query
			PreparedStatement st = connection.prepareStatement(REMOVE);

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

	/**
	 * retrieve all computers.
	 * @return
	 */
	public List<Computer> findAll() {

		Connection connection = null;
		List<Computer> computers = new ArrayList<Computer>();

		try{

			connection = jdbcTool.newConnection();
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(FIND_ALL);

			while (rs.next()) {
				computers.add(ComputerMapper.INSTANCE.map(rs));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// close connection
			jdbcTool.close(connection);
		}

		return computers;

	}

	/**
	 * retrieve the computer with the given id.
	 * @param id
	 * @return
	 */
	public Computer findById(Long id) {

		Connection connection = null;
		Computer computer = null;

		try{

			connection = jdbcTool.newConnection();
			PreparedStatement st = connection.prepareStatement(FIND_BY_ID);

			st.setLong(1, id);

			ResultSet resultSet = st.executeQuery();

			if (resultSet.next()) {

				computer = ComputerMapper.INSTANCE.map(resultSet);

			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			// close connection
			jdbcTool.close(connection);
		}

		return computer;
	}


}
