package main.java.com.excilys.cdb.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import main.java.com.excilys.cdb.constante.Constante;
import main.java.com.excilys.cdb.mapper.ComputerMapper;
import main.java.com.excilys.cdb.model.Computer;
import main.java.com.excilys.cdb.model.Page;

/**
 * DAO about Computer.
 * @author Aurelien Denoize
 */
public enum ComputerDao {
    INSTANCE;

    private static final String CREATE = "INSERT INTO computer (name, introduced, discontinued, company_id) VALUES (?,?,?,?);";
    private static final String UPDATE = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?";
    private static final String REMOVE = "DELETE FROM computer where id = ?";
    private static final String GET_PAGE = "SELECT * FROM computer LIMIT ? OFFSET ?";
    private static final String FIND_BY_ID = "SELECT * FROM computer WHERE id = ?";
    private static final String COUNT = "SELECT count(id) FROM computer";
    private static final String COUNT_PAGE_SEARCH = "SELECT count(id) FROM computer WHERE name LIKE ?";
    private static final String GET_PAGE_SEARCH = "SELECT * FROM computer WHERE name LIKE ? LIMIT ? OFFSET ?";

    /**
     * Make persistent the given Computer.
     * @param computer the computer to persist
     * @return the id of entity
     */
    public Long create(Computer computer) {

        Long id = -1L;

        try (Connection connection = JdbcTool.INSTANCE.newConnection()) {

            PreparedStatement st = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, computer.getName());

            if (computer.getIntroduced() != null) {
                st.setDate(2, Date.valueOf(computer.getIntroduced()));
            } else {
                st.setDate(2, null);
            }
            if (computer.getDiscontinued() != null) {
                st.setDate(3, Date.valueOf(computer.getDiscontinued()));
            } else {
                st.setDate(3, null);
            }
            if (computer.getCompany() != null) {
                st.setLong(4, computer.getCompany());
            } else {
                st.setObject(4, null);
            }

            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();

            if (rs.next()) {
                id = new Long(rs.getInt(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            id = -1L;
        }

        return id;
    }

    /**
     * Update the given Computer into database.
     * @param computer the computer to update
     * @return true if was update
     */
    public boolean update(Computer computer) {

        int result = 0;

        try (Connection connection = JdbcTool.INSTANCE.newConnection()) {

            // prepare query
            PreparedStatement st = connection.prepareStatement(UPDATE);
            st.setString(1, computer.getName());
            if (computer.getIntroduced() != null) {
                st.setDate(2, Date.valueOf(computer.getIntroduced()));
            } else {
                st.setDate(2, null);
            }
            if (computer.getDiscontinued() != null) {
                st.setDate(3, Date.valueOf(computer.getDiscontinued()));
            } else {
                st.setDate(3, null);
            }
            if (computer.getCompany() != null) {
                st.setLong(4, computer.getCompany());
            } else {
                st.setObject(4, null);
            }
            st.setLong(5, computer.getId());

            result = st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return (result == 1);
    }

    /**
     * Remove the Computer with the given id.
     * @param id the id of computer
     * @return true if the given computer was remove
     */
    public boolean removeById(Long id) {

        int result = 0;

        try (Connection connection = JdbcTool.INSTANCE.newConnection()) {

            // prepare query
            PreparedStatement st = connection.prepareStatement(REMOVE);

            st.setLong(1, id);

            result = st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return (result == 1);

    }

    /**
     * Retrieve a page of computers.
     * @param offset index of the first computer of the page
     * @return Page of computers
     */
    public Page<Computer> getPage(int offset) {

        List<Computer> computers = new ArrayList<Computer>();

        try (Connection connection = JdbcTool.INSTANCE.newConnection()) {

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
     * Retrieve a page of computers.
     * @param offset index of the first computer of the page
     * @param limit the number of computer for the page
     * @return Page of computers
     */
    public Page<Computer> getPage(int offset, int limit) {

        List<Computer> computers = new ArrayList<Computer>();

        try (Connection connection = JdbcTool.INSTANCE.newConnection()) {

            PreparedStatement st = connection.prepareStatement(GET_PAGE);
            st.setInt(1, limit);
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
     * Retrieve a page of computers.
     * @param offset index of the first computer of the page
     * @param limit the number of computer for the page
     * @param search the criteria
     * @return Page of computers
     */
    public Page<Computer> getPage(int offset, int limit, String search) {
        List<Computer> computers = new ArrayList<Computer>();

        try (Connection connection = JdbcTool.INSTANCE.newConnection()) {

            PreparedStatement st = connection.prepareStatement(GET_PAGE_SEARCH);
            st.setString(1, "%" + search + "%");
            st.setInt(2, limit);
            st.setInt(3, offset);

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
     * Retrieve the computer with the given id.
     * @param id the id of computer
     * @return the computer
     */
    public Computer findById(Long id) {

        Computer computer = null;

        try (Connection connection = JdbcTool.INSTANCE.newConnection()) {

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

    /**
     * Retrieve the number of computers.
     * @return the number of computers
     */
    public int count() {

        int count = 0;

        try (Connection connection = JdbcTool.INSTANCE.newConnection()) {

            Statement st = connection.createStatement();

            ResultSet resultSet = st.executeQuery(COUNT);

            if (resultSet.next()) {

                count = resultSet.getInt(1);

            }

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }

        return count;
    }

    /**
     * Retrieve the number of computers.
     * @param search the criteria
     * @return the number of computers
     */
    public int count(String search) {

        int count = 0;

        try (Connection connection = JdbcTool.INSTANCE.newConnection()) {

            PreparedStatement st = connection.prepareStatement(COUNT_PAGE_SEARCH);
            st.setString(1, "%" + search + "%");

            ResultSet resultSet = st.executeQuery();

            if (resultSet.next()) {

                count = resultSet.getInt(1);

            }

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }

        return count;
    }

}
