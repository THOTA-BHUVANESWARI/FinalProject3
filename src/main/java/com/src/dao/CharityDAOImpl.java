package com.src.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.src.model.Charity;
import com.src.util.DBConnection;

public class CharityDAOImpl implements CharityDAO {

	@Override
	public boolean insertCharity(Charity charity) throws Exception {
	    boolean result = false;
	    Connection con = DBConnection.getConnection();
	    String query = "INSERT INTO charity(name, regNo, contact, email, location, password) VALUES (?, ?, ?, ?, ?, ?)";
	    try (PreparedStatement ps = con.prepareStatement(query)) {
	        ps.setString(1, charity.getName());
	        ps.setString(2, charity.getRegNo());
	        ps.setString(3, charity.getContact());
	        ps.setString(4, charity.getEmail());
	        ps.setString(5, charity.getLocation());
	        ps.setString(6, charity.getPassword());

	        int rows = ps.executeUpdate();
	        result = (rows > 0);
	    } catch (SQLException e) {
	        throw new Exception(e.getMessage()); // propagate for duplicate email handling
	    }
	    return result;
	}


    @Override
    public Charity findCharityById(int id) {
        Charity charity = null;
        String sql = "SELECT * FROM charity WHERE charityId = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                charity = extractCharityFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return charity;
    }

    @Override
    public Charity findCharityByEmail(String email) {
        Charity charity = null;
        String sql = "SELECT * FROM charity WHERE email = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                charity = extractCharityFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return charity;
    }

    @Override
    public List<Charity> findAllCharities() {
        List<Charity> list = new ArrayList<>();
        String sql = "SELECT * FROM charity";
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                list.add(extractCharityFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean updateCharity(Charity charity) {
        String sql = "UPDATE charity SET name=?, regNo=?, contact=?, email=?, location=? WHERE charityId=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, charity.getName());
            ps.setString(2, charity.getRegNo());
            ps.setString(3, charity.getContact());
            ps.setString(4, charity.getEmail());
            ps.setString(5, charity.getLocation());
            ps.setInt(6, charity.getCharityId());

            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean deleteCharity(int id) {
        String sql = "DELETE FROM charity WHERE charityId = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Helper method
    private Charity extractCharityFromResultSet(ResultSet rs) throws SQLException {
        Charity c = new Charity();
        c.setCharityId(rs.getInt("charityId"));
        c.setName(rs.getString("name"));
        c.setRegNo(rs.getString("regNo"));
        c.setContact(rs.getString("contact"));
        c.setEmail(rs.getString("email"));
        c.setLocation(rs.getString("location"));
        c.setPassword(rs.getString("password"));
        return c;
    }
}
