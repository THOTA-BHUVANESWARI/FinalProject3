package com.src.model;

import java.util.Date;

import com.src.annotation.Column;
import com.src.annotation.Id;
import com.src.annotation.Table;
import com.src.annotation.Temporal;
import com.src.annotation.TemporalType;

@Table(name = "transaction")
public class Transaction {

    @Id
    @Column(name = "transactionId")
    private int transactionId;

    @Column(name = "donorId", nullable = false)
    private int donorId;

    @Column(name = "charityId", nullable = false)
    private int charityId;

    @Column(name = "campaignId", nullable = false)
    private int campaignId;

    @Column(name = "amount", nullable = false)
    private double amount;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "transactionDate")
    private Date donationDate;

    // New fields
    @Column(name = "panNumber")
    private String panNumber;

    @Column(name = "transactionPhoto")
    private String transactionPhoto;

    // Constructors
    public Transaction() {}

    public Transaction(int transactionId, int donorId, int campaignId, int charityId,
                       double amount, Date donationDate, String panNumber) {
        this.transactionId = transactionId;
        this.donorId = donorId;
        this.charityId = charityId;
        this.campaignId = campaignId;
        this.amount = amount;
        this.donationDate = donationDate;
        this.panNumber = panNumber;
    }

    // Getters and Setters
    public int getTransactionId() { return transactionId; }
    public void setTransactionId(int transactionId) { this.transactionId = transactionId; }

    public int getDonorId() { return donorId; }
    public void setDonorId(int donorId) { this.donorId = donorId; }

    public int getCharityId() { return charityId; }
    public void setCharityId(int charityId) { this.charityId = charityId; }

    public int getCampaignId() { return campaignId; }
    public void setCampaignId(int campaignId) { this.campaignId = campaignId; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public Date getDonationDate() { return donationDate; }
    public void setDonationDate(Date donationDate) { this.donationDate = donationDate; }

    public String getPanNumber() { return panNumber; }
    public void setPanNumber(String panNumber) { this.panNumber = panNumber; }

}
