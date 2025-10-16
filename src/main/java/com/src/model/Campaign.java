package com.src.model;

import java.time.LocalDate;
import java.util.Objects;

import com.src.annotation.Column;
import com.src.annotation.Id;
import com.src.annotation.Table;

@Table(name = "campaign")
public class Campaign {

    @Id
    @Column(name = "campId")
    private int campaignId;

    @Column(name = "title", length = 100)
    private String campaignName;

    @Column(name = "description")
    private String description;

    @Column(name = "startDate")
    private LocalDate startDate;

    @Column(name = "endDate")
    private LocalDate endDate;

    @Column(name = "status", length = 20)
    private String status;

    @Column(name = "charityId")
    private int charityId;

    public Campaign() {}

    public Campaign(int campaignId, String campaignName, int charityId, String description, LocalDate startDate, LocalDate endDate) {
        this.campaignId = campaignId;
        this.campaignName = campaignName;
        this.charityId = charityId;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

	@Override
	public int hashCode() {
		return Objects.hash(campaignId, campaignName, charityId, description, endDate, startDate, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Campaign other = (Campaign) obj;
		return campaignId == other.campaignId && Objects.equals(campaignName, other.campaignName)
				&& charityId == other.charityId && Objects.equals(description, other.description)
				&& Objects.equals(endDate, other.endDate) && Objects.equals(startDate, other.startDate)
				&& Objects.equals(status, other.status);
	}

	public int getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(int campaignId) {
		this.campaignId = campaignId;
	}

	public String getCampaignName() {
		return campaignName;
	}

	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getCharityId() {
		return charityId;
	}

	public void setCharityId(int charityId) {
		this.charityId = charityId;
	}

	@Override
	public String toString() {
		return "Campaign [campaignId=" + campaignId + ", campaignName=" + campaignName + ", description=" + description
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", status=" + status + ", charityId="
				+ charityId + "]";
	}

}
