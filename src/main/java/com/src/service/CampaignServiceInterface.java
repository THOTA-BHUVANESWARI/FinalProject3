package com.src.service;

import java.util.List;

import com.src.model.Campaign;

public interface CampaignServiceInterface {
    boolean addCampaign(Campaign campaign);
    Campaign getCampaignById(int campaignId);
    List<Campaign> getAllCampaigns();
    List<Campaign> getCampaignsByCharityId(int charityId); // NEW
    boolean updateCampaign(Campaign campaign);
    boolean deleteCampaign(int campaignId);
}
