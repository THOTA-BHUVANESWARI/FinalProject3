package com.src.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.src.model.Donor;
import com.src.util.DBConnection;

public class DonorDAOImpl implements DonorDAO {

	@Override
	public boolean insertDonor(Donor donor) {
	    String sql = "INSERT INTO donor (name, email, phone, address, password) VALUES (?, ?, ?, ?, ?)";
	    try (Connection con = DBConnection.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setString(1, donor.getDonorName());
	        ps.setString(2, donor.getEmail());
	        ps.setString(3, donor.getPhone());
	        ps.setString(4, donor.getAddress());
	        ps.setString(5, donor.getPassword());

	        return ps.executeUpdate() > 0;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return false;
	}


    @Override
    public Donor findDonorById(int donorId) {
        String sql = "SELECT * FROM donor WHERE donorId=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, donorId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Donor(
                    rs.getInt("donorId"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getString("address"),
                    rs.getString("password")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // ✅ New method to find donor by email
    @Override
    public Donor getDonorByEmail(String email) {
        String sql = "SELECT * FROM donor WHERE email=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Donor(
                    rs.getInt("donorId"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getString("address"),
                    rs.getString("password")
                    
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Donor> findAllDonors() {
        List<Donor> donors = new ArrayList<>();
        String sql = "SELECT * FROM donor";
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                donors.add(new Donor(
                    rs.getInt("donorId"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getString("address"),
                    rs.getString("password")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return donors;
    }

    @Override
    public boolean updateDonor(Donor donor) {
        String sql = "UPDATE donor SET name=?, email=?, phone=?, address=? WHERE donorId=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, donor.getDonorName());
            ps.setString(2, donor.getEmail());
            ps.setString(3, donor.getPhone());
            ps.setString(4, donor.getAddress());
            ps.setInt(5, donor.getDonorId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteDonor(int donorId) {
        String sql = "DELETE FROM donor WHERE donorId=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, donorId);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public boolean updatePasswordByEmail(String email, String newPassword) {
        String sql = "UPDATE donor SET password=? WHERE email=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, newPassword);
            ps.setString(2, email);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
