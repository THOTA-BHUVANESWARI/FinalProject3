package com.src.dao;

import java.util.List;

import com.src.model.Campaign;

public interface CampaignDAO {
    boolean insertCampaign(Campaign campaign);
    Campaign findCampaignById(int campaignId);
    List<Campaign> findAllCampaigns();
    List<Campaign> findCampaignsByCharityId(int charityId); // NEW
    boolean updateCampaign(Campaign campaign);
    boolean deleteCampaign(int campaignId);
}
