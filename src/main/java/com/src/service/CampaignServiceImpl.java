package com.src.service;

import java.util.List;

import com.src.dao.CampaignDAO;
import com.src.dao.CampaignDAOImpl;
import com.src.model.Campaign;

public class CampaignServiceImpl implements CampaignServiceInterface {

    private CampaignDAO campaignDAO = new CampaignDAOImpl();

    @Override
    public boolean addCampaign(Campaign campaign) {
        return campaignDAO.insertCampaign(campaign);
    }

    @Override
    public Campaign getCampaignById(int campaignId) {
        return campaignDAO.findCampaignById(campaignId);
    }

    @Override
    public List<Campaign> getAllCampaigns() {
        return campaignDAO.findAllCampaigns();
    }

    @Override
    public List<Campaign> getCampaignsByCharityId(int charityId) { // NEW
        return campaignDAO.findCampaignsByCharityId(charityId);
    }

    @Override
    public boolean updateCampaign(Campaign campaign) {
        return campaignDAO.updateCampaign(campaign);
    }

    @Override
    public boolean deleteCampaign(int campaignId) {
        return campaignDAO.deleteCampaign(campaignId);
    }
}
