package com.src.service;

import java.util.List;

import com.src.dao.TransactionDAO;
import com.src.dao.TransactionDAOImpl;
import com.src.model.Transaction;

public class TransactionServiceImpl implements TransactionServiceInterface {

    private TransactionDAO transactionDAO = new TransactionDAOImpl();

    @Override
    public boolean addTransaction(Transaction transaction) {
        return transactionDAO.insertTransaction(transaction);
    }

    @Override
    public Transaction getTransactionById(int transactionId) {
        return transactionDAO.findTransactionById(transactionId);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionDAO.findAllTransactions();
    }

    @Override
    public List<Transaction> getTransactionsByDonorId(int donorId) {
        return transactionDAO.findTransactionsByDonorId(donorId);
    }

    @Override
    public List<Transaction> getTransactionsByCampaign(int campaignId) {
        return transactionDAO.findTransactionsByCampaign(campaignId);
    }

    @Override
    public List<Transaction> getTransactionsByCharityId(int charityId) {
        return transactionDAO.findTransactionsByCharity(charityId);
    }
}
