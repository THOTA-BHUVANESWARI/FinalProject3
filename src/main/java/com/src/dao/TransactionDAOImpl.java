package com.src.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.src.model.Transaction;
import com.src.util.DBConnection;

public class TransactionDAOImpl implements TransactionDAO {

	@Override
	public boolean insertTransaction(Transaction transaction) {
	    String sql = "INSERT INTO transaction (donorId, charityId, campaignId, amount, transactionDate, panNumber) VALUES (?, ?, ?, ?, ?, ?)";
	    try (Connection con = DBConnection.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setInt(1, transaction.getDonorId());
	        ps.setInt(2, transaction.getCharityId());
	        ps.setInt(3, transaction.getCampaignId());
	        ps.setDouble(4, transaction.getAmount());
	        ps.setTimestamp(5, transaction.getDonationDate() != null ?
	                new Timestamp(transaction.getDonationDate().getTime()) :
	                new Timestamp(System.currentTimeMillis()));
	        ps.setString(6, transaction.getPanNumber()); // <-- Add this line

	        return ps.executeUpdate() > 0;

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}


	private Transaction extractTransaction(ResultSet rs) throws SQLException {
	    return new Transaction(
	        rs.getInt("transactionId"),
	        rs.getInt("donorId"),
	        rs.getInt("campaignId"),
	        rs.getInt("charityId"),
	        rs.getDouble("amount"),
	        rs.getTimestamp("transactionDate"),
	        rs.getString("panNumber") // <-- corrected column name
	    );
	}


    @Override
    public Transaction findTransactionById(int transactionId) {
        String sql = "SELECT * FROM transaction WHERE transactionId=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, transactionId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return extractTransaction(rs);

        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    @Override
    public List<Transaction> findAllTransactions() {
        List<Transaction> list = new ArrayList<>();
        String sql = "SELECT * FROM transaction";
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) list.add(extractTransaction(rs));

        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    @Override
    public List<Transaction> findTransactionsByDonorId(int donorId) {
        List<Transaction> list = new ArrayList<>();
        String sql = "SELECT * FROM transaction WHERE donorId=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, donorId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(extractTransaction(rs));

        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    @Override
    public List<Transaction> findTransactionsByCampaign(int campaignId) {
        List<Transaction> list = new ArrayList<>();
        String sql = "SELECT * FROM transaction WHERE campaignId=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, campaignId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(extractTransaction(rs));

        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    @Override
    public List<Transaction> findTransactionsByCharity(int charityId) {
        List<Transaction> list = new ArrayList<>();
        String sql = "SELECT * FROM transaction WHERE charityId=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, charityId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(extractTransaction(rs));

        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }
}
