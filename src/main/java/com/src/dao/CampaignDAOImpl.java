package com.src.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.src.model.Campaign;
import com.src.util.DBConnection;

public class CampaignDAOImpl implements CampaignDAO {

    @Override
    public boolean insertCampaign(Campaign campaign) {
        String sql = "INSERT INTO campaign (title, description, startDate, endDate, status, charityId) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, campaign.getCampaignName()); // title
            ps.setString(2, campaign.getDescription());
            ps.setDate(3, Date.valueOf(campaign.getStartDate()));
            ps.setDate(4, Date.valueOf(campaign.getEndDate()));
            ps.setString(5, "Active"); // default status
            ps.setInt(6, campaign.getCharityId());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Campaign findCampaignById(int campaignId) {
        String sql = "SELECT * FROM campaign WHERE campId=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, campaignId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Campaign c = new Campaign();
                c.setCampaignId(rs.getInt("campId"));
                c.setCampaignName(rs.getString("title"));
                c.setDescription(rs.getString("description"));
                c.setStartDate(rs.getDate("startDate").toLocalDate());
                c.setEndDate(rs.getDate("endDate").toLocalDate());
                c.setCharityId(rs.getInt("charityId"));
                return c;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Campaign> findAllCampaigns() {
        List<Campaign> campaigns = new ArrayList<>();
        String sql = "SELECT * FROM campaign";
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Campaign c = new Campaign();
                c.setCampaignId(rs.getInt("campId"));
                c.setCampaignName(rs.getString("title"));
                c.setDescription(rs.getString("description"));
                c.setStartDate(rs.getDate("startDate").toLocalDate());
                c.setEndDate(rs.getDate("endDate").toLocalDate());
                c.setCharityId(rs.getInt("charityId"));
                campaigns.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return campaigns;
        
    }

    @Override
    public boolean updateCampaign(Campaign campaign) {
        String sql = "UPDATE campaign SET title=?, description=?, startDate=?, endDate=?, charityId=? WHERE campId=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, campaign.getCampaignName());
            ps.setString(2, campaign.getDescription());
            ps.setDate(3, Date.valueOf(campaign.getStartDate()));
            ps.setDate(4, Date.valueOf(campaign.getEndDate()));
            ps.setInt(5, campaign.getCharityId());
            ps.setInt(6, campaign.getCampaignId());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteCampaign(int campaignId) {
        String sql = "DELETE FROM campaign WHERE campId=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, campaignId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public List<Campaign> findCampaignsByCharityId(int charityId) {
        List<Campaign> campaigns = new ArrayList<>();
        String sql = "SELECT * FROM campaign WHERE charityId=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, charityId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Campaign c = new Campaign();
                c.setCampaignId(rs.getInt("campId"));
                c.setCampaignName(rs.getString("title"));
                c.setDescription(rs.getString("description"));
                c.setStartDate(rs.getDate("startDate").toLocalDate());
                c.setEndDate(rs.getDate("endDate").toLocalDate());
                c.setCharityId(rs.getInt("charityId"));
                campaigns.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return campaigns;
    }

}
