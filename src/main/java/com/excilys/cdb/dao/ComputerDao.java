/**
 * 
 */
package main.java.com.excilys.cdb.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.java.com.excilys.cdb.constante.Constante;
import main.java.com.excilys.cdb.mapper.ComputerMapper;
import main.java.com.excilys.cdb.model.Computer;
import main.java.com.excilys.cdb.model.Page;

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
	private static final String GET_PAGE = "SELECT * FROM computer LIMIT ? OFFSET ?";
	private static final String FIND_BY_ID = "SELECT * FROM computer WHERE id = ?";




	private ComputerDao() {

				
	}

	/**
	 * Make persistent the given Computer.
	 * @param Computer
	 * @return
	 */
	public boolean create(Computer computer) {


		boolean isCreated = true;

		try(Connection connection = JdbcTool.INSTANCE.newConnection()){

			// open connection
			;

			// prepare query
			PreparedStatement st = connection.prepareStatement(CREATE);
			st.setString(1, computer.getName());
			st.setDate(2, Date.valueOf(computer.getIntroduced()));
			st.setDate(3, Date.valueOf(computer.getDiscontinued()));
			st.setLong(4, computer.getCompany());

			st.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			isCreated = false;
		}
		
		return isCreated;
	}

	/**
	 * Update the given Computer into database.
	 * @param Computer
	 * @return
	 */
	public boolean update(Computer computer) {

		boolean isCreated = true;

		try(Connection connection = JdbcTool.INSTANCE.newConnection()){

			// prepare query
			PreparedStatement st = connection.prepareStatement(UPDATE);
			st.setString(1, computer.getName());
			st.setDate(2,  Date.valueOf(computer.getIntroduced()));
			st.setDate(3,  Date.valueOf(computer.getDiscontinued()));
			st.setLong(4, computer.getCompany());
			st.setLong(5, computer.getId());

			st.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			isCreated = false;
		}

		return isCreated;
	}

	/**
	 * Remove the given Computer.
	 * @param Computer
	 */
	public boolean remove(Computer computer) {
	
		boolean isCreated = true;

		try(Connection connection = JdbcTool.INSTANCE.newConnection()){

			// prepare query
			PreparedStatement st = connection.prepareStatement(REMOVE);

			st.setLong(1, computer.getId());

			st.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			isCreated = false;
		}

		return isCreated;

	}

	/**
	 * Remove the Computer with the given id.
	 * @param id
	 */
	public boolean removeById(Long id) {
	
		boolean isCreated = true;

		try(Connection connection = JdbcTool.INSTANCE.newConnection()){
			
			// prepare query
			PreparedStatement st = connection.prepareStatement(REMOVE);

			st.setLong(1, id);

			st.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			isCreated = false;
		}

		return isCreated;

	}

	/**
	 * retrieve a page of companies.
	 * @return
	 */
	public Page<Computer> getPage(int offset){

		List<Computer> computers = new ArrayList<Computer>();

		try(Connection connection = JdbcTool.INSTANCE.newConnection()){
			
			PreparedStatement st = connection.prepareStatement(GET_PAGE);
			st.setInt(1, Constante.LIMIT_PAGE);
			st.setInt(2, offset);
			ResultSet rs = st.executeQuery();
			

			while (rs.next()) {
				computers.add(ComputerMapper.INSTANCE.map(rs));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
 
		return new Page<Computer>(computers);
	}

	/**
	 * retrieve the computer with the given id.
	 * @param id
	 * @return
	 */
	public Computer findById(Long id) {

	
		Computer computer = null;

		try(Connection connection = JdbcTool.INSTANCE.newConnection()){
			
			PreparedStatement st = connection.prepareStatement(FIND_BY_ID);

			st.setLong(1, id);

			ResultSet resultSet = st.executeQuery();

			if (resultSet.next()) {

				computer = ComputerMapper.INSTANCE.map(resultSet);

			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return computer;
	}


}
